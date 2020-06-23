package com.example.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.domain.User;
import com.example.repository.user.UserRepository;

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

	@Autowired
	private PasswordEncoder passwordEncoder; 

	
	/**
	 * ユーザー情報を登録する.
	 * 
	 * @param user 新規のユーザー情報
	 */
	public void insert(User user) {
		String encodePassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodePassword);
		userRepository.save(user);
	}
}
