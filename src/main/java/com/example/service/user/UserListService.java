package com.example.service.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.User;
import com.example.repository.user.UserRepository;

/**
 * 全ユーザー情報を取得するサービス.
 * 
 * @author oyamadakenji
 *
 */
@Service
public class UserListService {

	@Autowired
	private UserRepository userRepository;
	
	/**
	 * 全ユーザー情報を取得する.
	 * 
	 * @return　全ユーザーのリスト
	 */
	public List<User> findAll(){
		List<User> userList = userRepository.findAll();
		return userList;
	}
}
