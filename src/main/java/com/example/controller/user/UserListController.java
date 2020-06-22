package com.example.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * ユーザーの一覧を表示するコントローラー.
 * 
 * @author oyamadakenji
 *
 */
@Controller
@RequestMapping("/user-list")
public class UserListController {

	/**
	 * ユーザー一覧画面へ.
	 * 
	 * @return ユーザー一覧画面
	 */
	@RequestMapping("/to-user-list")
	public String toUserList() {
		return "user/user_list";
	}
}
