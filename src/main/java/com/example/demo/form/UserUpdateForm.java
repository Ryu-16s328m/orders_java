package com.example.demo.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserUpdateForm {
	
	
	private int userId;
	@NotBlank(message = "{blankMessage}")
	@Size(max = 12 , message = "{userName}")
	private String lastName;
	@NotBlank(message = "{blankMessage}")
	@Size(max = 12 , message = "{userName}")
	private String firstName;
	@NotBlank(message = "{blankMessage}")
	private String email;
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}
