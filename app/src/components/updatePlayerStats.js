export const updatePlayerStats = (playerId) => {
   const API_BASE_URL = "http://localhost:8080";
  return fetch(`${API_BASE_URL}/stats/update/${playerId}`, {
    method: 'PUT', // Using PUT as per your backend's requirement
    headers: { 'Content-Type': 'application/json' },
  })
  .then(response => {
    if (!response.ok) {
      throw new Error('Network response was not ok');
    }

  })
  .catch(error => {
    console.error('Error updating player stats:', error);
    // Handle or throw the error as appropriate
  });
};