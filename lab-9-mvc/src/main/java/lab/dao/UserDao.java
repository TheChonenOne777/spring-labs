package lab.dao;

import java.util.List;

import lab.domain.User;


public interface UserDao {
	
	public void insert(User user);
	
	public User select(int id);
	
	public List<User> selectAll();
	
}
