package com.example.ribbonclient.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel(description = "Details about the student")
public class TeamService {

    @ApiModelProperty(notes = "The id of the Team", name = "id", required = true)
    private String id;

    @ApiModelProperty(notes = "The name of the Team", name = "name", required = true)
    private String name;

    @ApiModelProperty(notes = "List of the Match IDs", name = "matchName")
    private List<String> matchsplayed;

    @ApiModelProperty(notes = "List of player IDs", name = "players")
    private List<String> players;

    public TeamService() {
    }

    public TeamService(String id, String name, List<String> matchsplayed, List<String> players) {
        this.id = id;
        this.name = name;
        this.matchsplayed = matchsplayed;
        this.players = players;
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

    public List<String> getMatchsplayed() {
        return matchsplayed;
    }

    public void setMatchsplayed(List<String> matchsplayed) {
        this.matchsplayed = matchsplayed;
    }

    public List<String> getPlayers() {
        return players;
    }

    public void setPlayers(List<String> players) {
        this.players = players;
    }
}
