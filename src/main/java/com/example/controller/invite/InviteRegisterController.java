
package com.example.controller.invite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.domain.Group;
import com.example.domain.LoginUser;
import com.example.form.GroupRelationForm;
import com.example.service.group.GroupDetailService;
import com.example.service.group_relation.GroupRelationRegisterService;

/**
 * @author oyamadakenji
 *
 * 招待する人を決定するコントローラー.
 *
 */
@Controller
@RequestMapping("/invite_register")
public class InviteRegisterController {

	@Autowired
	private GroupDetailService groupDetailService;
	
	@Autowired
	private GroupRelationRegisterService groupRelationRegisterService;

	/**
	 * グループ招待画面へ.
	 * 
	 * @return グループ招待画面
	 */
	@RequestMapping("/to_invite_register")
	public String toInviteRegister(Integer groupId,Model model,@AuthenticationPrincipal LoginUser loginUser) {
		
		Group group = groupDetailService.load(groupId);
				
		model.addAttribute("group",group);
		model.addAttribute("userId",loginUser.getUser().getUserId());

		return "group_relation/invite_register";

	}
	
	/**
	 * 招待をする(登録).
	 * 
	 * @return グループ招待画面
	 */
	@RequestMapping("/invite_register")
	public String inviteRegister(GroupRelationForm form,RedirectAttributes redirectAttributes) {
		
		//■ UserList(招待したユーザー)分、insertする > コード量が多い > setGroupRelation()に分割 > そのままinsert  
		groupRelationRegisterService.setGroupRelation(form);
		
		redirectAttributes.addAttribute("id",form.getIntGroupId());
		return "redirect:/group-detail/to-group-detail";
	}
	
//	/**
//	 * 「inviteRegister」メソッドのサポートメソッド.<br>
//	 * 1.formからdomainに値を入れる。<br>
//	 * 2.userListに入ったユーザーのid分insertを回す。
//	 * 
//	 */
//	public void setGroupRelation(GroupRelationForm form) {	
//		
//		//■ userListに入ったユーザーのid分insertを回す。
//		for (String userId : form.getUserList()) {
//			GroupRelation groupRelation = new GroupRelation();
//			groupRelation.setGroupId(form.getIntGroupId());
//			
//			//■ グループ参加状況 0:招待中 1:参加 9:不参加
//			groupRelation.setStatus(0);
//			groupRelation.setUserId(Integer.parseInt(userId));
//			groupRelationRegisterService.insert(groupRelation);
//		}
//
//	}

}
