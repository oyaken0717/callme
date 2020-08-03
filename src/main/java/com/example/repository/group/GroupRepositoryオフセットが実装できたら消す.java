package com.example.repository.group;

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
import com.example.domain.Task;

/**
 * グループ情報を取得するレポジトリー.
 * 
 * @author oyamadakenji
 *
 */
/**
 * @author oyamadakenji
 *
 */
@Repository
public class GroupRepositoryオフセットが実装できたら消す {

	@Autowired
	private NamedParameterJdbcTemplate template;

	// ■自動生成されたIDを取得できるようになる。
	private SimpleJdbcInsert insert;

	// ■init()
	@PostConstruct
	public void init() {
		insert = new SimpleJdbcInsert((JdbcTemplate) template.getJdbcTemplate());
		insert = insert.withTableName("groups").usingGeneratedKeyColumns("id");
	}

	private static final RowMapper<Group> GROUP_ROW_MAPPER = (rs, i) -> {
		Group group = new Group();

		group.setId(rs.getInt("g_id"));
		group.setName(rs.getString("g_name"));
		group.setOwnerId(rs.getInt("g_owner_id"));

		return group;
	};

	private static final ResultSetExtractor<List<Group>> GROUP_RESULT_SET_EXTRACTOR = (rs) -> {
		List<Group> groupList = new ArrayList<>();
		List<Task> taskList = null;
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
				
				taskList = new ArrayList<>();
				group.setTaskList(taskList);

				groupList.add(group);
			}

			if (rs.getInt("t_id") != 0) {	
				//■ Task
				Task task = new Task();
				task.setId(rs.getInt("t_id"));
				task.setName(rs.getString("t_name"));
				task.setContent(rs.getString("t_content"));
				task.setUserId(rs.getInt("t_user_id"));
				task.setGroupId(rs.getInt("t_group_id"));
				
				taskList.add(task);
			}	
			beforeGroupId = nowGroupId;
		}
		return groupList;
	};

	/**
	 * グループ情報を登録、更新する.
	 * 
	 * @param group グループ情報が入ったドメイン
	 * @return idの入ったグループ
	 */
	public Group save(Group group) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(group);
		if (group.getId() == null) {
			Number key = insert.executeAndReturnKey(param);
			group.setId(key.intValue());
		} else {
			StringBuilder sql = new StringBuilder();

			sql.append("UPDATE ");
			sql.append(" groups ");
			sql.append("SET ");
			sql.append(" id=:id, name=:name, owner_id=:ownerId ");
			sql.append("WHERE ");
			sql.append(" id =:id ");

            template.update(sql.toString(), param);
		}
		return group;
	}

	/**
	 * idからグループ情報を特定する.
	 * 
	 * @param id グループのid情報
	 * @return idで特定されたグループ情報
	 */
	public Group maenoload(Integer id) {

		StringBuilder sql = new StringBuilder();

		sql.append("SELECT ");
//■ Group
		sql.append(" g.id g_id, g.name g_name, g.owner_id g_owner_id ");
//■ FROM
		sql.append("FROM ");
		sql.append(" groups g ");
//■ WHERE
		sql.append("WHERE ");
		sql.append(" g.id = :id ");
//■ ORDER BY
		sql.append("ORDER BY ");
		sql.append(" g.id DESC ");

		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		List<Group> groupList = template.query(sql.toString(), param, GROUP_ROW_MAPPER);
		if (groupList.size() == 0) {
			return null;
		}
		return groupList.get(0);
	}
	
	
	/**
	 * idからグループの詳細とグループに登録されているタスクを取得する.
	 * 
	 * @param id グループのid情報
	 * @return idで特定されたグループ情報
	 */
	public Group load(Integer id) {
		
		StringBuilder sql = new StringBuilder();

		sql.append("SELECT ");
//■ Group
		sql.append(" g.id g_id, g.name g_name, g.owner_id g_owner_id, ");
//■ Task
		sql.append(" t.id t_id, t.name t_name, t.content t_content, t.user_id t_user_id, t.group_id t_group_id ");
//■ FROM
		sql.append("FROM ");
		sql.append(" groups g ");
//■ JOIN
		sql.append("LEFT OUTER JOIN tasks t ON t.group_id = g.id ");
//■ WHERE
		sql.append("WHERE ");
		sql.append(" g.id = :id ");
//■ ORDER BY
		sql.append("ORDER BY ");
		sql.append(" t.id ");
		
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		List<Group> groupList = template.query(sql.toString(), param, GROUP_RESULT_SET_EXTRACTOR);
		if (groupList == null) {
			return null;
		}
		return groupList.get(0);
	}

	/**
	 * OwnerIdから所属しているグループを取得する.
	 * 
	 * @param id ユーザーのid
	 * @return ユーザーがオーナーのグループのリスト
	 */
	public List<Group> findByOwnerId(Integer id) {

		StringBuilder sql = new StringBuilder();

		sql.append("SELECT ");
//■ Group
		sql.append(" g.id g_id, g.name g_name, g.owner_id g_owner_id ");
//■ FROM
		sql.append("FROM ");
		sql.append(" groups g ");
//■ WHERE
		sql.append("WHERE ");
		sql.append(" g.owner_id = :id ");
//■ ORDER BY
		sql.append("ORDER BY ");
		sql.append(" g.id ");

		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		List<Group> groupList = template.query(sql.toString(), param, GROUP_ROW_MAPPER);
		return groupList;
	}

	/**
	 * ユーザーidから参加しているグループのリストを取得する.
	 * 
	 * @param id ユーザーのid
	 * @return 参加しているユーザーのリスト
	 */
	public List<Group> findByUserId(Integer id,Integer status) {

		StringBuilder sql = new StringBuilder();

		sql.append("SELECT ");
//■ Group		
		sql.append(" g.id g_id, g.name g_name, g.owner_id g_owner_id, ");
//■ GroupRelation		
		sql.append(" gr.id gr_id, gr.group_id gr_group_id, gr.user_id gr_user_id, gr.status gr_status, ");
//■ Task
		sql.append(" t.id t_id, t.name t_name, t.content t_content, t.user_id t_user_id, t.group_id t_group_id ");
//■ FROM
		sql.append("FROM ");
		sql.append(" groups g ");
//■ JOIN		
		sql.append("LEFT OUTER JOIN group_relations gr ON g.id = gr.group_id ");
		sql.append("LEFT OUTER JOIN tasks t ON g.id = t.group_id ");		
//■ WHERE
		sql.append("WHERE ");
		sql.append(" gr.user_id =:id ");
		sql.append("AND ");
		sql.append(" gr.status =:status ");
//■ ORDER BY
		sql.append("ORDER BY ");
		sql.append(" g.id ");

		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id).addValue("status", status);
		List<Group> groupList = template.query(sql.toString(), param, GROUP_RESULT_SET_EXTRACTOR);
		return groupList;
	}
}
