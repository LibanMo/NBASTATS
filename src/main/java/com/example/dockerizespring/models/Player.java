package com.example.dockerizespring.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Entity
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String team;
    @OneToMany(mappedBy = "player", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<PlayerStat> playerStats = new ArrayList<>();

    public Player(String name, String team) {
        this.name = name;
        this.team = team;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public void setPlayerStats(List<PlayerStat> playerStats) {
        playerStats.forEach(this::addPlayerStat);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTeam() {
        return team;
    }

    public List<PlayerStat> getPlayerStats() {
        return playerStats;
    }

    public void addPlayerStat(PlayerStat playerStat) {
        this.playerStats.add(playerStat);
        playerStat.setPlayer(this);
    }

    public PlayerStat getLatestGameStat() {
        return this.getPlayerStats().stream()
                .max(Comparator.comparing(PlayerStat::getDate))
                .orElse(null); // Returns null if no stats are found
    }


    public Player() {
    }

}
