package com.example.demo.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.demo.entity.UsersEntity;
import com.example.demo.repository.UserRepository;
import com.example.demo.session.LoginSession;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	LoginSession session;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		if (StringUtils.isEmpty(email)) {
			throw new UsernameNotFoundException("メールアドレスを入力して下さい");
		}
 
		// usernameを元にDBからアカウント情報が取得できたら、アカウント情報を生成
		List<UsersEntity> accounts = userRepository.findAll();
		UsersEntity account = userRepository.findByEmail(email);
		if (account == null) {
			throw new UsernameNotFoundException("メールアドレスが違うよ");
		}
		session.setUserId(account.getUserId());
		session.setFirstName(account.getFirstName());
		session.setLastName(account.getLastName());
		session.setRegistDate(account.getRegistDate());
		session.setUpdateDate(account.getUpdateDate());
		session.setEmail(account.getEmail());
		session.setPassword(account.getPassword());
		session.setLoginMissCount(account.getLoginMissCount());
		
		session.setLoginDate(new Timestamp(System.currentTimeMillis()));
		
		userRepository.registLoginDate(session.getUserId(), session.getLoginDate());
		
		return account;
	}
	
	public void doUpdate(int userId , String lastName , String firstName , String email) {
		session.setLastName(lastName);
		session.setFirstName(firstName);
		session.setEmail(email);
		session.setUpdateDate(new Timestamp(System.currentTimeMillis()));
		userRepository.doUpdate(userId, lastName, firstName, email , session.getUpdateDate());
	}
	
	public void doSignup(String lastName , String firstName , String email , String password) {
		userRepository.doSignup(lastName, firstName, email, password);
	}


}
