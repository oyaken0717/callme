package com.example.controller.user;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

	@Autowired
	private UserDetailService userDetailService;
	
	@Autowired
	private UserEditService userEditService;
	
//	@ModelAttribute
//	private UserEditForm setUpForm() {
//		return new UserEditForm();
//	}
	
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
	
	@RequestMapping("/user-edit")
	public String userEdit(UserEditForm userEditForm,Model model,RedirectAttributes redirectAttributes) {
		//■ パスワードのチェック	
		
		User lockUser = userDetailService.lockToLoad(userEditForm.getIntUserId());
		
		//■ 楽観処理 ①編集画面の時のversionと②新しく取得したversionが合っているか確認する。
		if (userEditForm.getIntVersion() != lockUser.getVersion()) {
			model.addAttribute("userId", userEditForm.getIntUserId());
			return toUserEdit(userEditForm.getIntUserId(),model);
		}
		
		String password = lockUser.getPassword();
		
		BeanUtils.copyProperties(userEditForm,lockUser);
		lockUser.setUserId(userEditForm.getIntUserId());
		lockUser.setPassword(password);
		//■ 本当はSQLで version + 1 をしたいが返り値の問題でここで+1する。		
		lockUser.setVersion(userEditForm.getIntVersion()+1);

		userEditService.save(lockUser);
		
		redirectAttributes.addAttribute("userId",userEditForm.getIntUserId());
		return "redirect:/user-detail/to-user-detail";
	}
	
}
