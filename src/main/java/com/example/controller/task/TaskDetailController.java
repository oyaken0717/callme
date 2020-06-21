package com.example.controller.task;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * タスクの詳細を表示するコントローラー.
 * 
 * @author oyamadakenji
 *
 */
@Controller
@RequestMapping("/task-detail")
public class TaskDetailController {
	
	/**
	 * タスク詳細画面へ.
	 * 
	 * @return タスク詳細画面
	 */
	@RequestMapping("/to-task-detail")
	public String toTaskDetail() {
		return "task/task_detail";
	}

}
