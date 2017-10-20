package lab.service;

import lab.dao.UserDao;
import lab.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserService {

    private UserDao userDao;

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS, isolation = Isolation.READ_COMMITTED)
    public List<User> loadAllUsers() {
        return userDao.selectAll();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.SERIALIZABLE)
    public void saveUser(User user) {
        userDao.insert(user);
    }

    public UserDao getUserDao() {
        return userDao;
    }

    @Autowired
    @Required
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
