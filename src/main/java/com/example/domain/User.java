package com.example.domain;

import java.util.List;

import org.springframework.scheduling.config.Task;

/**
 * ユーザー情報が入るドメイン.
 * 
 * @author oyamadakenji
 *
 */
public class User {

	/** ユーザーid */
	private Integer userId;
	/** ユーザー名 */
	private String name;
	/** メールアドレス */
	private String email;
	/** パスワード */
	private String password;
	
	/** 登録したタスクのリスト */
	private List<Task> taskList;
	/** 所属しているグループのリスト */
	private List<Group> groupList;
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<Task> getTaskList() {
		return taskList;
	}
	public void setTaskList(List<Task> taskList) {
		this.taskList = taskList;
	}
	public List<Group> getGroupList() {
		return groupList;
	}
	public void setGroupList(List<Group> groupList) {
		this.groupList = groupList;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", name=" + name + ", email=" + email + ", password=" + password
				+ ", taskList=" + taskList + ", groupList=" + groupList + "]";
	}
}
