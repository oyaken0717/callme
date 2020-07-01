package com.example.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.LoginUser;
import com.example.domain.User;
import com.example.service.user.UserDetailService;

/**
 * ユーザーの詳細を表示するコントローラー.
 * 
 * @author oyamadakenji
 *
 */
@Controller
@RequestMapping("/user-detail")
public class UserDetailController {

	@Autowired
	private UserDetailService userDetailService;
	
	/**
	 * ユーザー詳細画面へ.
	 * 
	 * @return ユーザー詳細画面
	 */
	@RequestMapping("/to-user-detail")
	public String toUserDetail(@AuthenticationPrincipal LoginUser loginUser,Model model) {
		User user = userDetailService.load(loginUser.getUser().getUserId());
		model.addAttribute("user", user);
		return "user/user_detail";
	}
}
