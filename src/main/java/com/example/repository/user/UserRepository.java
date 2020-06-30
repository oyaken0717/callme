package com.example.repository.user;

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
		insert = insert.withTableName("users").usingGeneratedKeyColumns("user_id");
	}

	private static final RowMapper<User> USER_ROW_MAPPER = (rs, i) -> {
		User user = new User();

		user.setUserId(rs.getInt("u_user_id"));
		user.setName(rs.getString("u_name"));
		user.setEmail(rs.getString("u_email"));
		user.setPassword(rs.getString("u_password"));

		return user;
	};

	/**
	 * ユーザー情報を登録、更新する.
	 * 
	 * @param user ユーザー情報が入ったドメイン
	 * @return idの入ったユーザー
	 */
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
	
	/**
	 * 全ユーザーの情報を取得する.
	 * 
	 * @return 全ユーザーのリスト
	 */
	public List<User> findAll() {
		StringBuilder sql = new StringBuilder();

		sql.append("SELECT ");
		sql.append(" u.user_id u_user_id, u.name u_name, u.email u_email, u.password u_password ");
		sql.append("FROM ");
		sql.append(" users u ");
		
		List<User> userList = template.query(sql.toString(), USER_ROW_MAPPER);
		return userList;		
	}
	
	
	/**
	 * ユーザー情報をemailから検索するメソッド.
	 * 
	 * @param email メールアドレス
	 * @return emailでマッチしたuser
	 */
	public User findByEmail(String email) {
		StringBuilder sql = new StringBuilder();

		sql.append("SELECT ");
		sql.append(" u.user_id u_user_id, u.name u_name, u.email u_email, u.password u_password ");
		sql.append("FROM ");
		sql.append(" users u ");
		sql.append("WHERE ");
		sql.append(" u.email = :email ");
		
		SqlParameterSource param = new MapSqlParameterSource().addValue("email", email);
		List<User> userList = template.query(sql.toString(), param, USER_ROW_MAPPER);
		if (userList.size() == 0) {
			return null;
		}
		return userList.get(0);		
	}
	
    /**
     * 検索フォームから入力されたnameからあいまい検索.
     * 
     * @param name 検索フォームから入力されたname
     * @return 該当するユーザーリスト
     */
	public List<User> findByName(String name) {
		StringBuilder sql = new StringBuilder();

		sql.append("SELECT ");
//■ User
		sql.append(" u.user_id u_user_id, u.name u_name, u.email u_email, u.password u_password ");
//■ FROM
		sql.append("FROM ");
		sql.append(" users u ");
//■ WHERE
		sql.append("WHERE ");
		sql.append(" u.name LIKE :name ");		
		
		SqlParameterSource param = new MapSqlParameterSource().addValue("name", "%" + name + "%");
		List<User> userList = template.query(sql.toString(), param, USER_ROW_MAPPER);
		if (userList.isEmpty()) {
			return null;
		}
		return userList;
	}

}
