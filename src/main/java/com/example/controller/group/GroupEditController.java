package com.example.controller.group;

import java.sql.ResultSet;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.domain.Group;
import com.example.form.GroupForm;
import com.example.service.group.GroupDetailService;
import com.example.service.group.GroupEditService;

/**
 * @author oyamadakenji
 * 
 * user情報を登録するコントローラー.
 *
 */
@Controller
@RequestMapping("/group-edit")
public class GroupEditController {
	
	@ModelAttribute
	private GroupForm setUpForm() {
		return new GroupForm();
	}	
	
	@Autowired
	private GroupDetailService groupDetailService;
	
	@Autowired
	private GroupEditService groupEditService;
	
	/**
	 * グループ編集画面へ.
	 * 
	 * @return グループ編集画面
	 */
	@RequestMapping("/to-group-edit")
	public String toGroupEdit(Integer id,Model model) {
		
		Group group = groupDetailService.load(id);
		
		model.addAttribute("groupForm",group);
		return "group/group_edit";
	}

	/**
	 * グループを編集する.
	 * 
	 * @param groupForm 入力された新しいグループの情報
	 * @param redirectAttributes フラッシュスコープ
	 * @return グループ詳細画面
	 */
	@RequestMapping("/group-edit")
	public String groupEdit(@Validated GroupForm groupForm, BindingResult result,RedirectAttributes redirectAttributes) {
		
		if (result.hasErrors()) {
			return "group/group_edit";
		}		
		
		Group group = groupDetailService.load(groupForm.getIntId());
		
		BeanUtils.copyProperties(groupForm, group);
		group.setId(groupForm.getIntId());
		group.setOwnerId(groupForm.getIntOwnerId());
		
		groupEditService.save(group);
		
		redirectAttributes.addAttribute("id",groupForm.getIntId());
		return "redirect:/group-detail/to-group-detail";
	}

}
