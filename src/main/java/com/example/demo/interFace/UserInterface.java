package com.example.demo.interFace;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.UsersEntity;

public interface UserInterface extends JpaRepository<UsersEntity, Integer> {
	public UsersEntity findByEmail(String email);
	public UsersEntity findByUserId(int userId);
}