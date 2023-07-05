package com.intheeast.springframe.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.intheeast.springframe.domain.User;

public class UserDao {
	
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
			
		this.dataSource = dataSource;
	}	
	
	public void add(final User user) throws SQLException {
		this.jdbcTemplate.update("insert into users(id, name, password) values(?,?,?)",
						user.getId(), 
						user.getName(), 
						user.getPassword());
	}	
	
	// java.lang.NoSuchMethodError:
	// org.springframework.dao.support.DataAccessUtils.nullableSingleResult()
	// : DataAccessUtils.nullableSingleResult() �޼���� 
	//   Spring Framework 5.x ���� �������� �������� �ʾ�����, 
	//   ��� DataAccessUtils.singleResult() �޼��尡 �����Ǿ����ϴ�. 
	//   �ݸ鿡 JdbcTemplate.queryForObject() �޼��忡���� Spring Framework 5.x ���� ���Ŀ� �߰��� 
	//   DataAccessUtils.nullableSingleResult() �޼��带 ����ϰ� �ֽ��ϴ�.
	//   ����, JdbcTemplate.queryForObject() �޼��带 ȣ���� �� ���Ǵ� 
	//   Spring Framework�� ������ DataAccessUtils Ŭ������ �޼��带 ȣ���ϴ� �κ��� 
	//   Spring Framework ������ ���� ��ġ���� ���� ��쿡�� �̿� ���� NoSuchMethodError ���ܰ� �߻��� �� �ֽ��ϴ�.
	//   �� ������ �ذ��ϱ� ���ؼ���, Spring Framework�� ������ ��ġ��Ű�ų�, 
	//   �Ǵ� JdbcTemplate.queryForObject() �޼��带 ������� �ʰ� ��ſ� 
	//   jdbcTemplate.queryForStream() �޼��带 ����ϴ� ��� ������ �ڵ带 �����ؾ� �մϴ�.
	/*public Optional<User> get(String id) {
		String sql = "select * from users where id = ?";
		User user = jdbcTemplate.queryForObject(sql, userRowMapper(), id);
        try {
            return Optional.of(user);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }		
	}*/
	
	private RowMapper<User> userRowMapper() {
        return ((rs, rowNum) -> {
        	User user = new User();
        	user.setId(rs.getString("id"));
        	user.setName(rs.getString("name"));
        	user.setPassword(rs.getString("password"));            
            return user;
        });
    }	
	
	/*private RowMapper<User> userRowMapper = (rs, rowNum) -> {
	    User user = new User();
	    user.setId(rs.getString("id"));
	    user.setName(rs.getString("name"));
	    user.setPassword(rs.getString("password"));
	    return user;
	};*/
	
	public Optional<User> get(String id) throws SQLException {
	    String sql = "select * from users where id = ?";	    		
	    
	    try (Stream<User> stream = jdbcTemplate.queryForStream(sql, userRowMapper(), id)) {
	        return stream.findFirst();
	    } catch (DataAccessException e) {
	        return Optional.empty();
	    }
	}

	
	/*
	 jdbcTemplate.query ����ص� �����ϳ� �Ʒ� query �޼ҵ� ȣ������ deprecated �Ǿ���.
	public Optional<User> get(String id) {
	    String sql = "select * from users where id = ?";
	    List<User> users = jdbcTemplate.query(sql, new Object[] { id }, new BeanPropertyRowMapper<>(User.class));
	    if (users.isEmpty()) {
	        return Optional.empty();
	    } else {
	        return Optional.of(users.get(0));
	    }
	}
	*/
	
	public void deleteAll() throws SQLException {
		this.jdbcTemplate.update("delete from users");
	}
	
	
	
	/*
	 java.lang.NoSuchMethodError:
	   org.springframework.dao.support.DataAccessUtils.nullableSingleResult()
	public int getCount()throws SQLException {
		String sql = "select count(*) from users";
		Integer count = this.jdbcTemplate.queryForObject(sql, Integer.class);
		
		return (count != null ? count.intValue() : 0);
		//return this.jdbcTemplate.queryForObject("SELECT COUNT(*) FROM users", Integer.class);
		
	}*/
	
	public int getCount() throws SQLException {
	    List<Integer> result = jdbcTemplate.query("select count(*) from users", 
	    		(rs, rowNum) -> rs.getInt(1));
	    return (int) DataAccessUtils.singleResult(result);
	}
	
	
	public List<User> getAll() throws DataAccessException, SQLException {
		return this.jdbcTemplate.query("select * from users order by id",
				userRowMapper()
		);
	}

	
	/*public List<User> getAll() {
		return this.jdbcTemplate.query("select * from users order by id",
				new RowMapper<User>() {
					public User mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						User user = new User();
						user.setId(rs.getString("id"));
						user.setName(rs.getString("name"));
						user.setPassword(rs.getString("password"));
						return user;
					}
				});
	}*/
}
