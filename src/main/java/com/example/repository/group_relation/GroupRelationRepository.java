package com.example.repository.group_relation;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.example.domain.Group;
import com.example.domain.GroupRelation;

/**
 * グループの参加に関する情報を取得するレポジトリー.
 * 
 * @author oyamadakenji
 *
 */
@Repository
public class GroupRelationRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

	// ■自動生成されたIDを取得できるようになる。
	private SimpleJdbcInsert insert;

	// ■init()
	@PostConstruct
	public void init() {
		insert = new SimpleJdbcInsert((JdbcTemplate) template.getJdbcTemplate());
		insert = insert.withTableName("group_relations").usingGeneratedKeyColumns("id");
	}

	private static final RowMapper<GroupRelation> GROUPRELATION_ROW_MAPPER = (rs, i) -> {
		GroupRelation groupRelation = new GroupRelation();

		groupRelation.setId(rs.getInt("gr_id"));
		groupRelation.setGroupId(rs.getInt("gr_group_id"));
		groupRelation.setUserId(rs.getInt("gr_user_id"));
		groupRelation.setStatus(rs.getInt("gr_status"));

		return groupRelation;
	};

	private static final ResultSetExtractor<List<Group>> GROUP_RESULT_SET_EXTRACTOR = (rs) -> {
		List<Group> groupList = new ArrayList<>();
		// List<●●> ●●List;
		// List<●●> ●●List;

		Integer nowGroupId;
		Integer beforeGroupId = 0;
		while (rs.next()) {
			nowGroupId = rs.getInt("g_id");
			if (nowGroupId != beforeGroupId) {
				// ■ Group
				Group group = new Group();
				group.setId(rs.getInt("g_id"));
				group.setName(rs.getString("g_name"));
				group.setOwnerId(rs.getInt("g_owner_id"));
				groupList.add(group);
			}

//			if (rs.get●●("●●") != 0) {	
//				//■ User				
//			}	
			beforeGroupId = nowGroupId;
		}
		return groupList;
	};

	public GroupRelation save(GroupRelation groupRelation) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(groupRelation);
		if (groupRelation.getId() == null) {
			Number key = insert.executeAndReturnKey(param);
			groupRelation.setId(key.intValue());
		} else {
        	StringBuilder sql=new StringBuilder();
        	
            sql.append("UPDATE ");
            sql.append(" group_relations ");
            sql.append("SET ");
            sql.append(" id=:id, group_id=:groupId, user_id=:userId, status=:status ");
            sql.append("WHERE ");
            sql.append(" id=:id");

            template.update(sql.toString(), param);
		}
		return groupRelation;
	}

	/**
	 * 特定のユーザーのグループ参加の状態によりグループリストを取得する.
	 * 
	 * @param status グループの参加状況 0:招待中 1:参加 9:不参加
	 * @param userId ログインユーザーID
	 * @return グループのリスト
	 * 
	 */
	public List<Group> findBySatusAndUserId(Integer status, Integer userId) {

		StringBuilder sql = new StringBuilder();

		sql.append("SELECT ");
//■ Group		
		sql.append(" g.id g_id, g.name g_name, g.owner_id g_owner_id, ");
//■ GroupRelation		
		sql.append(" gr.id gr_id, gr.group_id gr_group_id, gr.user_id gr_user_id, gr.status gr_status, ");
//■ User		
		sql.append(" u.user_id u_user_id, u.name u_name, u.email u_email, u.password u_password ");
//■ FROM
		sql.append("FROM ");
		sql.append(" groups g ");
//■ JOIN		
		sql.append("LEFT OUTER JOIN group_relations gr ON g.id = gr.group_id ");
		sql.append("LEFT OUTER JOIN users u ON gr.user_id = u.user_id ");
//■ WHERE
		sql.append("WHERE ");
		sql.append(" gr.status = :status ");
		sql.append("AND ");
		sql.append(" u.user_id = :id ");
//■ ORDER BY
		sql.append("ORDER BY ");
		sql.append(" g.id ,u.user_id ");

		SqlParameterSource param = new MapSqlParameterSource().addValue("status", status).addValue("id", userId);
		List<Group> groupList = template.query(sql.toString(), param, GROUP_RESULT_SET_EXTRACTOR);
		return groupList;
	}

	/**
	 * 「招待中」のグループの情報を取得する.
	 * 
	 * 
	 */
	public GroupRelation findByGroupIdAndUserId(Integer groupId, Integer userId) {

		StringBuilder sql = new StringBuilder();

		sql.append("SELECT ");
//■ GroupRelation
		sql.append(" gr.id gr_id, gr.group_id gr_group_id, gr.user_id gr_user_id, gr.status gr_status ");
//■ FROM
		sql.append("FROM ");
		sql.append(" group_relations gr ");
//■ WHERE
		sql.append("WHERE ");
		sql.append(" gr.group_id = :groupId ");
		sql.append("AND ");
		sql.append(" gr.user_id = :userId ");

		SqlParameterSource param = new MapSqlParameterSource().addValue("groupId", groupId).addValue("userId", userId);
		GroupRelation groupRelation = template.queryForObject(sql.toString(), param, GROUPRELATION_ROW_MAPPER);
		return groupRelation;
	}

}
