package com.example.domain;

import java.util.List;

public class Group {

	/** グループid */
	private Integer id;
	/** グループ名 */
	private String name;
	/** グループを作ったユーザーのid */
	private Integer ownerId;
	/** グループを作った人のUser情報 */	
	private User ownerUser;
	
	/** グループ所属している人のUser情報 */	
	private List<User> userList;
	/** そのグループで登録されたタスク情報 */	
	private List<Task> taskList;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(Integer ownerId) {
		this.ownerId = ownerId;
	}
	public User getOwnerUser() {
		return ownerUser;
	}
	public void setOwnerUser(User ownerUser) {
		this.ownerUser = ownerUser;
	}
	public List<User> getUserList() {
		return userList;
	}
	public void setUserList(List<User> userList) {
		this.userList = userList;
	}
	public List<Task> getTaskList() {
		return taskList;
	}
	public void setTaskList(List<Task> taskList) {
		this.taskList = taskList;
	}
	@Override
	public String toString() {
		return "Group [id=" + id + ", name=" + name + ", ownerId=" + ownerId + ", ownerUser=" + ownerUser
				+ ", userList=" + userList + ", taskList=" + taskList + "]";
	}
}
