package com.example.service.task;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Task;
import com.example.repository.task.TaskRepository;

/**
 * タスク一覧を取得するサービス.
 * 
 * @author oyamadakenji
 *
 */
@Service
@Transactional
public class TaskListService {

	@Autowired
	private TaskRepository taskRepository;
	
	/**
	 * groupのidからtaskを取得する.
	 * 
	 * @param groupId グループのid
	 * @return タスクの一覧
	 */
	public List<Task> findByGroupId(Integer groupId) {
		List<Task> taskList = taskRepository.findByGroupId(groupId);
		return taskList;
	}
}
