package com.example.service.group;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.Group;
import com.example.repository.group.GroupRepository;

/**
 * 所属しているグループを取得するサービス.
 * 
 * @author oyamadakenji
 *
 */
@Service
public class GroupJoinListService {

	@Autowired
	private GroupRepository groupRepository;
	
	/**
	 * OwnerIdから所属しているグループを取得する.
	 * 
	 * @param id ユーザーのid
	 * @return ユーザーがオーナーのグループのリスト
	 */
	public List<Group> findByOwnerId(Integer id) {
		List<Group> groupList = groupRepository.findByOwnerId(id);
		return groupList;
	}
	
}
