package com.example.controller.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.User;
import com.example.service.user.UserListService;

/**
 * ユーザーの一覧を表示するコントローラー.
 * 
 * @author oyamadakenji
 *
 */
@Controller
@RequestMapping("/user-list")
public class UserListController {

	@Autowired
	private UserListService userListService;
	
	/**
	 * ユーザー一覧画面へ.
	 * 
	 * @return ユーザー一覧画面
	 */
	@RequestMapping("/to-user-list")
	public String toUserList(Model model) {
		List<User> userList = userListService.findAll();
		model.addAttribute("userList",userList);
		return "user/user_list";
	}
}
