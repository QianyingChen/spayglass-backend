package com.skillstorm.spyglass.services;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.MediaType;

import com.skillstorm.spyglass.dtos.GoalDto;
import com.skillstorm.spyglass.exception.ResourceNotFoundException;
import com.skillstorm.spyglass.models.Goal;
import com.skillstorm.spyglass.repositories.GoalRepository;

@Service
public class GoalService {
	
	@Autowired
	private GoalRepository goalRepository;
	
	@Autowired
    private S3Service s3Service;

    @Value("${aws.s3.bucketName}")
    private String bucketName;
	
	public List<GoalDto> getAllGoals() {
		return goalRepository.findAll()
				.stream()
				.map(goal->goal.toDto())
				.collect(Collectors.toList());
	}
		
	public List<GoalDto> getAllGoalsByUserId(String userId) {
    return goalRepository.findAllByUserId(userId)
            .stream()
            .map(Goal::toDto)
            .collect(Collectors.toList());
	}
	
	public GoalDto getGoalById(long id) {
		return goalRepository.findById(id)
				.orElseThrow((()->new RuntimeException("Goal not found")))
				.toDto();
	}

	public GoalDto createGoal(@Valid GoalDto goalData) {
		Goal goal = new Goal(goalData.getId(), goalData.getUserId(),goalData.getName(),goalData.getDescription(), goalData.getPicture(), goalData.getStartDate(), goalData.getTargetDate(), goalData.getTargetAmount(), goalData.getCurrentAmount()) ;
		return goalRepository.save(goal).toDto();
	}

	public GoalDto updateGoal(long id, @Valid GoalDto goalData) {
		Goal goal = new Goal(goalData.getId(), goalData.getUserId(),goalData.getName(),goalData.getDescription(), goalData.getPicture(), goalData.getStartDate(), goalData.getTargetDate(), goalData.getTargetAmount(), goalData.getCurrentAmount());
		goal.setId(id);
		return goalRepository.save(goal).toDto();
	}

	public void deleteGoal(long id) {
		goalRepository.deleteById(id);
	}

	 public void uploadGoalImage(long id, MultipartFile image) {
        Goal goal = goalRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Goal not found"));

        String picture = UUID.randomUUID().toString();
        try {
            String contentType = MediaType.parseMediaType(image.getContentType()).toString(); // Get the content type from the MultipartFile

            s3Service.putObject(
                bucketName,
                "goal-images/%s/%s".formatted(id, picture),
                image.getBytes(),
                contentType // Pass the content type to the S3Service
            );
          } catch (IOException e) {
            throw new RuntimeException("Failed to upload goal image", e);
          }

        goal.setPicture(picture);
        goalRepository.save(goal);
    }
	 
	public byte[] getGoalImage(long id) {
    Goal goal = goalRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Goal not found"));

    if (Objects.isNull(goal.getPicture()) || goal.getPicture().trim().isEmpty()) {
        throw new ResourceNotFoundException("Goal image not found");
    }

    return s3Service.getObject(bucketName, String.format("goal-images/%s/%s", id, goal.getPicture()));
}
	
	
	

}
