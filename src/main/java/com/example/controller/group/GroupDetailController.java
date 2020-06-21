package com.example.controller.group;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author oyamadakenji
 * 
 * グループの詳細を表示するコントローラー.
 *
 */
@Controller
@RequestMapping("/group-detail")
public class GroupDetailController {
	
	/**
	 * グループ詳細画面へ.
	 * 
	 * @return グループ詳細画面
	 */
	@RequestMapping("/to-group-detail")
	public String toGroupDetail() {
		
		return "group/group_detail";
	}

}
