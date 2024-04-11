import React from 'react';
import ReactDOM from 'react-dom/client';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import PlayerStats from './components/PlayerStats';
import DashIframe from './components/DashIframe';
import AllPlayerStats from './components/AllPlayerStat';
import CreatePlayerForm from './components/CreatePlayerForm';
const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <BrowserRouter>
    <Routes>

        <Route path="/compare" element={<DashIframe />}></Route>
        <Route path="/:seasonId" element={<PlayerStats />}></Route>
        <Route path="/" element={<AllPlayerStats />}></Route>
        <Route path="/create" element={<CreatePlayerForm/>}></Route>
    </Routes>
      
  </BrowserRouter>,
);
