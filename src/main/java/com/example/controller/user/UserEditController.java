package com.example.controller.user;

import javax.swing.text.AbstractDocument.BranchElement;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
		
		model.addAttribute("userEditForm",userEditForm);
		return "user/user_edit";
	}
	
	@RequestMapping("/user-edit")
	public String userEdit(UserEditForm userEditForm,RedirectAttributes redirectAttributes) {
		//■ パスワードのチェック
		
		User user = userDetailService.load(userEditForm.getIntUserId());
		String password = user.getPassword();
		
		BeanUtils.copyProperties(userEditForm,user);
		user.setUserId(userEditForm.getIntUserId());
		user.setPassword(password);

		userEditService.save(user);
		
		redirectAttributes.addAttribute("userId",userEditForm.getIntUserId());
		return "redirect:/user-detail/to-user-detail";
	}
	
}
