package com.example.service.group_relation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.GroupRelation;
import com.example.repository.group_relation.GroupRelationRepository;

/**
 * グループの参加に関する情報を取得するレポジトリー.
 * 
 * @author oyamadakenji
 *
 */
@Service
public class GroupRelationRegisterService {

	@Autowired
	private GroupRelationRepository groupRelationRepository;
	
	/**
	 * グループへ招待する.
	 * 
	 * @param groupRelation グループと招待するユーザーの情報
	 * @return 
	 */
	public GroupRelation insert(GroupRelation groupRelation) {
		//■ 以前、招待されたか?
		// groupIdとuserIdから記録を調べる。
		//■ YES > update
		
		//■ NO > insert
		groupRelation = groupRelationRepository.save(groupRelation);
		
		return groupRelation;
	}
}
