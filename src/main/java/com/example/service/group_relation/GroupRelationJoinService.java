package com.example.service.group_relation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.GroupRelation;
import com.example.repository.group_relation.GroupRelationRepository;

/**
 * グループに参加をするサービス.
 * 
 * @author oyamadakenji
 *
 */
@Service
public class GroupRelationJoinService {

	@Autowired
	private GroupRelationRepository groupRelationRepository;
	
	/**
	 * 「招待中」のグループの情報を取得する.
	 * 
	 * @param groupId グループのid
	 * @param userId 参加者のid
	 * @return 招待中のグループの情報
	 */
	public GroupRelation findByGroupIdAndUserId(Integer groupId, Integer userId) {
		GroupRelation groupRelation = groupRelationRepository.findByGroupIdAndUserId(groupId, userId);
		return groupRelation;
	}
	
	public void save(GroupRelation groupRelation) {
		groupRelationRepository.save(groupRelation);
	}
	
}
