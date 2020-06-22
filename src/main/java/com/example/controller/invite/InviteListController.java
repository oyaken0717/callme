
package com.example.controller.invite;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author oyamadakenji
 *
 * 招待されているグループを表示するコントローラー.
 *
 */
@Controller
@RequestMapping("/invite_list")
public class InviteListController {

	/**
	 * 招待されているグループ一覧画面へ.
	 * 
	 * @return 招待されているグループ一覧画面
	 */
	@RequestMapping("/to_invite_list")
	public String toInviteList() {
		
		return "group_relation/invite_list";
	}
}
