package com.varvashevich.dao.userDao;

import com.varvashevich.dao.baseDao.BaseDaoImpl;
import com.varvashevich.entity.User;
import com.varvashevich.entity.User_;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Optional;

@Repository
public class UserDaoImpl extends BaseDaoImpl<Long, User> implements UserDao {

    @Override
    public Class<User> getEntityClass() {
        return User.class;
    }

    @Override
    public Optional<User> getByLoginPassword(String login, String password) {
        CriteriaBuilder cb = getSessionFactory().getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<User> criteria = cb.createQuery(User.class);
        Root<User> root = criteria.from(User.class);
        criteria.select(root)
                .where(
                        cb.equal(root.get(User_.login), login),
                        cb.equal(root.get(User_.password), password)
                );
        return getSessionFactory().getCurrentSession().createQuery(criteria).list().stream().findFirst();
    }
}