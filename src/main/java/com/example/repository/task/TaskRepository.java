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
//        	StringBuilder sql=new StringBuilder();
//                //■①INNER JOIN > 左と右のテーブルがあるのが前提 > 無いとエラー
//                //  ②LEFT OUTER JOIN > 基本LEFT OUTER JOINでOK > 左側に右側をくっつける > 右なくてもエラーにならない。
//                sql.append("UPDATE orders SET user_id=:userId,status=:status,total_price=:totalPrice,order_date=:orderDate,");
//                sql.append("destination_name=:destinationName,destination_email=:destinationEmail,destination_zipcode=:destinationZipcode,");
//                sql.append("destination_address=:destinationAddress,destination_tel=:destinationTel,delivery_time=:deliveryTime,");
//                sql.append("payment_method=:paymentMethod WHERE id=:id");
//
//                //■ 「sql.toString()」に変更注意
//                template.update(sql.toString(), param);
		}
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
