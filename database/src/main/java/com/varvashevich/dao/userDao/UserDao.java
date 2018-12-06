package com.varvashevich.dao.userDao;

import com.varvashevich.dao.baseDao.BaseDao;
import com.varvashevich.entity.User;
import org.hibernate.Session;

import java.util.Optional;

public interface UserDao extends BaseDao<Long, User> {

    Optional<User> getByLoginPassword(Session session, String login, String password);
}