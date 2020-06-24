package com.example.controller.group;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.domain.Group;
import com.example.domain.LoginUser;
import com.example.form.GroupForm;
import com.example.service.group.GroupRegisterService;

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
	private GroupDetailController groupDetailController;
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

		Group group = new Group();
		BeanUtils.copyProperties(form, group);
		group.setOwnerId(loginUser.getUser().getUserId());
		group = groupRegisterService.insert(group);

		redirectAttributes.addAttribute("id", group.getId());
		//redirectAttributes.addFlashAttribute("id", group.getId());
		return "redirect:/group-detail/to-group-detail";
	}

	
}
