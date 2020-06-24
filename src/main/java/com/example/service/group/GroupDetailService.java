package com.example.service.group;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.Group;
import com.example.repository.group.GroupRepository;

/**
 * グループの詳細を取得するサービス.
 * 
 * @author oyamadakenji
 *
 */
@Service
public class GroupDetailService {

	@Autowired
	private GroupRepository groupRepository;
	
	/**
	 * グループの詳細を取得する.
	 * 
	 * @param group idで特定されたグループ情報
	 */
	public Group load(Integer id) {
		Group group = groupRepository.load(id);
		return group;
	}
}
