package com.example.controller.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.service.task.TaskDeleteService;

/**
 * タスクを削除するコントローラー.
 * 
 * @author oyamadakenji
 *
 */
@Controller
@RequestMapping("/task-delete")
public class TaskDeleteController {

	@Autowired
	private TaskDeleteService taskDeleteService;
	
	/**
	 * タスク削除.
	 * 
	 * @return 
	 */
	@RequestMapping("/task-delete")
	public String taskdelete(Integer groupId, Integer taskId, RedirectAttributes redirectAttributes) {

		taskDeleteService.deleteByTaskId(taskId);
		
		redirectAttributes.addAttribute("id", groupId);
		return "redirect:/group-detail/to-group-detail";
	}

}
