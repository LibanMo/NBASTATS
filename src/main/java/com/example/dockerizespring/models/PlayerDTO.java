package com.example.dockerizespring.models;

public class PlayerDTO {
    private Long id;
    private String name;
    private String team;
    // Assuming PlayerStat has properties like date, points, rebounds, etc.
    // We will include the latest game stats as an example
    private PlayerStatDTO latestGameStat;

    public PlayerDTO(Long id, String name, String team, PlayerStatDTO latestGameStat) {
        this.id = id;
        this.name = name;
        this.team = team;
        this.latestGameStat = latestGameStat;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public PlayerStatDTO getLatestGameStat() {
        return latestGameStat;
    }

    public void setLatestGameStat(PlayerStatDTO latestGameStat) {
        this.latestGameStat = latestGameStat;
    }
}