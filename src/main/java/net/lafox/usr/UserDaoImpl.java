package net.lafox.usr;

import net.lafox.muza.entity.Role;
import net.lafox.muza.entity.User;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Service
public class UserDaoImpl implements UserDao {
    private static Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

    @Qualifier("sessionFactory")
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional(readOnly = true)
    public User findUserByUsername(String username) {
        Session session = sessionFactory.getCurrentSession();
        User user = (User) session.createQuery("from User user where user.username=?")
                .setParameter(0, username)
                .uniqueResult();

        if (logger.isDebugEnabled()) {
            if (user == null) {
                logger.trace("User not found: " + username);
            } else {
                logger.trace("User found: " + username);
                for (Role role : user.getRoles()) {
                    logger.trace("\tUser role: " + role.getRoleName());
                }
            }
        }
        return user;
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<User> list(String roleName) {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(User.class);
        List<User> res = new ArrayList<>();
        if (roleName != null && !roleName.isEmpty()) {
            Role role = (Role) sessionFactory.getCurrentSession().get(Role.class, roleName);

            //noinspection unchecked
            for (User user : (List<User>) criteria.list()) {
                Hibernate.initialize(user.getRoles());
                if (role != null && user.getRoles().contains(role)) {
                    res.add(user);
                }
            }
        }
        return res;
    }

    @Override
    @Transactional(readOnly = true)
    public User findUserByToken(String token) {
        Session session = this.sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(User.class);
        return  (User) criteria.add(Restrictions.eq("token",token)).uniqueResult();
    }
}
