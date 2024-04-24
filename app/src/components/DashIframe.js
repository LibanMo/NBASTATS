import React, { useState } from "react";

function DashIframe() {
  // Initialize with one empty input
  const [playerIds, setPlayerIds] = useState([""]);

  const handlePlayerIdChange = (index, value) => {
    // Update the specific player ID based on the index
    const newPlayerIds = [...playerIds];
    newPlayerIds[index] = value;
    setPlayerIds(newPlayerIds);
  };

  const addPlayerInput = () => {
    // Add a new input field by adding an empty string to the playerIds array
    setPlayerIds([...playerIds, ""]);
  };

  const removePlayerInput = (index) => {
    // Remove the input field at the specific index
    const newPlayerIds = [...playerIds];
    newPlayerIds.splice(index, 1);
    setPlayerIds(newPlayerIds);
  };

  const iframeSrc = `http://localhost:8050/?${playerIds
    .map((id, index) => `player_id_${index + 1}=${id}`)
    .join("&")}`;

  // Hitta IP-adressen:

  return (
    <div>
      {playerIds.map((id, index) => (
        <div key={index}>
          <input
            type="number"
            placeholder={`Enter Player ID ${index + 1}`}
            value={id}
            onChange={(e) => handlePlayerIdChange(index, e.target.value)}
          />
          {index > 0 && (
            <button onClick={() => removePlayerInput(index)}>Remove</button>
          )}
        </div>
      ))}
      <button onClick={addPlayerInput}>Add Player</button>
      <iframe
        src={iframeSrc}
        style={{ width: "100%", height: "600px", border: "none" }}
        title="Player Statistics"
      />
    </div>
  );
}

export default DashIframe;
