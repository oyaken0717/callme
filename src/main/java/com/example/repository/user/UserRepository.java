package com.example.repository.user;

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
		user.setVersion(rs.getInt("u_version"));

		return user;
	};
	
	private static final ResultSetExtractor<List<User>> User_RESULT_SET_EXTRACTOR = (rs) -> {			
		List<User> userList = new ArrayList<>();		
//		List<★★> ★★List = null;		
//		List<●●> ●●List = null;		
				
		Integer nowUserId;		
		Integer beforeUserId = 0;		
		while (rs.next()) {		
			nowUserId  = rs.getInt("u_user_id");	
			if (nowUserId != beforeUserId) {	
				User user = new User();
				user.setUserId(rs.getInt("u_user_id"));
				user.setName(rs.getString("u_name"));
				user.setEmail(rs.getString("u_email"));
				user.setPassword(rs.getString("u_password"));	
				user.setVersion(rs.getInt("u_version"));
//				★★List = new ArrayList;	
//				●●.set★★List(★★List);	
				
				userList.add(user);	
			}	
				
//			if (rs.get★★("★★") != 0) {	
//				
//				
//				★★List.add(★★);	
//			}	
			beforeUserId = nowUserId;	
		}		
		return userList;		
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
			StringBuilder sql = new StringBuilder();

			sql.append("UPDATE ");
			sql.append(" users ");
			sql.append("SET ");
			sql.append(" user_id =:userId , name =:name , email =:email , password =:password, version =:version + 1 ");
			sql.append("WHERE ");
			sql.append(" user_id =:userId ");
			sql.append("AND ");
			sql.append(" version =:version ");
			
            template.update(sql.toString(), param);
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
		sql.append(" u.user_id u_user_id, u.name u_name, u.email u_email, u.password u_password, u.version u_version ");
		sql.append("FROM ");
		sql.append(" users u ");
		
		List<User> userList = template.query(sql.toString(), USER_ROW_MAPPER);
		return userList;		
	}

	/**
	 * 今までグループに招待されていなかった人を取得する.
	 * 
	 * @param id ログインユーザーのid
	 * @return 今までグループに招待されていなかったユーザー一覧
	 */
	public List<User> findByLikeNameAndUserIdAndGroupId(String name,Integer userId,Integer groupId) {

		StringBuilder sql = new StringBuilder();

		sql.append("SELECT ");
		sql.append(" u.user_id u_user_id, u.name u_name, u.email u_email, u.password u_password, u.version u_version ");
//■ FROM		
		sql.append("FROM ");
		sql.append(" users u ");
//■ WHERE
		sql.append("WHERE ");
		sql.append(" u.name LIKE :name ");
		sql.append("AND ");
		sql.append(" u.user_id != :userId ");
		sql.append("AND ");
//■ NOT IN -以下のSELECTに当てはまるユーザー「以外」-		
		sql.append(" u.user_id NOT IN ( ");
		sql.append("  SELECT ");
		sql.append("   user_id ");
//■ FROM
		sql.append("  FROM ");
		sql.append("   group_relations ");
//■ WHERE		
		sql.append("  WHERE ");
		sql.append("   group_id =:groupId ");
//■ AND		
		sql.append("  AND ");
		sql.append("   status IN (0,1) ");
		sql.append(" ) ");
		
		SqlParameterSource param = new MapSqlParameterSource().addValue("name","%" + name + "%").addValue("userId",userId).addValue("groupId",groupId);
		List<User> userList = template.query(sql.toString(),param,USER_ROW_MAPPER);
		return userList;		
	}
		
	/**
	 * idからユーザー情報を取得する.
	 * 
	 * @param id
	 * @return ユーザー
	 */
	public User load(Integer id) {
		StringBuilder sql = new StringBuilder();

		sql.append("SELECT ");
		sql.append(" u.user_id u_user_id, u.name u_name, u.email u_email, u.password u_password, u.version u_version ");
		sql.append("FROM ");
		sql.append(" users u ");
		sql.append("WHERE ");
		sql.append(" u.user_id = :id ");
		
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		User user = template.queryForObject(sql.toString(), param, USER_ROW_MAPPER);
		return user;		
	} 

	/**
	 * 楽観処理の為に悲観処理を入れる<br>
	 * FOR UPDATE
	 * 
	 * @param id ユーザーのid
	 * @return ユーザー
	 */
	public User lockToLoad(Integer id) {
		StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT ");
		sql.append(" u.user_id u_user_id, u.name u_name, u.email u_email, u.password u_password, u.version u_version ");
		sql.append("FROM ");
		sql.append(" users u ");
		sql.append("WHERE ");
		sql.append(" u.user_id = :id ");
		sql.append("FOR UPDATE ");
		
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		User user = template.queryForObject(sql.toString(), param, USER_ROW_MAPPER);
		return user;		
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
		sql.append(" u.user_id u_user_id, u.name u_name, u.email u_email, u.password u_password, u.version u_version ");
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
		sql.append(" u.user_id u_user_id, u.name u_name, u.email u_email, u.password u_password, u.version u_version ");
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

	/**
	 * グループに所属している全ユーザー情報を取得する.
	 * 
	 * @param groupId グループid
	 * @return ユーザーリスト
	 */
	public List<User> findByGroupId(Integer groupId) {
		StringBuilder sql = new StringBuilder();

		sql.append("SELECT ");
//■ User
		sql.append(" u.user_id u_user_id, u.name u_name, u.email u_email, u.password u_password, u.version u_version ");
//■ FROM
		sql.append("FROM ");
		sql.append(" users u ");
//■ JOIN
		sql.append("LEFT OUTER JOIN group_relations gr ON u.user_id = gr.user_id ");
//■ WHERE
		sql.append("WHERE ");
		sql.append(" gr.group_id = :groupId ");		
		sql.append("AND ");
		sql.append(" gr.status = 1 ");		

		SqlParameterSource param = new MapSqlParameterSource().addValue("groupId",groupId);
		List<User> userList = template.query(sql.toString(), param, User_RESULT_SET_EXTRACTOR);
		if (userList.isEmpty()) {
			return null;
		}
		return userList;
	}
	
	/**
	 * ユーザー情報を削除する.
	 * 
	 * @param userId ユーザーid
	 */
	public void delete(Integer userId) {
		StringBuilder sql = new StringBuilder();

//		sql.append("DELETE ");
//		sql.append("FROM ");
//		sql.append(" users ");
//		sql.append("WHERE ");
//		sql.append(" user_id  =:userId ");

		sql.append("WITH ");
		sql.append(" deleted ");
		sql.append("AS ");
		sql.append(" ( DELETE FROM users WHERE user_id =:userId RETURNING user_id ) ");
		sql.append(" ");
		sql.append("DELETE ");
		sql.append("FROM ");
		sql.append(" group_relations ");
		sql.append("WHERE ");
		sql.append(" user_id ");
		sql.append("IN ");
		sql.append(" ( SELECT user_id FROM deleted ) ");		
		
		SqlParameterSource param = new MapSqlParameterSource().addValue("userId", userId);
		template.update(sql.toString(),param);		
	}
	
}
