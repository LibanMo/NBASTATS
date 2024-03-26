import React from 'react';
import ReactDOM from 'react-dom/client';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import PlayerStats from './components/PlayerStats';
import CreatePlayerForm from './components/CreatePlayerForm';
import AllPlayerStats from './components/AllPlayerStat';
const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <BrowserRouter>
    <Routes>

        <Route path="/" element={<CreatePlayerForm/>}></Route>
        <Route path="/:seasonId" element={<PlayerStats />}></Route>
        <Route path="/all" element={<AllPlayerStats />}></Route>
    </Routes>
      
  </BrowserRouter>,
);
