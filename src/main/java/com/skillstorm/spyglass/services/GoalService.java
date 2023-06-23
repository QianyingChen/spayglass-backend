package com.skillstorm.spyglass.services;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skillstorm.spyglass.dtos.GoalDto;
import com.skillstorm.spyglass.models.Goal;
import com.skillstorm.spyglass.repositories.GoalRepository;

@Service
public class GoalService {
	
	@Autowired
	private GoalRepository goalRepository;
	
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
		Goal goal = new Goal(goalData.getId(), goalData.getUserId(),goalData.getName(),goalData.getDescription(), goalData.getPicture(), goalData.getTargetDate(), goalData.getTargetAmount(), goalData.getCurrentAmount(), goalData.getSavedAmount()) ;
		return goalRepository.save(goal).toDto();
	}

	public GoalDto updateGoal(long id, @Valid GoalDto goalData) {
		Goal goal = new Goal(goalData.getId(), goalData.getUserId(),goalData.getName(),goalData.getDescription(), goalData.getPicture(), goalData.getTargetDate(), goalData.getTargetAmount(), goalData.getCurrentAmount(), goalData.getSavedAmount());
		goal.setId(id);
		return goalRepository.save(goal).toDto();
	}

	public void deleteGoal(long id) {
		goalRepository.deleteById(id);
	}
	

}
