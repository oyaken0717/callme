package com.example.controller.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.User;
import com.example.service.user.UserGroupListService;

/**
 * グループに参加しているユーザーの一覧を表示するコントローラー.
 * 
 * @author oyamadakenji
 *
 */
@Controller
@RequestMapping("/user-group-join-list")
public class UserGroupJoinListController {

	@Autowired
	private UserGroupListService userGroupListService;
	
	/**
	 * グループごとのユーザー一覧画面へ.
	 * 
	 * @return グループごとのユーザー一覧画面
	 */
	@RequestMapping("/to-user-group-list")
	public String toUserGroupList(Integer groupId,Model model) {
		List<User> userList = userGroupListService.findByGroupId(groupId);
		model.addAttribute("userList",userList);
		return "user/user_group_list";
	}
}
