package com.example.repository.task;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
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
 * タスクの情報を取得するレポジトリ.
 * 
 * @author oyamadakenji
 *
 */
@Repository
public class TaskRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

	private SimpleJdbcInsert insert;

	// ■init()
	@PostConstruct
	public void init() {
		insert = new SimpleJdbcInsert((JdbcTemplate) template.getJdbcTemplate());
		insert = insert.withTableName("tasks").usingGeneratedKeyColumns("id");
	}

	private static final RowMapper<Task> TASK_ROW_MAPPER = (rs, i) -> {
		Task task = new Task();

		task.setId(rs.getInt("t_id"));
		task.setName(rs.getString("t_name"));
		task.setContent(rs.getString("t_content"));
		task.setUserId(rs.getInt("t_user_id"));
		task.setGroupId(rs.getInt("t_group_id"));

		return task;
	};

	/**
	 * タスク情報を登録、更新する.
	 * 
	 * @param task タスク情報が入ったドメイン
	 * @return idの入ったタスク
	 */
	public Task save(Task task) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(task);
		if (task.getId() == null) {
			Number key = insert.executeAndReturnKey(param);
			task.setId(key.intValue());
		} else {
			StringBuilder sql = new StringBuilder();

			sql.append("UPDATE ");
			sql.append(" tasks ");
			sql.append("SET ");
			sql.append(" id =:id, name =:name, content =:content, user_id =:userId, group_id =:groupId ");
			sql.append("WHERE ");
			sql.append(" id =:id ");
			
			template.update(sql.toString(), param);
		}
		return task;
	}
	
	/**
	 * idからタスクを取得する.
	 * 
	 * @param id タスクのid
	 * @return タスク
	 */
	public Task load(Integer id) {

		StringBuilder sql = new StringBuilder();

		sql.append("SELECT ");
//■ Task
		sql.append(" t.id t_id, t.name t_name, t.content t_content, t.user_id t_user_id, t.group_id t_group_id ");
//■ FROM
		sql.append("FROM ");
		sql.append(" tasks t ");
//■ WHERE
		sql.append("WHERE ");
		sql.append(" t.id = :id ");

		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		Task task = template.queryForObject(sql.toString(),param,TASK_ROW_MAPPER);
		
		return task;
	}	

	/**
	 * groupのidからtaskを取得する.
	 * 
	 * @param groupId グループのid
	 * @return タスクの一覧
	 */
	public List<Task> findByGroupId(Integer groupId) {

		StringBuilder sql = new StringBuilder();

		sql.append("SELECT ");
//■ Task
		sql.append(" t.id t_id, t.name t_name, t.content t_content, t.user_id t_user_id, t.group_id t_group_id ");
//■ FROM
		sql.append("FROM ");
		sql.append(" tasks t ");
//■ WHERE
		sql.append("WHERE ");
		sql.append(" t.group_id = :groupId ");
//■ ORDER BY
		sql.append("ORDER BY ");
		sql.append(" t.id ");

		SqlParameterSource param = new MapSqlParameterSource().addValue("groupId", groupId);
		List<Task> taskList = template.query(sql.toString(),param,TASK_ROW_MAPPER);
		
		return taskList;
	}

}
