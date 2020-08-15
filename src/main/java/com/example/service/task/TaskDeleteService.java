package com.example.service.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Task;
import com.example.repository.task.TaskRepository;

/**
 * 
 * タスクを削除するサービス.
 * 
 * @author oyamadakenji
 *
 */
@Service
@Transactional
public class TaskDeleteService {

	@Autowired
	private TaskRepository taskRepository;
	
	/**
	 * タスクを削除する.
	 * 
	 * @param id タスクのid
	 */
	public void deleteByTaskId(Integer id) {
		taskRepository.deleteByTaskId(id);
	}
}
