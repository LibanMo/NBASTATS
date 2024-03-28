import React, { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';


const PlayerStats = () => {
  const [stats, setStats] = useState([]);
  const { seasonId } = useParams(); // Extract the seasonId from URL parameters
   const API_BASE_URL = process.env.REACT_APP_API_BASE_URL

  useEffect(() => {
    const fetchStats = async () => {
      try {
      
        const response = await fetch(`${API_BASE_URL}/stats/season/${seasonId}`);

        console.log(response)
        const data = await response.json();
        setStats(data);
        <div>
        loading stats'</div>
        console.log("amount of items = " + data.length )
      } catch (error) {
        console.error("Failed to fetch player stats:", error);
      }
    };

    fetchStats();
  }, [seasonId]);

  const styles = {
    table: {
      position: "relative",
      left: "200px",
      width: '40%',
      backgroundColor: '#333', // Example dark background color
      color: 'lightgrey', // Light font color for contrast
      borderCollapse: 'collapse',
    },
    th: {
      padding: '8px',
      textAlign: 'left',
      borderBottom: '1px solid #ddd',
    },
    td: {
      padding: '8px',
      textAlign: 'left',
      borderBottom: '1px solid #ddd',
    }
  };


  return (
     <table style={styles.table}>
      <thead>
        <tr>
          <th style={styles.th}>Date</th>
          <th style={styles.th}>PTS</th>
          <th style={styles.th}>REB</th>
          <th style={styles.th}>AST</th>
          <th style={styles.th}>STL</th>
          <th style={styles.th}>BLK</th>
          {/* Add other headers */}
        </tr>
      </thead>
      <tbody>
          {/* Create a copy and reverse the array to avoid mutating the original stats array */}
          {[...stats].reverse().map((stat, index) => (
            <tr key={index}> {/* Consider using a unique id instead of index */}
              <td style={styles.td}>{stat.date}</td>
              <td style={styles.td}>{stat.pts}</td>
              <td style={styles.td}>{stat.reb}</td>
              <td style={styles.td}>{stat.ast}</td>
              <td style={styles.td}>{stat.stl}</td>
              <td style={styles.td}>{stat.blk}</td>
              {/* Render other properties as needed */}
            </tr>
          ))}
      </tbody>
    </table>
  );
       
  
  
};

export default PlayerStats;
