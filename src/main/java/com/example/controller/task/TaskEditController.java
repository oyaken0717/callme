package com.example.controller.task;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * タスクを編集するコントローラー.
 * 
 * @author oyamadakenji
 *
 */
@Controller
@RequestMapping("/task-edit")
public class TaskEditController {
	
	/**
	 * タスク編集画面へ.
	 * 
	 * @return タスク編集画面
	 */
	@RequestMapping("/to-task-edit")
	public String toTaskEdit() {
		return "task/task_edit";
	}

}
