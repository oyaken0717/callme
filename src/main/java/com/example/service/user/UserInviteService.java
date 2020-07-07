package com.example.service.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.User;
import com.example.repository.user.UserRepository;

/**
 * グループにユーザーを招待する時のサービス.
 * 
 * @author oyamadakenji
 *
 */
@Service
@Transactional
public class UserInviteService {

	@Autowired
	private UserRepository userRepository;

    /**
     * 検索フォームから入力されたnameからあいまい検索.
     * 
     * @param name 検索フォームから入力されたname
     * @return 該当するユーザーリスト
     */	
	public List<User> findByLikeNameAndUserIdAndGroupId(String name,Integer userId,Integer groupId) {
		List<User> userList = userRepository.findByLikeNameAndUserIdAndGroupId(name,userId,groupId);
		return userList;
	}

}
