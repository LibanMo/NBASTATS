import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';

function CreatePlayerForm() {
  const [playerName, setPlayerName] = useState('');
  const [teamName, setTeamName] = useState('');
  const [isLoading, setIsLoading] = useState(false); 
  const navigate = useNavigate();

  
const API_BASE_URL = process.env.REACT_APP_API_BASE_URL

  const handleSubmit = async (event) => {
    console.log(API_BASE_URL)
    event.preventDefault(); // Prevent the default form submit action
    setIsLoading(true);

    try {
      
      const response = await fetch(`${API_BASE_URL}/create`, { // Use the API_BASE_URL
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({
          name: playerName,
          team: teamName,
        }),
      });

      setIsLoading(false);
      if (response.ok) {
        const data = await response.json();
        console.log('Player created:', data);
        navigate(`/${data}`); // Assume `data` has an `id` field for the new player
      } else {
        console.error('Failed to create player');
        // Optionally handle error response more gracefully here
      }
    } catch (error) {
      console.error('Error creating player:', error);
      setIsLoading(false);
    }
  };

  if (isLoading) {
    return <div>Loading... {API_BASE_URL}</div>; // Render loading indicator while loading
  }

  console.log("port: " + API_BASE_URL)

  return (
    <div>
      <h2>Create a New GGG</h2>
      <form onSubmit={handleSubmit}>
        <label>
          Player Name:
          <input
            type="text"
            value={playerName}
            onChange={(e) => setPlayerName(e.target.value)}
          />
        </label>
        <br />
        <label>
          Team Name:
          <input
            type="text"
            value={teamName}
            onChange={(e) => setTeamName(e.target.value)}
          />
        </label>
        <br />
        <button type="submit">Create Player</button>
      </form>
    </div>
  );
}

export default CreatePlayerForm;
