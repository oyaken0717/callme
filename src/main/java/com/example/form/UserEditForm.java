package com.example.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 入力されたユーザーの情報を受け取るフォーム.
 * 
 * @author oyamadakenji
 *
 */
public class UserEditForm {

	/** ユーザーid */
	private String userId;

	/** ユーザー名 */
	@NotBlank(message = "ユーザー名を入力してください")
	private String name;

	/** メールアドレス */
	@NotBlank(message = "メールアドレスを入力してください")
	private String email;
	
	/** 現在のパスワード */
	private String password;	

	/** 新しいパスワード */
	private String newPassword;

	/** 確認用パスワード */
	private String passwordConfirmation;
	
	/** ロック用のバージョン */
	private String version;
	
//■ getIntーーーーー
	public Integer getIntUserId() {
	  return Integer.parseInt(userId);
	}
	public Integer getIntVersion() {
	  return Integer.parseInt(version);
	}
//■ ーーーーーーーーー
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
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
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public String getPasswordConfirmation() {
		return passwordConfirmation;
	}
	public void setPasswordConfirmation(String passwordConfirmation) {
		this.passwordConfirmation = passwordConfirmation;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	@Override
	public String toString() {
		return "UserEditForm [userId=" + userId + ", name=" + name + ", email=" + email + ", password=" + password
				+ ", newPassword=" + newPassword + ", passwordConfirmation=" + passwordConfirmation + ", version="
				+ version + "]";
	}
}
