package com.example.dockerizespring.models;

import java.time.LocalDate;

public class PlayerStatDTO {
    private String date;

    private int pts;
    private int reb;
    private int ast;
    private int stl;
    private int blk;
    // Add other stat fields as necessary

    // Constructor, getters, and setters
    public PlayerStatDTO(String date, int pts , int reb, int ast, int stl, int blk) {
        this.date = date;
        this.pts = pts;
        this.reb = reb;
        this.ast = ast;
        this.stl = stl;
        this.blk = blk;
    }

    // Getters and setters
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getPts() {
        return pts;
    }

    public void setPts(int pts) {
        this.pts = pts;
    }

    public int getReb() {
        return reb;
    }

    public void setReb(int reb) {
        this.reb = reb;
    }

    public int getAst() {
        return ast;
    }

    public void setAst(int ast) {
        this.ast = ast;
    }

    public int getStl() {
        return stl;
    }

    public void setStl(int stl) {
        this.stl = stl;
    }

    public int getBlk() {
        return blk;
    }

    public void setBlk(int blk) {
        this.blk = blk;
    }
}
