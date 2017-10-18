package lab.dao;

import lab.domain.User;

import java.util.List;


public interface UserDao {

    void insert(User user);

    User select(int id);

    List<User> selectAll();

}
