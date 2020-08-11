package com.example.controller.task;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
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
	
	@ModelAttribute
	public TaskForm setUpForm() {
		return new TaskForm();
	}
	
	@Autowired
	private TaskRegisterService taskRegisterService;
	
	/**
	 * タスク登録画面へ.
	 * 
	 * @return タスク登録画面
	 */
	@RequestMapping("/to-task-register")
	public String toTaskRegister(Integer groupId,Model model) {
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
	public String taskRegister(@Validated TaskForm form, BindingResult result, @AuthenticationPrincipal LoginUser loginUser,RedirectAttributes redirectAttributes, Model model) {

		if (result.hasErrors()) {
			return toTaskRegister(form.getIntGroupId(),model);
		}
		
		Task task = new Task();
		BeanUtils.copyProperties(form, task);
		task.setUserId(loginUser.getUser().getUserId());
		task.setGroupId(form.getIntGroupId());

		taskRegisterService.insert(task);
		redirectAttributes.addAttribute("id", form.getIntGroupId());		
		return "redirect:/group-detail/to-group-detail";
	}

}
