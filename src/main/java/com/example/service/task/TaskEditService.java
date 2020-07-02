package com.example.service.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Task;
import com.example.repository.task.TaskRepository;

/**
 * タスクを編集する.
 * 
 * @author oyamadakenji
 *
 */
@Service
@Transactional
public class TaskEditService {

	@Autowired
	private TaskRepository taskRepository;
	
	/**
	 * タスクを編集する.
	 * 
	 * @param task 更新するタスク
	 */
	public void save(Task task) {
		taskRepository.save(task);
	}
}
