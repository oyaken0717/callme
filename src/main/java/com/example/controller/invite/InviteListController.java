
package com.example.controller.invite;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Group;
import com.example.domain.LoginUser;
import com.example.service.group_relation.GroupRelationListService;

/**
 * @author oyamadakenji
 *
 * 招待されているグループを表示するコントローラー.
 *
 */
@Controller
@RequestMapping("/invite_list")
public class InviteListController {

	@Autowired
	private GroupRelationListService groupRelationListService;
	
	/**
	 * 招待されているグループ一覧画面へ.
	 * 
	 * @return 招待されているグループ一覧画面
	 */
	@RequestMapping("/to_invite_list")
	public String toInviteList(@AuthenticationPrincipal LoginUser loginUser,Model model) {
		List<Group> groupList = groupRelationListService.findBySatus(0, loginUser.getUser().getUserId());//■ 「0」はstatus。「招待中」を表す。
		model.addAttribute("groupList",groupList);
		return "group_relation/invite_list";
	}
}
