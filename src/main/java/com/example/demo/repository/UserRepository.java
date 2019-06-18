package com.example.demo.repository;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.UsersEntity;
import com.example.demo.interFace.UserInterface;

@Repository
public class UserRepository {
	
	@Autowired
	UserInterface userInterface;
	
	public List<UsersEntity> findAll(){
		List<UsersEntity> users = userInterface.findAll();
		return users;
	}
 
	public UsersEntity findByEmail(String email) {
		return userInterface.findByEmail(email);
	}
	
	public void registLoginDate(int userId , Timestamp loginDate) {
		UsersEntity userEntity = userInterface.findByUserId(userId);
		userEntity.setLoginDate(loginDate);
		userInterface.save(userEntity);
	}
	
	public void doUpdate(int userId , String lastName , String firstName , String email , Timestamp updateDate) {
		UsersEntity userEntity = userInterface.findByUserId(userId);
		userEntity.setLastName(lastName);
		userEntity.setFirstName(firstName);
		userEntity.setEmail(email);
		userEntity.setUpdateDate(updateDate);
		userInterface.save(userEntity);
	}
	
	public void doSignup(String lastName , String firstName , String email , String password) {
		UsersEntity userEntity = new UsersEntity();
		userEntity.setLastName(lastName);
		userEntity.setFirstName(firstName);
		userEntity.setEmail(email);
		userEntity.setPassword(password);
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		userEntity.setLoginDate(timestamp);
		userEntity.setRegistDate(timestamp);
		userEntity.setUpdateDate(timestamp);
		userEntity.setLoginMissCount(0);
		userInterface.save(userEntity);
		
	}
}
