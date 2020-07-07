package com.example.service.group_relation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.GroupRelation;
import com.example.domain.User;
import com.example.form.GroupRelationForm;
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
	 * 「inviteRegister」メソッドのサポートメソッド.<br>
	 * 1.formからdomainに値を入れる。<br>
	 * 2.userListに入ったユーザーのid分insertを回す。
	 * 
	 */
	public void setGroupRelation(GroupRelationForm form) {	
		
		//■ userListに入ったユーザーのid分insertを回す。
		for (String userId : form.getUserList()) {
			GroupRelation groupRelation = new GroupRelation();
			groupRelation.setGroupId(form.getIntGroupId());
			
			//■ グループ参加状況 0:招待中 1:参加 9:不参加
			groupRelation.setStatus(0);
			groupRelation.setUserId(Integer.parseInt(userId));
			insert(groupRelation);
		}

	}
	
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
	
	/**
	 * オートコンプリート用の情報を格納するメソッド.
	 * 
	 * 
	 */
	public StringBuilder getUserListForAutoconplete(List<User> userList) {
		StringBuilder userListForAutocomplete = new StringBuilder();
		for (int i = 0; i < userList.size(); i++) {
			if (i != 0) {
				userListForAutocomplete.append(",");
			}
			User user = userList.get(i);
			userListForAutocomplete.append("\"");
			userListForAutocomplete.append(user.getName());
			userListForAutocomplete.append("\"");

		}
		return userListForAutocomplete;
	}
}
