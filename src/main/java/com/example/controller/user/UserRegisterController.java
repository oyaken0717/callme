package com.example.controller.user;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.User;
import com.example.form.user.UserForm;
import com.example.service.user.UserRegisterService;

/**
 * @author oyamadakenji
 * 
 * user情報を登録するコントローラー.
 *
 */
@Controller
@RequestMapping("/user-register")
public class UserRegisterController {
	
	@Autowired
	private UserRegisterService userRegisterService;
	
	/**
	 * 
	 * ユーザー登録画面へ.
	 * 
	 * @return ユーザー登録画面
	 */
	@RequestMapping("/to-user-register")
	public String toUserRegister() {
		return "user/user_register";
	}
	
	/**
	 * 
	 * ユーザー情報の登録をする.
	 * 
	 */
	@RequestMapping("/insert")
	public String insert(UserForm form) {
	
		User user = new User();
		BeanUtils.copyProperties(form, user);
		userRegisterService.insert(user);
	
		return "user/login";

	}

}
