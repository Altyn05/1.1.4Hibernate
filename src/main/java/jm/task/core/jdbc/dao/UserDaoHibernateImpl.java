package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.*;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {


    public UserDaoHibernateImpl() {
    }


    @Override
    public void createUsersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS user (id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY, name VARCHAR (45), lastname VARCHAR (45), age INT )";

        SessionFactory sessionFactory = Util.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query quary = session.createSQLQuery(sql);
        //.addEntity(User.class);
        quary.executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void dropUsersTable() {
        String sql = "DROP TABLE IF EXISTS user";
        SessionFactory sessionFactory = Util.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query quary = session.createSQLQuery(sql);
        //.addEntity(User.class);
        quary.executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        SessionFactory sessionFactory = Util.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        User user = new User(name,lastName,age);
        session.save(user);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void removeUserById(long id) {
        SessionFactory sessionFactory = Util.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        User user = (User) session.get(User.class, id);
        session.delete(user);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<User> getAllUsers() {

        List users = null;

        SessionFactory sessionFactory = Util.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        users = session.createQuery("FROM User").list();
        session.getTransaction().commit();
        session.close();
        return users;
    }

    @Override
    public void cleanUsersTable() {
        SessionFactory sessionFactory = Util.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query quary = session.createQuery("delete from User");
        quary.executeUpdate();
        session.getTransaction().commit();
        session.close();

    }
}