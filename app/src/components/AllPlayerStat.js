import React, { useState, useEffect } from 'react';
import './styles/AllPlayerStats.css';
import { useNavigate } from 'react-router-dom';
// AllPlayerStats.js or some other file
import { updatePlayerStats } from './updatePlayerStats';



function AllPlayerStats() {
    const [stats, setStats] = useState([]);
    const [isLoading, setIsLoading] = useState(false);
    const [error, setError] = useState(null);
    const navigate = useNavigate();
     const API_BASE_URL = process.env.REACT_APP_API_BASE_URL;

    useEffect(() => {
        setIsLoading(true);
        fetch(API_BASE_URL+ '/players/latest')
            .then(response => {
                if (!response.ok) {
                    throw new Error('Failed to fetch');
                }
                return response.json();
            })
            .then(data => {
                setStats(data);
                setIsLoading(false);
            })
            .catch(error => {
                console.error('Error fetching player stats:', error);
                setError(error.toString());
                setIsLoading(false);
            });
    }, []);

    if (isLoading) return <div className="allPlayerStats">Loading...</div>;
    if (error) return <div className="allPlayerStats">Error: {error}</div>;

        const handleRowClick = (playerId) => {
        navigate(`/${playerId}`); // Navigate to player detail page
    };

    

    return (
        <div className="allPlayerStats">
            <h2>All Player Stats</h2>
            <table className="table">
                <thead>
                    <tr>
                        <th>Player Name</th>
                        <th>Game Date</th>
                        <th>Points</th>
                        <th>Rebounds</th>
                        <th>Assists</th>
                        <th>Steals</th>
                        <th>Blocks</th>
                    </tr>
                </thead>
                <tbody>
                    {stats.map((stat, index) => (
                        <tr key={index} onClick={() => handleRowClick(stat.id)} >
                            <td>{stat.name}</td> 
                            <td>{stat.latestGameStat.date}</td>
                            <td>{stat.latestGameStat.pts}</td>
                            <td>{stat.latestGameStat.reb}</td>
                            <td>{stat.latestGameStat.ast}</td>
                            <td>{stat.latestGameStat.stl}</td>
                            <td>{stat.latestGameStat.blk}</td>
                           <td>
                                <button 
                                    onClick={(e) => {
                                        e.stopPropagation(); // Prevent the row click event
                                        updatePlayerStats(stat.id);
                                    }}
                                    >
                                    Update Stats
                                </button>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
}

export default AllPlayerStats;
