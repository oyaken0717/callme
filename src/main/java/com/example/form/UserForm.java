package com.example.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * 入力されたユーザーの情報を受け取るフォーム.
 * 
 * @author oyamadakenji
 *
 */
public class UserForm {

	/** ユーザーid */
	private String userId;

	/** ユーザー名 */
	@Size(min=1,max=127,message = "1文字以上127文字以下で入力してください")
	@NotBlank(message = "ユーザー名を入力してください")
	private String name;

	/** メールアドレス */
	@Email(message="メールアドレスが正しく入力されている必要があります。")
	@NotBlank(message = "メールアドレスを入力してください")
	private String email;

	/** パスワード */
	@Pattern(regexp="^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z0-9]{8,100}$", message="8文字以上32文字以下、半角英字(大文字)、半角英字(小文字)、半角数字をそれぞれ1種類以上使ってください。")
	@NotBlank(message = "パスワードを入力してください")
	private String password;
	
	/** 確認用パスワード */
	@NotBlank(message = "確認用パスワードを入力してください")
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
		return "UserForm [userId=" + userId + ", name=" + name + ", email=" + email + ", password=" + password
				+ ", passwordConfirmation=" + passwordConfirmation + ", version=" + version + "]";
	}
}
