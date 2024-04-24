package com.example.dockerizespring.controller;

import com.example.dockerizespring.models.Player;
import com.example.dockerizespring.models.PlayerDTO;
import com.example.dockerizespring.models.PlayerStat;
import com.example.dockerizespring.repos.PlayerRepository;
import com.example.dockerizespring.service.PlayerService;
import com.example.dockerizespring.service.ScraperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("")
public class PlayerController {
    @Autowired
    private  ScraperService scraperService;


    @Autowired
    PlayerService playerService;

    @GetMapping("/stats/season/{id}")
    @CrossOrigin(origins = {"http://localhost:3000", "http://localhost:8082", "http://16.171.23.194:8082"})
    public List<PlayerStat> statsSeason(@PathVariable Long id){
        return playerService.statsSeason(id);
    }


    @GetMapping("/scrape/stats/{id}")
    @CrossOrigin(origins = {"http://localhost:3000", "http://localhost:8082", "http://16.171.23.194:8082"})
    public List<PlayerStat> getStats(@PathVariable Long id){
        Player p = playerService.findById(id);
        return scraperService.getStats(p.getName());
    }

    // Create a new person
    @PostMapping("create")
    @CrossOrigin(origins = {"http://localhost:3000", "http://localhost:8082"})
    public Long createPlayer(@RequestBody Player player) {
        playerService.savePlayer(player);
        getStats(player.getId());
        return player.getId();
    }
    @CrossOrigin(origins = {"http://localhost:3000", "http://localhost:8082"})
    @GetMapping("/{id}")
    public Player getPlayerById(@PathVariable Long id){
        return playerService.getPlayerById(id);
    }


    @GetMapping("players/latest")
    @CrossOrigin(origins = {"http://localhost:3000", "http://localhost:8082"})

    public List<PlayerDTO> getAllPlayersLatestGame() {
        return playerService.getAllPlayersLatestGame();
    }


    @PutMapping("/stats/update/{id}")
    @CrossOrigin(origins = {"http://localhost:3000", "http://localhost:8082"})
    public void  updatePlayerStat(@PathVariable Long id) {
        playerService.updateStat(id);

    }


    @DeleteMapping("delete/{id}")
    @CrossOrigin(origins = {"http://localhost:3000", "http://localhost:8082"})
    public void deleteById(@PathVariable Long id){

        Player p = playerService.getPlayerById(id);
        playerService.deleteById(p.getId());
    }

    @PostMapping("save/all")
    @Async
    public void AddAll(@RequestBody List<Player> players){
        for( Player p : players){
            createPlayer(p);
            try {
                Thread.sleep(13000); // 60 seconds
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

}
