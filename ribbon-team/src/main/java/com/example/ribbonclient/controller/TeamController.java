package com.example.ribbonclient.controller;

import com.example.ribbonclient.model.TeamService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@Api(tags = "TeamService API")
@RequestMapping("/teams")
public class TeamController {

	private List<TeamService> teamList;

	public TeamController() {
		teamList = new ArrayList<>();
		// Ajouter des éléments à teamList lors de la création du contrôleur
		teamList.add(new TeamService("1", "team 1", List.of("1"), List.of("player1", "player2", "player3", "player4", "player5", "player6", "player7", "player8", "player9", "player10", "player11")));
		teamList.add(new TeamService("2", "team 2", List.of("2"), List.of("player1", "player2", "player3", "player4", "player5", "player6", "player7", "player8", "player9", "player10", "player11")));
	}

	@ApiOperation(value = "Get Team", response = List.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully retrieved the list of Team"),
			@ApiResponse(code = 401, message = "You are not authorized to view the Team"),
			@ApiResponse(code = 403, message = "Access to the Teams is forbidden"),
			@ApiResponse(code = 404, message = "The Teams were not found")
	})
	@GetMapping("/{id}")
	public ResponseEntity<TeamService> getTeamById(@PathVariable String id) {
		// Recherche du joueur par son identifiant
		for (TeamService team : teamList) {
			if (team.getId().equals(id)) {
				return new ResponseEntity<>(team, HttpStatus.OK);
			}
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@ApiOperation(value = "add team", response = List.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 201, message = "team Created"),
			@ApiResponse(code = 401, message = "You are not authorized to post the teams"),
			@ApiResponse(code = 403, message = "Access forbidden"),
			@ApiResponse(code = 404, message = "Not found")
	})
	@PostMapping
	public ResponseEntity<Void> addTeam(@RequestBody TeamService newTeam) {
		// Ajout du nouveau joueur à la liste
		teamList.add(newTeam);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@ApiOperation(value = "update team", response = List.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 201, message = "team Created"),
			@ApiResponse(code = 401, message = "You are not authorized to post the teams"),
			@ApiResponse(code = 403, message = "Access forbidden"),
			@ApiResponse(code = 404, message = "Not found")
	})
	@PutMapping("/{id}")
	public ResponseEntity<Void> updateTeam(@PathVariable String id, @RequestBody TeamService updatedTeam) {
		// Recherche du joueur par son identifiant
		for (TeamService team : teamList) {
			if (team.getId().equals(id)) {
				// Mise à jour des informations du joueur
				team.setName(updatedTeam.getName());
				team.setMatchsplayed(updatedTeam.getMatchsplayed());
				team.setPlayers(updatedTeam.getPlayers());
				return new ResponseEntity<>(HttpStatus.OK);
			}
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@ApiOperation(value = "delete team", response = List.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 201, message = "team Deleted"),
			@ApiResponse(code = 401, message = "You are not authorized"),
			@ApiResponse(code = 403, message = "Access forbidden")
	})
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteTeam(@PathVariable String id) {
		// Recherche du joueur par son identifiant
		for (TeamService team : teamList) {
			if (team.getId().equals(id)) {
				// Suppression du joueur
				teamList.remove(team);
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@ApiOperation(value = "Get all teams", response = List.class)
	@ApiResponses(value = {
			@ApiResponse(code = 401, message = "You are not authorized to view the matches"),
			@ApiResponse(code = 403, message = "Access to the matches is forbidden"),
			@ApiResponse(code = 404, message = "The matches were not found")
	})
	@GetMapping
	public ResponseEntity<List<TeamService>> getAllTteams() {
		// Renvoie la liste complète des joueurs
		return new ResponseEntity<>(teamList, HttpStatus.OK);
	}
}
