package com.example.ribbonclient.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Details about the match")
public class MatchService {

    @ApiModelProperty(notes = "The id of the Match", name = "id", required = true)
    private String id;

    @ApiModelProperty(notes = "The team_one of the Match", name = "team_one", required = true)
    private String team_one;

    @ApiModelProperty(notes = "The team_two of the Match", name = "team_two", required = true)
    private String team_two;

    @ApiModelProperty(notes = "The referee of the Match", name = "arbitre")
    private String arbitre;

    @ApiModelProperty(notes = "Goals scored by team one", name = "team_one_buts")
    private String team_one_buts;

    @ApiModelProperty(notes = "Goals scored by team two", name = "team_two_buts")
    private String team_two_buts;

    public MatchService() {
    }

    public MatchService(String id, String team_one, String team_two, String arbitre, String team_one_buts, String team_two_buts) {
        this.id = id;
        this.team_one = team_one;
        this.team_two = team_two;
        this.arbitre = arbitre;
        this.team_one_buts = team_one_buts;
        this.team_two_buts = team_two_buts;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTeam_one() {
        return team_one;
    }

    public void setTeam_one(String team_one) {
        this.team_one = team_one;
    }

    public String getTeam_two() {
        return team_two;
    }

    public void setTeam_two(String team_two) {
        this.team_two = team_two;
    }

    public String getArbitre() {
        return arbitre;
    }

    public void setArbitre(String arbitre) {
        this.arbitre = arbitre;
    }

    public String getTeam_one_buts() {
        return team_one_buts;
    }

    public void setTeam_one_buts(String team_one_buts) {
        this.team_one_buts = team_one_buts;
    }

    public String getTeam_two_buts() {
        return team_two_buts;
    }

    public void setTeam_two_buts(String team_two_buts) {
        this.team_two_buts = team_two_buts;
    }

    public String getWinner() {
        int goalsTeamOne = Integer.parseInt(team_one_buts );
        int goalsTeamTwo = Integer.parseInt(team_two_buts );
        int winer = Integer.max(goalsTeamOne, goalsTeamTwo);
        if (Integer.parseInt(team_one_buts) == winer){
            return team_one;
        }else {
            return team_two;
        }
    }
}
