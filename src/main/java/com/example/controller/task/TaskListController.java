package com.example.controller.task;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Task;
import com.example.service.task.TaskListService;

/**
 * タスク一覧を表示するコントローラー.
 * 
 * @author oyamadakenji
 *
 */
@Controller
@RequestMapping("/task-list")
public class TaskListController {
	
	@Autowired
	private TaskListService taskListService;
	
	/**
	 * タスク一覧画面へ.
	 * 
	 * @return タスク一覧画面
	 */
	@RequestMapping("/to-task-list")
	public String toTaskList(Integer groupId,Model model) {
		List<Task> taskList = taskListService.findByGroupId(groupId);
		model.addAttribute("taskList",taskList);
		return "task/task_list";
	}

}
