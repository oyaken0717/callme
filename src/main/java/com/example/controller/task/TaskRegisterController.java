package com.example.controller.task;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.domain.LoginUser;
import com.example.domain.Task;
import com.example.form.TaskForm;
import com.example.service.task.TaskRegisterService;

/**
 * タスクを登録するコントローラー.
 * 
 * @author oyamadakenji
 *
 */
@Controller
@RequestMapping("/task-register")
public class TaskRegisterController {
	
	@Autowired
	private TaskRegisterService taskRegisterService;
	
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
	public String taskRegister(TaskForm form,@AuthenticationPrincipal LoginUser loginUser,RedirectAttributes redirectAttributes) {

		Task task = new Task();
		BeanUtils.copyProperties(form, task);
		task.setUserId(loginUser.getUser().getUserId());
		task.setGroupId(form.getIntGroupId());

		taskRegisterService.insert(task);
		redirectAttributes.addAttribute("groupId", form.getIntGroupId());		
		return "redirect:/task-list/to-task-list";
	}

}
