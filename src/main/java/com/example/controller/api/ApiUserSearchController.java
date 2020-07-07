package com.example.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.LoginUser;
import com.example.domain.User;
import com.example.service.user.UserInviteService;


/**
 * ajaxを使ってユーザー一覧を取得する.
 * 
 * @author oyamadakenji
 *
 */
@RestController
@RequestMapping("/api-user-search")
public class ApiUserSearchController {
	
	@Autowired
	private UserInviteService userInviteService;
		
    /**
     * 検索フォームから入力された①nameと②groupのidと③ログインユーザーのidからあいまい検索.
     * 
     * @param name 検索フォームから入力されたname
     * @return 該当するユーザーリスト
     */
    @RequestMapping(value ="/find-by-like-name-and-user-id-and-group-id", method = RequestMethod.POST)
	public List<User> findByLikeNameAndUserIdAndGroupId(String name,Integer userId,Integer groupId) {
    	List<User> userList = userInviteService.findByLikeNameAndUserIdAndGroupId(name,userId,groupId);
    	return userList;			
	}	

}
