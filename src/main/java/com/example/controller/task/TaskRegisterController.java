package com.example.controller.task;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * タスクを登録するコントローラー.
 * 
 * @author oyamadakenji
 *
 */
@Controller
@RequestMapping("/task-register")
public class TaskRegisterController {
	
	/**
	 * タスク登録画面へ.
	 * 
	 * @return タスク登録画面
	 */
	@RequestMapping("/to-task-regsdter")
	public String toTaskRegsdter() {
		return "task/task_register";
	}

}
