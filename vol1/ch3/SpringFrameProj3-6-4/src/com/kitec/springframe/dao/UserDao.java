package com.kitec.springframe.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.kitec.springframe.domain.User;

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
	// : DataAccessUtils.nullableSingleResult() 메서드는 
	//   Spring Framework 5.x 버전 이전에는 존재하지 않았으며, 
	//   대신 DataAccessUtils.singleResult() 메서드가 제공되었습니다. 
	//   반면에 JdbcTemplate.queryForObject() 메서드에서는 Spring Framework 5.x 버전 이후에 추가된 
	//   DataAccessUtils.nullableSingleResult() 메서드를 사용하고 있습니다.
	//   따라서, JdbcTemplate.queryForObject() 메서드를 호출할 때 사용되는 
	//   Spring Framework의 버전과 DataAccessUtils 클래스의 메서드를 호출하는 부분의 
	//   Spring Framework 버전이 서로 일치하지 않을 경우에는 이와 같은 NoSuchMethodError 예외가 발생할 수 있습니다.
	//   이 문제를 해결하기 위해서는, Spring Framework의 버전을 일치시키거나, 
	//   또는 JdbcTemplate.queryForObject() 메서드를 사용하지 않고 대신에 
	//   jdbcTemplate.queryForStream() 메서드를 사용하는 방법 등으로 코드를 수정해야 합니다.
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
	 jdbcTemplate.query 사용해도 무방하나 아래 query 메소드 호출방식은 deprecated 되었음.
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
