package services;

import dao.UserDao;
import models.User;

import java.util.List;

public class UserService {
    private final UserDao userDao = new UserDao();

    public UserService() {
    }

    public User findUser(int id){return userDao.findById(id);}
    public void saveUser(User user)  {userDao.save(user); }
    public void deleteUser(User user){userDao.delete(user); }
    public void updateUser(User user){userDao.update(user); }
    public List<User> findAllUser(){return userDao.findAll(); }
}
