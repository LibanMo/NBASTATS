import React, { useState, useEffect } from "react";
import { useParams } from "react-router-dom";
import "./styles/PlayerStatstyles.css";

const PlayerStats = () => {
  const [stats, setStats] = useState([]);
  const { seasonId } = useParams(); // Extract the seasonId from URL parameters
  const API_BASE_URL = "http://localhost:8080";

  useEffect(() => {
    const fetchStats = async () => {
      try {
        const response = await fetch(
          `${API_BASE_URL}/stats/season/${seasonId}`,
        );

        console.log(response);
        const data = await response.json();
        setStats(data);
        <div>loading stats'</div>;
        console.log("amount of items = " + data.length);
      } catch (error) {
        console.error("Failed to fetch player stats:", error);
      }
    };

    fetchStats();
  }, [seasonId]);

  return (
    <table>
      <thead>
        <tr>
          <th>Date</th>
          <th>PTS</th>
          <th>REB</th>
          <th>AST</th>
          <th>STL</th>
          <th>BLK</th>
          {/* Add other headers */}
        </tr>
      </thead>
      <tbody>
        {/* Create a copy and reverse the array to avoid mutating the original stats array */}
        {[...stats].reverse().map((stat, index) => (
          <tr key={index}>
            {" "}
            {/* Consider using a unique id instead of index */}
            <td>{stat.date}</td>
            <td>{stat.pts}</td>
            <td>{stat.reb}</td>
            <td>{stat.ast}</td>
            <td>{stat.stl}</td>
            <td>{stat.blk}</td>
            {/* Render other properties as needed */}
          </tr>
        ))}
      </tbody>
    </table>
  );
};

export default PlayerStats;
