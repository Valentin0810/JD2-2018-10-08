package com.varvashevich.dao.userDao;

import com.varvashevich.dao.baseDao.BaseDao;
import com.varvashevich.entity.User;

import java.util.Optional;

public interface UserDao extends BaseDao<Long, User> {

    Optional<User> getByLoginPassword(String login, String password);
}