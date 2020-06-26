package com.example.service.group_relation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.Group;
import com.example.repository.group_relation.GroupRelationRepository;

/**
 * グループの参加に関する情報を取得するサービス.
 * 
 * @author oyamadakenji
 *
 */
@Service
public class GroupRelationListService {
	
	@Autowired
	private GroupRelationRepository groupRelationRepository;
	
	/**
	 * 特定のユーザーのグループ参加の状態によりグループリストを取得する.
	 * 
	 * @param status グループの参加状況　0:招待中 1:参加 9:不参加
	 * @param userId ログインユーザーID
	 * @return グループのリスト
	 * 
	 */
	public List<Group> findBySatus(Integer status,Integer userId) {
		List<Group> groupList = groupRelationRepository.findBySatusAndUserId(status,userId);
		return groupList;
	}
}
