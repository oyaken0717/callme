package com.example.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * ユーザーのログイン時のコントローラー.
 * 
 * @author oyamadakenji
 *
 */
@Controller
@RequestMapping("/user-login")
public class UserLoginController {
		
	/**
	 * ユーザーログイン画面へ.
	 * 
	 * @return ログイン画面
	 */
	@RequestMapping("/to-login")
	public String toLogin(Model model,@RequestParam(required = false) String error) {
		if (error != null) {
			model.addAttribute("errorMessage","メールアドレス又はパスワードが間違っています。");
		}
		return "user/login";
	}	

}
