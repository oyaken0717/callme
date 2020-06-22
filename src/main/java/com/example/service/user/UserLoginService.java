package com.example.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.User;
import com.example.repository.user.UserRepository;

/**
 * ユーザー情報をemailで検索するメソッド.
 * 
 * @author oyamadakenji
 *
 */
@Service
public class UserLoginService {

	@Autowired
	private UserRepository userRepository;
	
	public User findByEmail(String email) {
		User user = userRepository.findByEmail(email);
		return user;
	}
}
