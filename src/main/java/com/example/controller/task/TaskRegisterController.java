package com.example.controller.task;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.form.TaskForm;

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
	@RequestMapping("/to-task-register")
	public String toTaskRegsdter(Integer groupId,Model model) {
        model.addAttribute("groupId",groupId);
		return "task/task_register";
	}

	/**
	 * タスク登録.
	 * 
	 * @param form 新規のタスクの情報
	 * @return 
	 */
	@RequestMapping("/task-register")
	public String taskRegister(TaskForm form) {
		
		
		return "task/task_list";
	}

}
