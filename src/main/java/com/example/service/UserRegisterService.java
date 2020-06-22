package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.User;
import com.example.repository.UserRepository;

/**
 * ユーザー情報を登録するサービス.
 * 
 * @author oyamadakenji
 *
 */
@Service
public class UserRegisterService {

	@Autowired
	private UserRepository userRepository;
	
	/**
	 * ユーザー情報を登録する.
	 * 
	 * @param user 新規のユーザー情報
	 */
	public void insert(User user) {
		userRepository.save(user);
	}
}
