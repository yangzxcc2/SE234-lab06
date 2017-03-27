package camt.se234.unittest.service;


import camt.se234.unittest.dao.UserDao;
import camt.se234.unittest.dao.UserDaoImpl;
import camt.se234.unittest.entity.User;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

public class UserServiceImpl {

    UserDao userDao;
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public User login(String username, String password){
        List<User> users = userDao.getUsers();
        return users.stream().filter(u -> (u.getUsername().equals(username) && u.getPassword().equals(password)))
                .findFirst().orElse(null);
    }

    public boolean isAbleToGoToPub(User user, LocalDate date){
        return ChronoUnit.YEARS.between(user.getDateOfBirth(),date)>=20;
    }

    public List<User> getPubAllowanceUser(LocalDate date){
        List<User> users = userDao.getUsers();

        return users.stream().filter(u-> isAbleToGoToPub(u,date)).collect(Collectors.toList());
    }
}
