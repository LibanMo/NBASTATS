import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';

function CreatePlayerForm() {
  const [playerName, setPlayerName] = useState('');
  const [teamName, setTeamName] = useState('');
   const [isLoading, setIsLoading] = useState(false); 
   const navigate = useNavigate();

  const handleSubmit = async (event) => {
    event.preventDefault(); // Prevent the default form submit action
    setIsLoading(true);

    try {
      const response = await fetch('http://localhost:8080/create', { // Update with your actual endpoint URL
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
        // Handle success response
        navigate(`/${data}`);
      } else {
        console.error('Failed to create player');
        // Handle error response
      }
    } catch (error) {
      console.error('Error creating player:', error);
       setIsLoading(false);
    }
  };
  if (isLoading) {
    return <div>Loading...</div>; // Render loading indicator while loading
  }

  return (
    <div>
    <h2>Create a New Player</h2>
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
