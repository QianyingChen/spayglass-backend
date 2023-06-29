package com.skillstorm.spyglass.repositories;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.skillstorm.spyglass.dtos.GoalDto;
import com.skillstorm.spyglass.models.Goal;

@Repository
public interface GoalRepository extends JpaRepository<Goal, Long> {

	List<Goal> findAllByUserId(String userId);
	
}
