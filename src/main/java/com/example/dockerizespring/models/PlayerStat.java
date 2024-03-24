package com.example.dockerizespring.models;

import jakarta.persistence.*;


@Entity
public class PlayerStat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

  private String date;
  private Integer pts;
  private Integer reb;
  private Integer ast;
  private Integer stl;
  private Integer blk;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "player_id")
    private Player player;

    public PlayerStat() {
    }

    public PlayerStat(String date, Integer pts, Integer reb, Integer ast, Integer stl, Integer blk) {
        this.date = date;
        this.pts = pts;
        this.reb = reb;
        this.ast = ast;
        this.stl = stl;
        this.blk = blk;
    }

    public Long getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getPts() {
        return pts;
    }

    public void setPts(Integer pts) {
        this.pts = pts;
    }

    public Integer getReb() {
        return reb;
    }

    public void setReb(Integer reb) {
        this.reb = reb;
    }

    public Integer getAst() {
        return ast;
    }

    public void setAst(Integer ast) {
        this.ast = ast;
    }

    public Integer getStl() {
        return stl;
    }

    public void setStl(Integer stl) {
        this.stl = stl;
    }

    public Integer getBlk() {
        return blk;
    }

    public void setBlk(Integer blk) {
        this.blk = blk;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
