package com.example.controller.user;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.User;
import com.example.form.user.UserForm;
import com.example.service.user.UserLoginService;

/**
 * ユーザーのログイン時のコントローラー.
 * 
 * @author oyamadakenji
 *
 */
@Controller
@RequestMapping("/user-login")
public class UserLoginController {
	
	@Autowired
	private UserLoginService userLoginService;
	/**
	 * ユーザーログイン画面へ.
	 * 
	 * @return ログイン画面
	 */
	@RequestMapping("/to-login")
	public String toLogin() {
		
		return "user/login";
	}
	
	/**
	 * ユーザーログイン処理
	 * 
	 * @return 参加グループ一覧画面
	 */
	@RequestMapping("/login")
	public String login(UserForm form) {
		User user = new User();
		BeanUtils.copyProperties(form, user);
		
		user = userLoginService.findByEmail(user.getEmail());
		
		if (user != null) {
			return "redirect:/group-join-list/to-group-join-list";			
		}
		return "redirect:/user-login/to-login";
	}
	

}
