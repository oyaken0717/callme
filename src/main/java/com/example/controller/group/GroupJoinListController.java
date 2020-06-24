package com.example.controller.group;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Group;
import com.example.domain.LoginUser;
import com.example.service.group.GroupJoinListService;

/**
 * @author oyamadakenji
 * 
 * 参加しているグループ情報を表示するコントローラー.
 *
 */
@Controller
@RequestMapping("/group-join-list")
public class GroupJoinListController {
	
	@Autowired
	private GroupJoinListService groupJoinListService;
	
	/**
	 * 参加グループ表示画面へ.
	 * 
	 * @return 参加グループ表示画面
	 */
	@RequestMapping("/to-group-join-list")
	public String toGroupJoinList(@AuthenticationPrincipal LoginUser loginUser,Model model) {
		
		List<Group> groupList = groupJoinListService.findByOwnerId(loginUser.getUser().getUserId());
		model.addAttribute("groupList",groupList);
		return "group/group_join_list";
	}

}
