package com.example.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.LoginUser;
import com.example.service.user.UserDeleteService;

/**
 * ユーザーの情報を削除するコントローラー.
 * 
 * @author oyamadakenji
 *
 */
@Controller
@RequestMapping("/user-delete")
public class UserDeleteController {

	@Autowired
	private UserDeleteService userDeleteService;
	
	/**
	 * ユーザー削除
	 *
	 * @param loginUser ログインユーザー 
	 * @return ログイン画面
	 */
	@RequestMapping("/user-delete")
	public String delete(@AuthenticationPrincipal LoginUser loginUser) {
		userDeleteService.delete(loginUser.getUser().getUserId());
		return "user/user_detail";
	}

}
