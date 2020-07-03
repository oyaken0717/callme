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
	 * プロフィール画面へ.
	 * 
	 * @return プロフィール詳細画面
	 */
	@RequestMapping("/to-user-profile-detail")
	public String toUserProfileDetail(@AuthenticationPrincipal LoginUser loginUser,Model model) {
		User user = userDetailService.load(loginUser.getUser().getUserId());
		model.addAttribute("user", user);
		return "user/user_detail";
	}

	/**
	 * ユーザー詳細画面へ
	 * 
	 * @param userId 指定されたユーザーのid
	 * @param model モデル
	 * @return ユーザー詳細画面
	 */
	@RequestMapping("/to-user-detail")
	public String toUserDetail(Integer userId,Model model) {
		User user = userDetailService.load(userId);
		model.addAttribute("user", user);
		return "user/user_detail";
	}

}
