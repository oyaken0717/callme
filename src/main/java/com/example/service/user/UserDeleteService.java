package com.example.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.repository.user.UserRepository;

/**
 * idからユーザー情報を削除するサービス.
 * 
 * @author oyamadakenji
 *
 */
@Service
@Transactional
public class UserDeleteService {

	@Autowired
	private UserRepository userRepository; 
	
	/**
	 * idからユーザー情報を取得する.
	 * 
	 * @param id　ユーザーid
	 * @return ユーザー
	 */
	public void delete(Integer userId) {
		userRepository.delete(userId);
	}
}
