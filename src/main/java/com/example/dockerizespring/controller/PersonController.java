package com.example.dockerizespring.controller;

import com.example.dockerizespring.models.Player;
import com.example.dockerizespring.models.PlayerStat;
import com.example.dockerizespring.repos.PlayerRepository;
import com.example.dockerizespring.service.PlayerService;
import com.example.dockerizespring.service.ScraperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("")

public class PersonController {
    @Autowired
    private final ScraperService scraperService;
    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    PlayerService playerService;

    @Autowired
    public PersonController(ScraperService scraperService, PlayerRepository playerRepository, PlayerService playerService) {
        this.scraperService = scraperService;
        this.playerRepository = playerRepository;
        this.playerService = playerService;

    }

    @GetMapping("/stats/season/{id}")
    @CrossOrigin(origins = {"http://localhost:3000", "http://localhost:8082"})
    public List<PlayerStat> statsSeason(@PathVariable Long id){
        return playerService.statsSeason(id);
    }



    @GetMapping("/scrape/stats/{id}")
    public List<PlayerStat> getStats(@PathVariable Long id){
        Optional<Player> p = playerRepository.findById(id);
        if(p.isPresent()){
            Player isPlayer = p.get();
            return scraperService.getStats(isPlayer.getName());
        }
        return null;
    }

    // Create a new person
    @PostMapping("create")
    public void createPlayer(@RequestBody Player player) {
        playerService.savePlayer(player);
        getStats(player.getId());
    }

    @GetMapping("/{id}")
    public Player getPlayerById(@PathVariable Long id){
       return playerService.getPlayerById(id);
    }

    @DeleteMapping("delete/{id}")
    public void deleteById(@PathVariable Long id){

        Player p = playerService.getPlayerById(id);
        playerService.deleteById(p.getId());
    }



}
