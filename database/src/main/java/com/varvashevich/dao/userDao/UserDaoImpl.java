package com.varvashevich.dao.userDao;

import com.varvashevich.dao.baseDao.BaseDaoImpl;
import com.varvashevich.entity.User;
import com.varvashevich.entity.User_;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.hibernate.Session;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserDaoImpl extends BaseDaoImpl<Long, User> implements UserDao {

    private static final UserDaoImpl INSTANCE = new UserDaoImpl();
    public static UserDaoImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public Class<User> getEntityClass() {
        return User.class;
    }

    @Override
    public Optional<User> getByLoginPassword(Session session, String login, String password) {
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<User> criteria = cb.createQuery(User.class);
        Root<User> root = criteria.from(User.class);
        criteria.select(root)
                .where(
                        cb.equal(root.get(User_.login), login),
                        cb.equal(root.get(User_.password), password)
                );
        return session.createQuery(criteria).list().stream().findFirst();
    }
}