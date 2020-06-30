
package com.example.controller.invite;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.domain.Group;
import com.example.domain.GroupRelation;
import com.example.domain.LoginUser;
import com.example.domain.User;
import com.example.form.GroupRelationForm;
import com.example.service.group.GroupDetailService;
import com.example.service.group_relation.GroupRelationRegisterService;
import com.example.service.user.UserListService;

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
	private UserListService userListService;
	
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
		List<User> userList = userListService.findAll();
				
		model.addAttribute("group",group);
		model.addAttribute("userList",userList);
		model.addAttribute("loginUserId",loginUser.getUser().getUserId());

		return "group_relation/invite_register";
	}
	
	/**
	 * 招待をする(登録).
	 * 
	 * @return グループ招待画面
	 */
	@RequestMapping("/invite_register")
	public String inviteRegister(GroupRelationForm form,RedirectAttributes redirectAttributes) {
		
		setGroupRelation(form);
		redirectAttributes.addAttribute("id",form.getIntGroupId());
		return "redirect:/group-detail/to-group-detail";
	}
	
	/**
	 * 「inviteRegister」メソッドのサポートメソッド.<br>
	 * formからdomainに値を入れる。<br>
	 * userListに入ったユーザーのid分insertを回す。
	 * 
	 */
	public void setGroupRelation(GroupRelationForm form) {	
		
		for (String userId : form.getUserList()) {
			//■ userListに入ったユーザーのid分insertを回す。
			GroupRelation groupRelation = new GroupRelation();
			groupRelation.setGroupId(form.getIntGroupId());
			//■ グループ参加状況 0:招待中 1:参加 9:不参加
			groupRelation.setStatus(0);
			groupRelation.setUserId(Integer.parseInt(userId));
			groupRelationRegisterService.insert(groupRelation);
		}

	}

}
