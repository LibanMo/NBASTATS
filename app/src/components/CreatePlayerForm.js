import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import "./styles/CreatePlayerForm.css";

function CreatePlayerForm() {
  const [playerName, setPlayerName] = useState("");
  const [teamName, setTeamName] = useState("");
  const [isLoading, setIsLoading] = useState(false);
  const navigate = useNavigate();

  const API_BASE_URL = "http://localhost:8080";

  const handleSubmit = async (event) => {
    console.log(API_BASE_URL);
    event.preventDefault(); // Prevent the default form submit action
    setIsLoading(true);

    try {
      const response = await fetch(`${API_BASE_URL}/create`, {
        // Use the API_BASE_URL
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          name: playerName,
          team: teamName,
        }),
      });

      setIsLoading(false);
      if (response.ok) {
        const data = await response.json();
        console.log("Player created:", data);
        navigate(`/${data}`); // Assume `data` has an `id` field for the new player
      } else {
        console.error("Failed to create player");
        // Optionally handle error response more gracefully here
      }
    } catch (error) {
      console.error("Error creating player:", error);
      setIsLoading(false);
    }
  };

  if (isLoading) {
    return <div>Loading... </div>; // Render loading indicator while loading
  }

  return (
    <div className="form-group">
      <h2>Create a New NBA Player</h2>
      <form onSubmit={handleSubmit}>
        <div>
          <label>
            Player Name:
            <input
              type="text"
              value={playerName}
              onChange={(e) => setPlayerName(e.target.value)}
              className="form-control form-control-sm"
            />
          </label>
        </div>
        <br />
        <label>
          Team Name:
          <input
            type="text"
            value={teamName}
            onChange={(e) => setTeamName(e.target.value)}
            className="form-control form-control-sm"
          />
        </label>
        <br />
        <button type="submit">Create Player</button>
      </form>
    </div>
  );
}

export default CreatePlayerForm;
