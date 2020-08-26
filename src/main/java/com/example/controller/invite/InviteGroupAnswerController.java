
package com.example.controller.invite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.domain.GroupRelation;
import com.example.domain.LoginUser;
import com.example.service.group_relation.GroupRelationAnswerService;

/**
 * @author oyamadakenji
 *
 * グループに参加するコントローラー.
 *
 */
@Controller
@RequestMapping("/invite_group_answer")
public class InviteGroupAnswerController {

	@Autowired
	private GroupRelationAnswerService groupRelationAnswerService;
	
	/**
	 * 
	 * グループに参加、不参加を登録する.
	 * 
	 * @param groupId グループのid
	 * @param loginUser ログインユーザーのid
	 * @param redirectAttributes フラッシュスコープ
	 * 
	 * @return グループ詳細画面
	 */
	@RequestMapping("/invite-answer")
	public String inviteAnswer(Integer groupId,Integer status,@AuthenticationPrincipal LoginUser loginUser,RedirectAttributes redirectAttributes) {
		//■ 「status:0 招待中」のグループ関係を取得する。
		GroupRelation groupRelation = groupRelationAnswerService.findByGroupIdAndUserId(groupId,loginUser.getUser().getUserId(),0);
		groupRelation.setStatus(status); //■ 更新する。「status:1 参加, 9 不参加」を入れる。
		
		groupRelationAnswerService.save(groupRelation);
		
		
		if (status == 9) { //■ 「status:9 不参加」であれば招待されているグループリストに戻る
			return "redirect:/invite_list/to_invite_list";		
		}
		
		redirectAttributes.addAttribute("id",groupRelation.getGroupId()); //■ グループ詳細画面へ
		return "redirect:/group-detail/to-group-detail";			
	}
}
