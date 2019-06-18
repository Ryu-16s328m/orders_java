package com.example.demo.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.service.UserService;


@EnableWebSecurity
public class UserSecurity extends WebSecurityConfigurerAdapter {
//		String hashedPass = passwordEncoder.encode("test");	//encode()メソッドでHash化
//		System.out.println(hashedPass);
//	@Autowired
//	PasswordEncoder passwordEncoder;
	
	@Autowired
	UserService userservice;
	
	@Override
	protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
//		String hashedPass = passwordEncoder.encode("test");	//encode()メソッドでHash化
//		System.out.println(hashedPass);
		// 認証するユーザーを設定する(今回はServiceで)
		authenticationManagerBuilder.userDetailsService(this.userservice)
				// PasswordEncoderを使用しハッシュ化した値でパスワード認証を行う
				.passwordEncoder(passwordEncoder());
	}
	
	@Override
	protected void configure(HttpSecurity httpSecurity)throws Exception{
		httpSecurity
			.csrf()
			.disable()
			.formLogin()
			.loginPage("/users/login")
			.defaultSuccessUrl("/orders" , true)
			.failureUrl("/login-error")
			.permitAll();
		httpSecurity
			.authorizeRequests()
			.antMatchers("/css/**","/users/signup","/users/signupConfirm","/users/backToSignup" , "/users/doSignup")
			.permitAll()
			.anyRequest()
			.authenticated();
		httpSecurity
			.logout().logoutSuccessUrl("/users/login")
			.permitAll();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}