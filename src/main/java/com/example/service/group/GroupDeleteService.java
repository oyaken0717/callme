package com.example.service.group;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.repository.group.GroupRepository;

/**
 * idからグループ情報を削除するサービス.
 * 
 * @author oyamadakenji
 *
 */
@Service
@Transactional
public class GroupDeleteService {

	@Autowired
	private GroupRepository groupRepository; 
	
	/**
	 * idからグループ情報を取得する.
	 * 
	 * @param id　グループid
	 * 
	 */
	public void delete(Integer groupId) {
		groupRepository.delete(groupId);
	}
}
