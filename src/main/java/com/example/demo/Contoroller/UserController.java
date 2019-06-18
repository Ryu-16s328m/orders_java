package com.example.demo.Contoroller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.form.UpdateForm;
import com.example.demo.form.UserSignupForm;
import com.example.demo.form.UserUpdateForm;
import com.example.demo.service.UserService;
import com.example.demo.session.LoginSession;

@Controller
public class UserController {
	
	@Autowired
	LoginSession session;
	
	@Autowired
	UserService userService;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@RequestMapping(value = "/login-error", method = RequestMethod.GET)                                                                
    public ModelAndView loginError(ModelAndView mav) {                                                                
        mav.addObject("isError", true);                                                            
        mav.setViewName("/login.html"); 
        return mav;                                                            
    }
	
	@RequestMapping(value = "/users/login", method = RequestMethod.GET)
	public String login() {
		return "/login.html";
	}
	
	@RequestMapping(value="/users/detail", method = RequestMethod.GET)
	public ModelAndView init() {
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.addObject("firstName", session.getFirstName());
		modelAndView.addObject("lastName", session.getLastName());
		modelAndView.addObject("registDate", session.getRegistDate());
		modelAndView.addObject("updateDate", session.getUpdateDate());
		modelAndView.addObject("email", session.getEmail());
		modelAndView.addObject("loginDate", session.getLoginDate());
		modelAndView.setViewName("/userDetail.html");
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/users/update" , method = RequestMethod.GET)
	public ModelAndView userUpdate(@ModelAttribute("form") UserUpdateForm form) {
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.addObject("firstName", session.getFirstName());
		modelAndView.addObject("lastName", session.getLastName());
		modelAndView.addObject("email", session.getEmail());
		modelAndView.setViewName("/userUpdate.html");
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/users/updateConfirm" , method = RequestMethod.GET)
	public ModelAndView updateConfirm(@ModelAttribute("form") @Valid UserUpdateForm form , BindingResult result) {
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.addObject("lastName", form.getLastName());
		modelAndView.addObject("firstName", form.getFirstName());
		modelAndView.addObject("email", form.getEmail());
		
		
		
		if (result.hasErrors()) {
			modelAndView.setViewName("/userUpdate.html");
		}else {
			modelAndView.setViewName("/userUpdateConfirm.html");
		}
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/users/doUpdate" , method = RequestMethod.POST)
	public String doUpdate(@ModelAttribute("form") UserUpdateForm form) {
		
		userService.doUpdate(form.getUserId(), form.getLastName(), form.getFirstName(), form.getEmail());
		
		
		
		return "redirect:/users/detail";
	}
	
	@RequestMapping(value = "/users/backToUserUpdate" , method = RequestMethod.POST)
	public ModelAndView backToUserUpdate(@ModelAttribute("form") UserUpdateForm form) {
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.addObject("lastName", form.getLastName());
		modelAndView.addObject("firstName", form.getFirstName());
		modelAndView.addObject("email", form.getEmail());
		modelAndView.setViewName("/userUpdate.html");
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/users/signup" , method = RequestMethod.GET)
	public ModelAndView signup(@ModelAttribute("form") UserSignupForm form) {
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.setViewName("/signup.html");
		modelAndView.addObject("lastName");
		modelAndView.addObject("firstName");
		modelAndView.addObject("email");
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/users/signupConfirm" , method = RequestMethod.POST)
	public ModelAndView signupConfirm(@ModelAttribute("form") @Valid UserSignupForm form , BindingResult result) {
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.addObject("name", form.getFullName());
		modelAndView.addObject("email" , form.getEmail());
		
		modelAndView.addObject("lastName" , form.getLastName());
		modelAndView.addObject("firstName" , form.getFirstName());
		modelAndView.addObject("password" , form.getPassword());
		
		
		if (result.hasErrors()) {
			modelAndView.setViewName("/signup.html");
		}else {
			modelAndView.setViewName("/signupConfirm.html");
		}
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/users/backToSignup" , method = RequestMethod.POST)
	public ModelAndView backToSignup(@ModelAttribute("form") UserSignupForm form) {
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.addObject("lastName" , form.getLastName());
		modelAndView.addObject("firstName" , form.getFirstName());
		modelAndView.addObject("email" , form.getEmail());
		modelAndView.setViewName("/signup.html");
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/users/doSignup" , method = RequestMethod.POST)
	public String doSignup(UserSignupForm form , BindingResult result) {
		
		userService.doSignup(form.getLastName(), form.getFirstName(), form.getEmail(), passwordEncoder.encode(form.getPassword()));
		
		return "redirect:/users/login";
	}
}
