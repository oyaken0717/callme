package com.example.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * ユーザー情報を編集するコントローラー.
 * 
 * @author oyamadakenji
 *
 */
@Controller
@RequestMapping("/user-edit")
public class UserEditController {

	/**
	 * ユーザー編集画面へ.
	 * 
	 * @return ユーザー編集画面
	 */
	@RequestMapping("/to-user-edit")
	public String toUserEdit() {
		return "user/user_edit";
	}
}
