package com.example.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * ユーザーの詳細を表示するコントローラー.
 * 
 * @author oyamadakenji
 *
 */
@Controller
@RequestMapping("/user-detail")
public class UserDetailController {

	/**
	 * ユーザー詳細画面へ.
	 * 
	 * @return ユーザー詳細画面
	 */
	@RequestMapping("/to-user-detail")
	public String toUserDetail() {
		return "user/user_detail";
	}
}
