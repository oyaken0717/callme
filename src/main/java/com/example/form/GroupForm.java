package com.example.form;

import javax.validation.constraints.NotBlank;

public class GroupForm {

	/** グループid */
	private String id;

	/** グループ名 */
	@NotBlank(message = "グループ名を入力してください")
	private String name;

	/** グループを作ったユーザーのid */
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
