package com.example.service.group;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Group;
import com.example.repository.group.GroupRepository;

/**
 * グループを登録するサービス.
 * 
 * @author oyamadakenji
 *
 */
@Service
@Transactional
public class GroupRegisterService {

	@Autowired
	private GroupRepository groupRepository;
	
	/**
	 * グループを登録する.
	 * 
	 * @param group 新規のグループ情報が入力されたドメイン
	 */
	public Group insert(Group group) {
		group = groupRepository.save(group);
		return group;
	}
}
