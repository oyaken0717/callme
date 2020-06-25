package com.example.domain;

/**
 * タスクの情報を保持するドメイン.
 * 
 * @author oyamadakenji
 *
 */
public class Task {

	/** タスクid */
	private Integer id;
	/** タスク名 */
	private String name;
	/** タスク内容 */
	private String content;
	/** タスクを作ったユーザーのid */
	private Integer userId;
	/** タスクが作られたグループのid */
	private Integer groupId;
	
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getGroupId() {
		return groupId;
	}
	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}
	@Override
	public String toString() {
		return "Task [id=" + id + ", name=" + name + ", content=" + content + ", userId=" + userId + ", groupId="
				+ groupId + "]";
	}
}
