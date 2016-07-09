package com.qreal.robots.components.database.users.DAO;

import com.qreal.robots.components.authorization.model.auth.User;
import com.qreal.robots.components.authorization.model.auth.UserRole;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {

    public static final String ROLE_USER = "ROLE_USER";

    @Autowired
    private SessionFactory sessionFactory;

    public UserDAOImpl() {
    }

    public UserDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void save(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.save(user);
        UserRole userRole = new UserRole(user, ROLE_USER);
        session.save(userRole);
    }

    public User findByUserName(String username) {
        Session session = sessionFactory.getCurrentSession();

        List<User> users = session.createQuery("from User where username=:username").
                setParameter("username", username).list();
        return (users.size() > 0) ? users.get(0) : null;
    }

    public boolean isUserExist(String username) {
        Session session = sessionFactory.getCurrentSession();

        List<User> users = session.createQuery("from User where username=:username")
                .setParameter("username", username).list();
        return users.size() > 0;

    }

}