package com.example.controller.task;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.domain.Task;
import com.example.form.TaskForm;
import com.example.service.task.TaskDetailService;
import com.example.service.task.TaskEditService;

/**
 * タスクを編集するコントローラー.
 * 
 * @author oyamadakenji
 *
 */
@Controller
@RequestMapping("/task-edit")
public class TaskEditController {
	
	@ModelAttribute
    public TaskForm setUpForm() {
            return new TaskForm();
    }

	@Autowired
	private TaskDetailService taskDetailService;
	
	@Autowired
	private TaskEditService taskEditService;
	
	/**
	 * タスク編集画面へ.
	 * 
	 * @return タスク編集画面
	 */
	@RequestMapping("/to-task-edit")
	public String toTaskEdit(Integer id,Model model) {
		Task task = taskDetailService.load(id);
		model.addAttribute("taskForm",task);		
		return "task/task_edit";
	}

	@RequestMapping("/task-edit")
	public String taskEdit(@Validated TaskForm form, BindingResult result, RedirectAttributes redirectAttributes) {

		if (result.hasErrors()) {
			return "task/task_edit";
		}		
		
		Task task = new Task();
		BeanUtils.copyProperties(form, task);
		task.setId(form.getIntId());
		task.setUserId(form.getIntUserId());
		task.setGroupId(form.getIntGroupId());
		
		taskEditService.save(task);
		
		redirectAttributes.addAttribute("id",form.getId());
		return "redirect:/task-detail/to-task-detail";
	}

}
