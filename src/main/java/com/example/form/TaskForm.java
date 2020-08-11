package com.example.form;

import javax.validation.constraints.NotBlank;

public class TaskForm {
	
	/** タスクid */
	private String id;
	/** タスク名 */
	@NotBlank(message = "タスク名を入力してください")
	private String name;
	/** タスク内容 */
	private String content;
	/** タスクを作ったユーザーのid */
	private String userId;
	/** タスクが作られたグループのid */
	private String groupId;

	//■ getIntーーーーー
	public Integer getIntId() {
	  return Integer.parseInt(id);
	}
	public Integer getIntUserId() {
	  return Integer.parseInt(userId);
	}
	public Integer getIntGroupId() {
	  return Integer.parseInt(groupId);
	}
	//■ ーーーーーーーーー
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
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
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	@Override
	public String toString() {
		return "TaskForm [id=" + id + ", name=" + name + ", content=" + content + ", userId=" + userId + ", groupId="
				+ groupId + "]";
	}
}
