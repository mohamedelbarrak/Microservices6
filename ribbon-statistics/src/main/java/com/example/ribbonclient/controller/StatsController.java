package com.example.ribbonclient.controller;

import com.example.ribbonclient.model.MatchService;
import com.example.ribbonclient.model.PlayerService;
import com.example.ribbonclient.model.TeamService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;


@Api(tags = "Stats API")
@RestController
@Service
public class StatsController {

	private List<TeamService> teamList;
	private List<PlayerService> playerList;

	private List<MatchService> matchList;

	public StatsController() {
		teamList = new ArrayList<>();
		teamList.add(new TeamService("1", "team1", List.of("1", "2"), List.of("player1", "player2", "player3", "player4", "player5", "player6", "player7", "player8", "player9", "player10", "player11")));
		teamList.add(new TeamService("2", "team2", List.of("2"), List.of("player1", "player2", "player3", "player4", "player5", "player6", "player7", "player8", "player9", "player10", "player11")));
		teamList.add(new TeamService("3", "team3", List.of("2"), List.of("player1", "player2", "player3", "player4", "player5", "player6", "player7", "player8", "player9", "player10", "player11")));


		playerList = new ArrayList<>();
		playerList.add(new PlayerService("player1", "mohamed", "Team1", Arrays.asList("1", "2", "3", "4")));
		playerList.add(new PlayerService("player2", "amine", "Team1", Arrays.asList("1")));
		playerList.add(new PlayerService("player3", "mohamed", "Team1", Arrays.asList("1", "2")));
		playerList.add(new PlayerService("player4", "amine", "Team3", Arrays.asList("1")));
		playerList.add(new PlayerService("player5", "mohamed", "Team1", Arrays.asList("1", "2", "3", "4")));
		playerList.add(new PlayerService("player6", "amine", "Team1", Arrays.asList("1")));
		playerList.add(new PlayerService("player7", "mohamed", "Team1", Arrays.asList("1", "2")));
		playerList.add(new PlayerService("player8", "amine", "Team1", Arrays.asList("1")));
		playerList.add(new PlayerService("player9", "mohamed", "Team1", Arrays.asList("1", "2")));
		playerList.add(new PlayerService("player10", "amine", "Team1", Arrays.asList("1")));
		playerList.add(new PlayerService("player11", "mohamed", "Team1", Arrays.asList("1", "2")));

		matchList = new ArrayList<>();
		matchList.add(new MatchService("1", "Team A", "Team B", "Arbitre 1", "2", "1"));
		matchList.add(new MatchService("2", "Team C", "Team D", "Arbitre 2", "0", "3"));
		matchList.add(new MatchService("3", "Team A", "Team B", "Arbitre 1", "2", "1"));
		matchList.add(new MatchService("4", "Team C", "Team D", "Arbitre 2", "0", "3"));
	}




	@Autowired
	RestTemplate restTemplate;


	@ApiOperation(value = "Get Team Statistics", response = List.class)
	@ApiResponses(value = {
			@ApiResponse(code = 401, message = "You are not authorized to view the Statistics of Team"),
			@ApiResponse(code = 403, message = "Access to the Statistics Teams is forbidden"),
			@ApiResponse(code = 404, message = "The Statistics Teams were not found")
	})
	@GetMapping("/team-stats/{teamId}")
	public String getTeamStatisticsById(@PathVariable String teamId) {
		return getTeamStatistics(teamId);
	}


	@ApiOperation(value = "Get Player Statistics", response = List.class)
	@ApiResponses(value = {
			@ApiResponse(code = 401, message = "You are not authorized to view the Statistics of Player"),
			@ApiResponse(code = 403, message = "Access to the Statistics Player is forbidden"),
			@ApiResponse(code = 404, message = "The Statistics Player were not found")
	})
	@GetMapping("/player-stats/{playerId}")
	public String getPlayerStatisticsById(@PathVariable String playerId) {
        return  getPlayerStatistics(playerId);
	}


	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}


	public String getTeamStatistics(String teamId) {
		StringBuilder statistics = new StringBuilder();

		// Recherche de l'équipe dans la liste des équipes
		TeamService team = null;
		for (TeamService t : teamList) {
			if (t.getId().equals(teamId)) {
				team = t;
				break;
			}
		}

		if (team != null) {
			statistics.append("Team Statistics for Team ID ").append(teamId).append(" (").append(team.getName()).append("):\n\n");

			// Collecter les statistiques pour chaque joueur de l'équipe
			for (String playerId : team.getPlayers()) {
				PlayerService player = getPlayerById(playerId);
				if (player != null) {
					List<String> matchesPlayed = player.getMatch_played();
					int totalMatchesPlayed = matchesPlayed.size();
					int totalWins = countWins(matchesPlayed);

					statistics.append("Player ID: ").append(player.getId())
							.append(", Name: ").append(player.getName())
							.append(", Matches Played: ").append(totalMatchesPlayed)
							.append(", Wins: ").append(totalWins)
							.append("\n");
				}
			}
		} else {
			statistics.append("Team with ID ").append(teamId).append(" not found.");
		}

		return statistics.toString();
	}

	private PlayerService getPlayerById(String playerId) {
		for (PlayerService player : playerList) {
			if (player.getId().equals(playerId)) {
				return player;
			}
		}
		return null;
	}

	private int countWins(List<String> matchesPlayed) {
		int count = 0;
		for (String matchId : matchesPlayed) {
			for (MatchService match : matchList) {
				if (match.getId().equals(matchId) && "1".equals(match.getWinner())) {
					count++;
					break;
				}
			}
		}
		return count;
	}





	public String getPlayerStatistics(String playerId) {
		StringBuilder statistics = new StringBuilder();

		// Recherche du joueur dans la liste des joueurs
		PlayerService player = getPlayerById(playerId);

		if (player != null) {
			statistics.append("Player Statistics for Player ID ").append(playerId).append(" (").append(player.getName()).append("):\n");

			// Collecter les statistiques pour le joueur spécifié
			List<String> matchesPlayed = player.getMatch_played();
			int totalMatchesPlayed = matchesPlayed.size();
			int totalWins = countWins(matchesPlayed);

			statistics.append("Matches Played: ").append(totalMatchesPlayed)
					.append(", Wins matchs: ").append(totalWins)
					.append("\n");
		} else {
			statistics.append("Player with ID ").append(playerId).append(" not found.");
		}

		return statistics.toString();
	}
}
