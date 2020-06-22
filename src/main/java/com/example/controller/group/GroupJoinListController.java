package com.example.controller.group;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author oyamadakenji
 * 
 * 参加しているグループ情報を表示するコントローラー.
 *
 */
@Controller
@RequestMapping("/group-join-list")
public class GroupJoinListController {
	
	/**
	 * 参加グループ表示画面へ.
	 * 
	 * @return 参加グループ表示画面
	 */
	@RequestMapping("/to-group-join-list")
	public String toGroupJoinList() {
		
		return "group/group_join_list";
	}

}
