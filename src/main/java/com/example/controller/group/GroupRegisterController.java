package com.example.controller.group;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.domain.Group;
import com.example.domain.GroupRelation;
import com.example.domain.LoginUser;
import com.example.form.GroupForm;
import com.example.service.group.GroupRegisterService;
import com.example.service.group_relation.GroupRelationRegisterService;

/**
 * @author oyamadakenji
 * 
 * user情報を登録するコントローラー.
 *
 */
@Controller
@RequestMapping("/group-register")
public class GroupRegisterController {
	
	@Autowired
	private GroupRegisterService groupRegisterService;
	
	@Autowired
	private GroupRelationRegisterService groupRelationRegisterService;
	/**
	 * グループ登録画面へ.
	 * 
	 * @return グループ登録画面
	 */
	@RequestMapping("/to-group-register")
	public String toGroupRegister() {
		
		return "group/group_register";
	}

	/**
	 * グループの登録
	 * 
	 * @return グループ詳細画面
	 */
	@RequestMapping("/group-register")
	public String groupRegister(GroupForm form, @AuthenticationPrincipal LoginUser loginUser,RedirectAttributes redirectAttributes) {

		//■ グループの登録
		Group group = new Group();
		BeanUtils.copyProperties(form, group);
		Integer userId = loginUser.getUser().getUserId();
		group.setOwnerId(userId);
		group = groupRegisterService.insert(group);

		//■ GroupRelationの登録
		GroupRelation groupRelation = new GroupRelation();
		groupRelation.setGroupId(group.getId());
		groupRelation.setUserId(userId);
		//■　グループ参加状況 0:招待中 1:参加 9:不参加
		groupRelation.setStatus(1);
		groupRelationRegisterService.insert(groupRelation);
		
		redirectAttributes.addAttribute("id", group.getId());
		return "redirect:/group-detail/to-group-detail";
	}

	
}
