
package com.example.controller.invite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.domain.GroupRelation;
import com.example.domain.LoginUser;
import com.example.service.group_relation.GroupRelationJoinService;

/**
 * @author oyamadakenji
 *
 * グループに参加するコントローラー.
 *
 */
@Controller
@RequestMapping("/invite_group_join")
public class InviteGroupJoinController {

	@Autowired
	private GroupRelationJoinService groupRelationJoinService;
	
	/**
	 * 
	 * グループに参加する.
	 * 
	 * @param groupId グループのid
	 * @param loginUser ログインユーザーのid
	 * @param redirectAttributes フラッシュスコープ
	 * 
	 * @return グループ詳細画面
	 */
	@RequestMapping("/group-join")
	public String groupJoin(Integer groupId,@AuthenticationPrincipal LoginUser loginUser,RedirectAttributes redirectAttributes) {
		//■ 「status:0 招待中」のグループ関係を取得する。
		GroupRelation groupRelation = groupRelationJoinService.findByGroupIdAndUserId(groupId, loginUser.getUser().getUserId());

		//■ 更新する。
		//■ 「status:1 参加」にする。
		groupRelation.setStatus(1);
		groupRelationJoinService.save(groupRelation);
		
		redirectAttributes.addAttribute("id",groupRelation.getId());
		return "redirect:/group-detail/to-group-detail";
	}
}
