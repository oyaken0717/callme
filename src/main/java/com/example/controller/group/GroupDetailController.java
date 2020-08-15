package com.example.controller.group;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.domain.Group;
import com.example.service.group.GroupDetailService;

/**
 * @author oyamadakenji
 * 
 * グループの詳細を表示するコントローラー.
 *
 */
@Controller
@RequestMapping("/group-detail")
public class GroupDetailController {
	
	@Autowired
	private GroupDetailService groupDetailService;
	
	/**
	 * グループ詳細画面へ.
	 * 
	 * @return グループ詳細画面
	 */
	@RequestMapping("/to-group-detail")
	public String toGroupDetail(Integer id, Model model) {
System.out.println("きてる");
System.out.println("id:"+id);
		Group group = groupDetailService.load(id);
		model.addAttribute("group",group);
		return "group/group_detail";
	}
}
