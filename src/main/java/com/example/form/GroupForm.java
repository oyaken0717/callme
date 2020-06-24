package com.example.form;

import javax.validation.constraints.NotNull;

public class GroupForm {

	/** グループid */
	private String id;

	/** グループ名 */
	@NotNull(message = "グループ名を入力してください")
	private String name;

	/** グループを作ったユーザーのid */
	@NotNull(message = "グループを作ったユーザーのidを入力してください")
	private String ownerId;

	//■ getIntーーーーー
	public Integer getIntId() {
	  return Integer.parseInt(id);
	}
	public Integer getIntOwnerId() {
	  return Integer.parseInt(ownerId);
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
	public String getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}
	@Override
	public String toString() {
		return "GroupForm [id=" + id + ", name=" + name + ", ownerId=" + ownerId + "]";
	}
}
