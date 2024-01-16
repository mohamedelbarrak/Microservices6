package com.example.ribbonserver.controller;

import com.example.ribbonserver.model.MatchService;
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
@Api(tags = "MatchService API")
@RequestMapping("/matches")
public class MatchController {

	private List<MatchService> matchList;

	public MatchController() {
		matchList = new ArrayList<>();
		matchList.add(new MatchService("1", "Team A", "Team B", "Arbitre 1", "2", "1"));
		matchList.add(new MatchService("2", "Team C", "Team D", "Arbitre 2", "0", "3"));
	}

	@ApiOperation(value = "Get matche", response = List.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully retrieved the list of matche"),
			@ApiResponse(code = 401, message = "You are not authorized to view the matche"),
			@ApiResponse(code = 403, message = "Access to the matches is forbidden"),
			@ApiResponse(code = 404, message = "The matches were not found")
	})
	@GetMapping("/{id}")
	public ResponseEntity<MatchService> getMatchById(@PathVariable String id) {
		for (MatchService match : matchList) {
			if (match.getId().equals(id)) {
				return new ResponseEntity<>(match, HttpStatus.OK);
			}
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@ApiOperation(value = "add Match", response = List.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 201, message = "match Created"),
			@ApiResponse(code = 401, message = "You are not authorized to post the matches"),
			@ApiResponse(code = 403, message = "Access forbidden"),
			@ApiResponse(code = 404, message = "Not found")
	})
	@PostMapping
	public ResponseEntity<Void> addMatch(@RequestBody MatchService newMatch) {
		matchList.add(newMatch);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@ApiOperation(value = "update Match", response = List.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 201, message = "match Created"),
			@ApiResponse(code = 401, message = "You are not authorized to post the matches"),
			@ApiResponse(code = 403, message = "Access forbidden"),
			@ApiResponse(code = 404, message = "Not found")
	})
	@PutMapping("/{id}")
	public ResponseEntity<Void> updateMatch(@PathVariable String id, @RequestBody MatchService updatedMatch) {
		for (MatchService match : matchList) {
			if (match.getId().equals(id)) {
				match.setTeam_one(updatedMatch.getTeam_one());
				match.setTeam_two(updatedMatch.getTeam_two());
				match.setArbitre(updatedMatch.getArbitre());
				match.setTeam_one_buts(updatedMatch.getTeam_one_buts());
				match.setTeam_two_buts(updatedMatch.getTeam_two_buts());
				return new ResponseEntity<>(HttpStatus.OK);
			}
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@ApiOperation(value = "delete Match", response = List.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 201, message = "match Deleted"),
			@ApiResponse(code = 401, message = "You are not authorized"),
			@ApiResponse(code = 403, message = "Access forbidden")
	})
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteMatch(@PathVariable String id) {
		for (MatchService match : matchList) {
			if (match.getId().equals(id)) {
				matchList.remove(match);
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@ApiOperation(value = "Get all matches", response = List.class)
	@ApiResponses(value = {
			@ApiResponse(code = 401, message = "You are not authorized to view the matches"),
			@ApiResponse(code = 403, message = "Access to the matches is forbidden"),
			@ApiResponse(code = 404, message = "The matches were not found")
	})
	@GetMapping
	public ResponseEntity<List<MatchService>> getAllMatches() {
		return new ResponseEntity<>(matchList, HttpStatus.OK);
	}
}
