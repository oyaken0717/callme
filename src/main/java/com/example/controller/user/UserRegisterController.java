package com.example.controller.user;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.User;
import com.example.form.UserForm;
import com.example.service.user.UserRegisterService;

/**
 * @author oyamadakenji
 * 
 *         user情報を登録するコントローラー.
 *
 */
@Controller
@RequestMapping("/user-register")
public class UserRegisterController {

	@ModelAttribute
	public UserForm setUpForm() {
		return new UserForm();
	}

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
	public String insert(@Validated UserForm form, BindingResult result) {

		//■ 入力されたEmailがすでに存在していないか確認する。		
		
		
		if (result.hasErrors()) {
			return toUserRegister();
		}

		User user = new User();
		BeanUtils.copyProperties(form, user);
		user.setVersion(0);
		userRegisterService.insert(user);

		return "redirect:/user-login/to-login";

	}

}
