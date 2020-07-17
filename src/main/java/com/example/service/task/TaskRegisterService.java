package com.example.service.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Task;
import com.example.repository.task.TaskRepository;

/**
 * 
 * タスクを登録するサービス.
 * 
 * @author oyamadakenji
 *
 */
@Service
@Transactional
public class TaskRegisterService {

	@Autowired
	private TaskRepository taskRepository;
	
	public void insert(Task task) {
		taskRepository.save(task);
	}
}
