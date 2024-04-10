# dash_app.py
from dash import Dash, dcc, html, Input, Output, callback_context
import plotly.graph_objects as go
import pandas as pd
import psycopg2
from urllib.parse import parse_qs
import os
from dotenv import load_dotenv

# Initialize Dash app
app = Dash(__name__)
server = app.server
load_dotenv()


# Define the layout of the app
app.layout = html.Div([
    dcc.Location(id='url', refresh=False),  # To access URL parameters
    dcc.Graph(id='player_comparison_graph'),  # The graph to display player stats
      dcc.RadioItems(
        id="stats_checklist",
        options=[
            {'label': 'Points', 'value': 'pts'},
            {'label': 'Rebounds', 'value': 'reb'},
            {'label': 'Assists', 'value': 'ast'},
            {'label': 'Steals', 'value': 'stl'},
            {'label': 'Blocks', 'value': 'blk'}
        ],
        value='pts',  # Default value to display Points initially
        inline=True
      )
])

# Define the callback to update the graph based on URL parameters

@app.callback(
    Output('player_comparison_graph', 'figure'),
    Input('url', 'search'),
    Input('stats_checklist', 'value')
)
def update_graph(search, selected_stats):
    if not search or not selected_stats:
        return go.Figure()

    # Parse the URL search parameters for multiple player IDs
    params = parse_qs(search.lstrip('?'))
    player_ids = [value for key, value in params.items() if key.startswith('player_id_')]

    if not player_ids or any([pid == [None] for pid in player_ids]):
        return go.Figure()  # Return an empty figure if no valid player IDs

    # Assuming all player_ids need to be integers
    try:
        player_ids = [int(pid[0]) for pid in player_ids]  # Convert each player ID from list of strings to int
    except ValueError:
        return go.Figure()  # Return an empty figure if any player_id is invalid

    # Fetch data and create a figure for multiple players
    fig = go.Figure()
    for pid in player_ids:
        df = fetch_player_data(pid, [selected_stats])  # Fetch data for the selected statistic

        if df.empty:
            continue

        player_name = df['name'].iloc[0]

        # Add a trace for the selected statistic
        fig.add_trace(
            go.Scatter(x=df['date'], y=df[selected_stats], mode='lines+markers', name=f'{player_name} - {selected_stats.capitalize()}')
        )

    fig.update_layout(
        title="Player Statistics Comparison",
        xaxis_title='Date',
        yaxis_title=selected_stats.capitalize(),
        legend_title="Player - Statistic",
    )

    return fig

# Function to fetch player data based on player_id
def fetch_player_data(player_id, selected_stats):
    # Database connection parameters
    db_name = "playersDB"
    db_user = os.getenv("DB_USER")
    db_password = os.getenv("DB_PASSWORD")
    db_host = "db"
    db_port = "5432"

    # Connect to the database
    conn = psycopg2.connect(
        dbname=db_name,
        user=db_user,
        password=db_password,
        host=db_host,
        port=db_port
    )

    # SQL query to fetch player statistics
    selected_columns = ', '.join([f'ps.{stat}' for stat in selected_stats] + ['ps.date', 'p.name'])
    query = f"""
    SELECT {selected_columns}
    FROM player_stat ps
    JOIN player p ON ps.player_id = p.id
    WHERE ps.player_id = %s
    ORDER BY ps.date;
    """

    # Execute the query and fetch the data
    df = pd.read_sql_query(query, conn, params=(player_id,))

    # Close the database connection
    conn.close()

    return df



