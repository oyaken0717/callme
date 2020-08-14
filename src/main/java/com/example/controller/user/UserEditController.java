package com.example.controller.user;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.domain.User;
import com.example.form.UserEditForm;
import com.example.service.user.UserDetailService;
import com.example.service.user.UserEditService;

/**
 * ユーザー情報を編集するコントローラー.
 * 
 * @author oyamadakenji
 *
 */
@Controller
@RequestMapping("/user-edit")
public class UserEditController {

	@ModelAttribute
	private UserEditForm setUpForm() {
		return new UserEditForm();
	}	
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserDetailService userDetailService;
	
	@Autowired
	private UserEditService userEditService;
	
	
	/**
	 * ユーザー編集画面へ.
	 * 
	 * @return ユーザー編集画面
	 */
	@RequestMapping("/to-user-edit")
	public String toUserEdit(Integer userId,Model model) {
		
		User user = userDetailService.load(userId);
		
		UserEditForm userEditForm = new UserEditForm();
		BeanUtils.copyProperties(user, userEditForm);
		userEditForm.setUserId(String.valueOf(user.getUserId()));
		userEditForm.setVersion(String.valueOf(user.getVersion()));
		
		model.addAttribute("userEditForm",userEditForm);
		
		return "user/user_edit";
	}
	
	/**
	 * ユーザーを更新する.
	 * 
	 * @param userEditForm ユーザー編集画面で入力されたフォーム
	 * @param model モデル
	 * @param redirectAttributes フラッシュ
	 * @return ユーザー詳細画面/編集画面
	 */
	@RequestMapping("/user-edit")
	public String userEdit(@Validated UserEditForm userEditForm, BindingResult result, Model model,RedirectAttributes redirectAttributes) {
		
		//■ 最新のUser情報
		User lockUser = userDetailService.lockToLoad(userEditForm.getIntUserId());
		
//■ 3つのパスワードのどれかが入力されていたら(空白でなければ)、チェックが動く > メソッドで切り分けたい。resultにrejectを格納して返す感じ。
		if (
			!"".equals(userEditForm.getPassword()) ||
			!"".equals(userEditForm.getNewPassword()) ||
			!"".equals(userEditForm.getPasswordConfirmation())
			) {		
			
			//■ 1.現在のパスワードが入力されているか？
			if ("".equals(userEditForm.getPassword())) {
				result.rejectValue("password", null, "現在のパスワードが間違っています。");
			}		

			//■ 2.新しいパスワードが入力されているか？
			if ("".equals(userEditForm.getNewPassword())) {
				result.rejectValue("newPassword", null, "新しいパスワードが間違っています。");
			}		
			
			//■ 3.確認用パスワードが入力されているか？
			if ("".equals(userEditForm.getPasswordConfirmation())) {
				result.rejectValue("passwordConfirmation", null, "確認用パスワードが間違っています。");
			}		
	
			//■ 現在のパスワードのチェック
			if(!passwordEncoder.matches(userEditForm.getPassword(), lockUser.getPassword())) {
				result.rejectValue("password", null, "パスワードが間違っています。");
			}
	
			//■ 新しいパスワードのチェック 正規表現
			String str = userEditForm.getNewPassword();
	        if (!str.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z0-9]{8,100}$")) {
	        	result.rejectValue("newPassword", null, "8文字以上32文字以下、半角英字(大文字)、半角英字(小文字)、半角数字をそれぞれ1種類以上使ってください。");
			}
			
	        //■ 新しいパスワードと確認用パスワードが同じかチェック
	        if (!userEditForm.getNewPassword().equals(userEditForm.getPasswordConfirmation())) {
	        	result.rejectValue("newPassword", null, "新しいパスワードと確認用パスワードが合っていません。");
			}
	        
			//■ 現在(昔)のパスワードと新しい(今)パスワードが同じであればエラー
	        if (userEditForm.getPassword().equals(userEditForm.getNewPassword())) {	
	        	result.rejectValue("password", null, "現在のパスワードと新しいパスワードは別にしてください。");
	        }
			
	    //■ 最初の「3つのパスワードのどれかが入力されていたら(空白でなければ)」のifの終わり
		}	
		
		//■ 楽観処理 ①編集画面の時のversionと②新しく取得したversionが合っているか確認する。
		if (userEditForm.getIntVersion() != lockUser.getVersion()) {
			result.rejectValue("version", null, "編集中にデータが更新されました。編集内容を確認してください。");			
		}

		if (result.hasErrors()) {
			return "user/user_edit";
		}		
		
//■ パスワードのハッシュ化
//■ 正常処理		
		String password = lockUser.getPassword();
		
		BeanUtils.copyProperties(userEditForm,lockUser);
		lockUser.setUserId(userEditForm.getIntUserId());
		lockUser.setPassword(password);

		try {
			userEditService.save(lockUser);			
		} catch (Exception e) {
			model.addAttribute("userId", userEditForm.getIntUserId());
			return toUserEdit(userEditForm.getIntUserId(),model);
		}
		
		redirectAttributes.addAttribute("userId",userEditForm.getIntUserId());
		return "redirect:/user-detail/to-user-detail";
	}
		
}
