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
public class UserEditService {

	@Autowired
	private UserRepository userRepository;

	/**
	 * ユーザー情報を更新する.
	 * 
	 * @param user 更新したいユーザー情報
	 */
	public void save(User user) {
		userRepository.save(user);
	}
}
