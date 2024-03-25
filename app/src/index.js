import React from 'react';
import ReactDOM from 'react-dom/client';
import App from './App';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import PlayerStats from './components/PlayerStats';
const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <BrowserRouter>
    <Routes>

        <Route path="/" element={<App />}></Route>
        <Route path="/:seasonId" element={<PlayerStats />}></Route>
    </Routes>
      
  </BrowserRouter>,
);
