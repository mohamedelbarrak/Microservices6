package com.example.ribbonclient.controller;

import com.example.ribbonclient.model.PlayerService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@Api(tags = "PlayerService API")
@RequestMapping("/players")
@Service
public class PlayerController {

	private List<PlayerService> playerList;

	public PlayerController() {
		playerList = new ArrayList<>();
		playerList.add(new PlayerService("1", "mohamed", "Team A", Arrays.asList("1", "2")));
		playerList.add(new PlayerService("2", "amine", "Team B", Arrays.asList("1")));
	}

	@HystrixCommand(fallbackMethod = "fallbackForGetStudentDetails")
	@ApiOperation(value = "Get Player", response = List.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully retrieved the list of Player"),
			@ApiResponse(code = 401, message = "You are not authorized to view the Player"),
			@ApiResponse(code = 403, message = "Access to the Players is forbidden"),
			@ApiResponse(code = 404, message = "The Players were not found")
	})
	@GetMapping("/{id}")
	public ResponseEntity<PlayerService> getPlayerById(@PathVariable String id) {
		for (PlayerService player : playerList) {
			if (player.getId().equals(id)) {
				return new ResponseEntity<>(player, HttpStatus.OK);
			}
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@ApiOperation(value = "add player", response = List.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 201, message = "Player Created"),
			@ApiResponse(code = 401, message = "You are not authorized to post the Players"),
			@ApiResponse(code = 403, message = "Access forbidden"),
			@ApiResponse(code = 404, message = "Not found")
	})
	@PostMapping
	public ResponseEntity<Void> addPlayer(@RequestBody PlayerService newPlayer) {
		playerList.add(newPlayer);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@ApiOperation(value = "update Player", response = List.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 201, message = "Player Created"),
			@ApiResponse(code = 401, message = "You are not authorized to post the Players"),
			@ApiResponse(code = 403, message = "Access forbidden"),
			@ApiResponse(code = 404, message = "Not found")
	})
	@PutMapping("/{id}")
	public ResponseEntity<Void> updatePlayer(@PathVariable String id, @RequestBody PlayerService updatedPlayer) {
		for (PlayerService player : playerList) {
			if (player.getId().equals(id)) {
				player.setName(updatedPlayer.getName());
				player.setTeam(updatedPlayer.getTeam());
				player.setMatch_played(updatedPlayer.getMatch_played());
				return new ResponseEntity<>(HttpStatus.OK);
			}
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@ApiOperation(value = "delete Player", response = List.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 201, message = "Player Deleted"),
			@ApiResponse(code = 401, message = "You are not authorized"),
			@ApiResponse(code = 403, message = "Access forbidden")
	})
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletePlayer(@PathVariable String id) {
		for (PlayerService player : playerList) {
			if (player.getId().equals(id)) {
				playerList.remove(player);
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@ApiOperation(value = "Get all Players", response = List.class)
	@ApiResponses(value = {
			@ApiResponse(code = 401, message = "You are not authorized to view the Players"),
			@ApiResponse(code = 403, message = "Access to the Players is forbidden"),
			@ApiResponse(code = 404, message = "The Players were not found")
	})
	@GetMapping
	public ResponseEntity<List<PlayerService>> getAllPlayers() {
		return new ResponseEntity<>(playerList, HttpStatus.OK);
	}

	private String fallbackForGetStudentDetails(String schoolName, String studentName) {
		return "Fallback response: Unable to get student details for schoolName=" + schoolName + " and studentName=" + studentName;
	}
}
