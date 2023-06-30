
package com.skillstorm.spyglass.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.MediaType;

import com.skillstorm.spyglass.dtos.GoalDto;
import com.skillstorm.spyglass.services.GoalService;
import org.springframework.http.HttpHeaders;

@RestController
@RequestMapping("/goals")
@CrossOrigin(allowCredentials = "true", originPatterns = {"http://localhost:5173", "http://qianying-project3-pipeline-bucket.s3-website-us-east-1.amazonaws.com/"})
public class GoalController {
	
	@Autowired
	private GoalService goalService;
	
	@GetMapping
	public List<GoalDto> getGoals(@AuthenticationPrincipal OAuth2User user){
		String userId = (String) user.getAttributes().get("sub");
		return goalService.getAllGoalsByUserId(userId);
	}
	
    @GetMapping("/{id}")
    public GoalDto getGoal(@PathVariable long id) {
        return goalService.getGoalById(id);
    }

	
	@PostMapping
	public ResponseEntity<GoalDto> createGoal(@Valid @RequestBody GoalDto goalData, @AuthenticationPrincipal OAuth2User user) {
		String userId = (String) user.getAttributes().get("sub");
		goalData.setUserId(userId);
		GoalDto createdGoal = goalService.createGoal(goalData);
		return new ResponseEntity<>(createdGoal, HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public GoalDto updateGoal(@PathVariable long id, @Valid @RequestBody GoalDto goalData) {
		goalData.setId(id);
		return goalService.updateGoal(id, goalData);
	}
	
	@DeleteMapping("/{id}")
	public void deleteGoal(@PathVariable long id) {
		goalService.deleteGoal(id);
	}
	
	 @PostMapping(path = "/{id}/upload", consumes = "multipart/form-data")
	public void uploadGoalImage(@RequestParam("image") MultipartFile image, @PathVariable long id, @AuthenticationPrincipal OAuth2User user) {
		goalService.uploadGoalImage(id, image);
	}

	@GetMapping("/{id}/goal-image")
	public ResponseEntity<byte[]> getGoalImage(@PathVariable("id") long id) {
		byte[] goalImage = goalService.getGoalImage(id);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.IMAGE_JPEG);
		return new ResponseEntity<>(goalImage, headers, HttpStatus.OK);
	}

}
