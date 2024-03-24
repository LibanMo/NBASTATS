package com.example.dockerizespring.service;

import com.example.dockerizespring.models.Player;
import com.example.dockerizespring.models.PlayerStat;
import com.example.dockerizespring.repos.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerService {
    @Autowired
    private PlayerRepository playerRepository; // You need to implement this repository interface

    public void SavePlayerStats(String playerName, List<PlayerStat> playerStats){
        System.out.println("HEre is the name:" + playerName);
        System.out.println("stats: " + playerStats.size());
        Player player = playerRepository.findByName(playerName)
                .orElse(new Player());
        playerStats.forEach(player::addPlayerStat);
        playerRepository.save(player);


    }

    public List<PlayerStat> statsSeason(Long id) {
        Optional<Player> maybePlayer = playerRepository.findById(id);
        if(maybePlayer.isPresent()){
            Player p = maybePlayer.get();
            for(PlayerStat stat : p.getPlayerStats()){
                System.out.println(stat.getPlayer().getTeam() + " -- pts : " + stat.getPts());
            }

            return p.getPlayerStats();
        }
        return null;
    }

    public Player getPlayerById(Long id) {
       Optional<Player> maybePlayer = playerRepository.findById(id);

        return maybePlayer.orElse(null);
    }

    public void deleteById(Long id) {
        playerRepository.deleteById(id);
    }

    public void savePlayer(Player player) {
        playerRepository.save(player);
    }
}
