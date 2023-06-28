package com.skillstorm.spyglass.dtos;

import java.time.LocalDate;
import java.util.Objects;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;


public class GoalDto {
	private Long id;
	private String userId;
	@NotBlank(message = "Name is required")
	private String name;
	private String description;
	private String picture;
	@NotNull(message = "Target date is required")
	private LocalDate targetDate;
	@Positive(message = "Target amount should be positive")
	private double targetAmount;
	private double currentAmount;

	 
	public GoalDto() {
		super();
	}

	public GoalDto(Long id, String userId, String name, String description, String picture, LocalDate targetDate,
			double targetAmount, double currentAmount) {
		super();
		this.id = id;
		this.userId = userId;
		this.name = name;
		this.description = description;
		this.picture = picture;
		this.targetDate = targetDate;
		this.targetAmount = targetAmount;
		this.currentAmount = currentAmount;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public LocalDate getTargetDate() {
		return targetDate;
	}

	public void setTargetDate(LocalDate targetDate) {
		this.targetDate = targetDate;
	}

	public double getTargetAmount() {
		return targetAmount;
	}

	public void setTargetAmount(double targetAmount) {
		this.targetAmount = targetAmount;
	}

	public double getCurrentAmount() {
		return currentAmount;
	}

	public void setCurrentAmount(double currentAmount) {
		this.currentAmount = currentAmount;
	}


	@Override
	public int hashCode() {
		return Objects.hash(currentAmount, description, id, name, picture, targetAmount, targetDate,
				userId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GoalDto other = (GoalDto) obj;
		return Double.doubleToLongBits(currentAmount) == Double.doubleToLongBits(other.currentAmount)
				&& Objects.equals(description, other.description) && Objects.equals(id, other.id)
				&& Objects.equals(name, other.name) && Objects.equals(picture, other.picture)
				&& Double.doubleToLongBits(targetAmount) == Double.doubleToLongBits(other.targetAmount)
				&& Objects.equals(targetDate, other.targetDate) && Objects.equals(userId, other.userId);
	}

	@Override
	public String toString() {
		return "GoalDto [id=" + id + ", userId=" + userId + ", name=" + name + ", description=" + description
				+ ", picture=" + picture + ", targetDate=" + targetDate + ", targetAmount=" + targetAmount
				+ ", currentAmount=" + currentAmount + ", savedAmount=" + "]";
	}

	
	 

}
