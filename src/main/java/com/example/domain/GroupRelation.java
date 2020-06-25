package com.example.domain;

/**
 * グループへの参加状況を保持するドメイン.
 * 
 * @author oyamadakenji
 *
 */
public class GroupRelation {

	/** グループ関係のid */
	private Integer id;
	/** グループのid */
	private Integer groupId;
	/** ユーザーのid */
	private Integer userId;
	/** グループ参加状況 0:招待中 1:参加 9:不参加  */
	private Integer status;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getGroupId() {
		return groupId;
	}
	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "GroupRelation [id=" + id + ", groupId=" + groupId + ", userId=" + userId + ", status=" + status + "]";
	}
}
