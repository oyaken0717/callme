package com.example.controller.group;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author oyamadakenji
 * 
 * user情報を登録するコントローラー.
 *
 */
@Controller
@RequestMapping("/group-register")
public class GroupRegisterController {
	
	/**
	 * グループ登録画面へ.
	 * 
	 * @return グループ登録画面
	 */
	@RequestMapping("/to-group-register")
	public String toGroupRegister() {
		
		return "group/group_register";
	}

}
