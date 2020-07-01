package com.example.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.User;
import com.example.repository.user.UserRepository;

/**
 * idからユーザー情報を取得するサービス.
 * 
 * @author oyamadakenji
 *
 */
@Service
@Transactional
public class UserDetailService {

	@Autowired
	private UserRepository userRepository; 
	
	/**
	 * idからユーザー情報を取得する.
	 * 
	 * @param id　ユーザーid
	 * @return ユーザー
	 */
	public User load(Integer id) {
		User user = userRepository.load(id);
		return user;
	}
}
