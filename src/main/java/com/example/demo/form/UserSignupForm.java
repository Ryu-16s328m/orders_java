package com.example.demo.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserSignupForm {
	
	@NotBlank(message = "{blankMessage}")
	@Size(max = 12 , message = "{userName}")
	private String lastName;
	@NotBlank(message = "{blankMessage}")
	@Size(max = 12 , message = "{userName}")
	private String firstName;
	@NotBlank(message = "{blankMessage}")
	private String email;
	@NotBlank(message = "{blankMessage}")
	private String password;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getFullName() {
		return this.lastName + this.firstName;
	}
	
}
