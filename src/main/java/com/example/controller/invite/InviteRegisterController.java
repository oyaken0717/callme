
package com.example.controller.invite;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author oyamadakenji
 *
 * 招待する人を決定するコントローラー.
 *
 */
@Controller
@RequestMapping("/invite_register")
public class InviteRegisterController {

	/**
	 * グループ招待画面へ.
	 * 
	 * @return グループ招待画面
	 */
	@RequestMapping("/to_invite_register")
	public String toInviteRegister() {
		
		return "group_relation/invite_register";
	}
}
