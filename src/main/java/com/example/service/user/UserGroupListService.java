package com.example.service.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.User;
import com.example.repository.user.UserRepository;

/**
 * グループに所属している全ユーザー情報を取得するサービス.
 * 
 * @author oyamadakenji
 *
 */
@Service
public class UserGroupListService {

	@Autowired
	private UserRepository userRepository;
	
	/**
	 * グループに所属している全ユーザー情報を取得する.
	 * 
	 * @return　グループに所属している全ユーザーのリスト
	 */
	public List<User> findByGroupId(Integer groupId){
		List<User> userList = userRepository.findByGroupId(groupId);				
		return userList;
	}
}
