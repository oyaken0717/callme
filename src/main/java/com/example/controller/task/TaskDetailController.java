package com.example.controller.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Task;
import com.example.service.task.TaskDetailService;

/**
 * タスクの詳細を表示するコントローラー.
 * 
 * @author oyamadakenji
 *
 */
@Controller
@RequestMapping("/task-detail")
public class TaskDetailController {
	
	@Autowired
	private TaskDetailService taskDetailService;
	
	/**
	 * タスク詳細画面へ.
	 * 
	 * @return タスク詳細画面
	 */
	@RequestMapping("/to-task-detail")
	public String toTaskDetail(Integer id, Model model) {
		Task task = taskDetailService.load(id);
		model.addAttribute("task",task);
		return "task/task_detail";
	}

}
