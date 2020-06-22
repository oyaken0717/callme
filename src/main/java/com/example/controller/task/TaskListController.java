package com.example.controller.task;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * タスク一覧を表示するコントローラー.
 * 
 * @author oyamadakenji
 *
 */
@Controller
@RequestMapping("/task-list")
public class TaskListController {
	
	/**
	 * タスク一覧画面へ.
	 * 
	 * @return タスク一覧画面
	 */
	@RequestMapping("/to-task-list")
	public String toTaskList() {
		return "task/task_list";
	}

}
