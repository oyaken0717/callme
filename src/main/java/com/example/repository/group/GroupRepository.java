package com.example.repository.group;

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

/**
 * グループ情報を取得するレポジトリー.
 * 
 * @author oyamadakenji
 *
 */
@Repository
public class GroupRepository {

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
		return group;
	}

	/**
	 * idからグループ情報を特定する.
	 * 
	 * @param id グループのid情報
	 * @return idで特定されたグループ情報
	 */
	public Group load(Integer id) {
		
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
		List<Group> groupList = template.query(sql.toString(),param,GROUP_ROW_MAPPER);
		if (groupList.size()==0) {
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
	public List<Group> findByOwnerId(Integer id){
		
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
		List<Group> groupList = template.query(sql.toString(),param,GROUP_ROW_MAPPER);
		return groupList;
	} 

}
