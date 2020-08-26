package com.example.controller.group;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.LoginUser;
import com.example.service.group.GroupDeleteService;

/**
 * グループの情報を削除するコントローラー.
 * 
 * @author oyamadakenji
 *
 */
@Controller
@RequestMapping("/group-delete")
public class GroupDeleteController {

	@Autowired
	private GroupDeleteService groupDeleteService;
	
	/**
	 * グループ削除
	 *
	 * @param loginUser ログインユーザー 
	 * @return グループ一覧画面
	 */
	@RequestMapping("/group-delete")
	public String groupDelete(Integer groupId) {
		groupDeleteService.delete(groupId);
		return "redirect:/group-join-list/to-group-join-list";
	}

}
