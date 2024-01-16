package com.example.ribbonclient.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel(description = "Details about the player")
public class PlayerService {

    @ApiModelProperty(notes = "The id of the Player", name="id", required = true)
    private String id;

    @ApiModelProperty(notes = "The name of the Player", name="name", required = true)
    private String name;

    @ApiModelProperty(notes = "The team of the Player", name="team")
    private String team;

    @ApiModelProperty(notes = "List of match IDs played by the player", name="match_played")
    private List<String> match_played;

    public PlayerService() {
    }

    public PlayerService(String id, String name, String team, List<String> match_played) {
        this.id = id;
        this.name = name;
        this.team = team;
        this.match_played = match_played;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public List<String> getMatch_played() {
        return match_played;
    }

    public void setMatch_played(List<String> match_played) {
        this.match_played = match_played;
    }
}
