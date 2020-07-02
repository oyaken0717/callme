package com.example.service.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Task;
import com.example.repository.task.TaskRepository;

/**
 * idからタスク情報を取得するサービス.
 * 
 * @author oyamadakenji
 *
 */
@Service
@Transactional
public class TaskDetailService {

	@Autowired
	private TaskRepository taskRepository; 
	
	/**
	 * idからタスク情報を取得する.
	 * 
	 * @param id　タスクid
	 * @return タスク
	 */
	public Task load(Integer id) {
		Task task = taskRepository.load(id);
		return task;
	}
}
