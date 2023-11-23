package com.task.task.model;

public class TaskDto {
	private Long id;
	private String name;
	private String company;
	private String number;
	private String email;

	public TaskDto() {
	}

	public TaskDto(Long id, String name, String company, String number, String email) {
		this.id = id;
		this.name = name;
		this.company = company;
		this.number = number;
		this.email = email;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}