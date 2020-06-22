package com.example.repository;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.example.domain.User;

/**
 * User情報を取得するレポジトリー.
 * 
 * @author oyamadakenji
 *
 */
@Repository
public class UserRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

	// ■自動生成されたIDを取得できるようになる。
	private SimpleJdbcInsert insert;

	// ■init()
	@PostConstruct
	public void init() {
		insert = new SimpleJdbcInsert((JdbcTemplate) template.getJdbcTemplate());
		insert = insert.withTableName("users").usingGeneratedKeyColumns("id");
	}

	private static final RowMapper<User> USER_ROW_MAPPER = (rs, i) -> {
		User user = new User();

		user.setUserId(rs.getInt("u_user_id"));
		user.setName(rs.getString("u_name"));
		user.setEmail(rs.getString("u_email"));
		user.setPassword(rs.getString("u_password"));

		return user;
	};

	public User save(User user) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(user);
		if (user.getUserId() == null) {
			Number key = insert.executeAndReturnKey(param);
			user.setUserId(key.intValue());
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
		return user;
	}
}
