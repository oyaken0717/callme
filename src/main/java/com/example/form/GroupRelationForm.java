package com.example.form;

import java.util.List;

import javax.validation.constraints.NotNull;

/**
 * グループに招待されたユーザー達の情報を受け取るフォーム.
 * 
 * @author oyamadakenji
 *
 */
public class GroupRelationForm {

	/** グループ関係のid */
	private String id;
	/** グループのid */
	@NotNull(message = "グループのidを入力してください")
	private String groupId;
	/** checkboxがTRUEの招待されたユーザーのidがリストで入る */
	@NotNull(message = "ユーザーのidを入力してください")
	private List<String> userList;
	/** グループ参加状況 0:招待中 1:参加 9:不参加  */
	@NotNull(message = "グループ参加状況 0:招待中 1:参加 9:不参加 を入力してください")
	private String status;

	//■ getIntーーーーー
	public Integer getIntId() {
	  return Integer.parseInt(id);
	}
	public Integer getIntGroupId() {
	  return Integer.parseInt(groupId);
	}
	public Integer getIntStatus() {
	  return Integer.parseInt(status);
	}
	//■ ーーーーーーーーー
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public List<String> getUserList() {
		return userList;
	}
	public void setUserList(List<String> userList) {
		this.userList = userList;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "GroupRelationForm [id=" + id + ", groupId=" + groupId + ", userList=" + userList + ", status=" + status
				+ "]";
	}
}
