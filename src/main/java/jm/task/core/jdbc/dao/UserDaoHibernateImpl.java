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
        Session session = null;
        Transaction transaction = null;
        try {
            SessionFactory sessionFactory = Util.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.createSQLQuery("CREATE TABLE IF NOT EXISTS users (id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY, name VARCHAR (45), lastname VARCHAR (45), age TINYINT )").executeUpdate();
            transaction.commit();
        } catch (HibernateException e) {
            try{
                transaction.rollback();
            }catch (Exception t){
                t.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                session.close();
            } catch (HibernateException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void dropUsersTable() {
        Session session = null;
        Transaction transaction = null;
        try {
            SessionFactory sessionFactory = Util.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.createSQLQuery("DROP TABLE IF EXISTS users").executeUpdate();
            //.addEntity(User.class);
            transaction.commit();
        } catch (HibernateException e) {
            try{
                transaction.rollback();
            }catch (Exception t){
                t.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                session.close();
            } catch (HibernateException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = null;
        Transaction transaction = null;
        try {
            SessionFactory sessionFactory = Util.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(new User(name, lastName, age));
            transaction.commit();
        } catch (HibernateException e) {
            try {
                transaction.rollback();
            } catch (Exception t) {
                t.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                session.close();
            } catch (HibernateException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void removeUserById(long id) {
        Session session = null;
        Transaction transaction = null;
        try {
            SessionFactory sessionFactory = Util.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            Query query = session.createQuery("DELETE from User where id = " + id);
            query.executeUpdate();
            transaction.commit();
        } catch (HibernateException e) {
            try {
                transaction.rollback();
            } catch (Exception t) {
                t.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                session.close();
            } catch (HibernateException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public List<User> getAllUsers() {
        Session session = null;
        Transaction transaction = null;
        List users = null;
        try {
            SessionFactory sessionFactory = Util.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            users = session.createQuery("FROM User ").list();
            transaction.commit();
        } catch (HibernateException e) {
            try {
                transaction.rollback();
            } catch (Exception t) {
                t.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                session.close();
            } catch (HibernateException ex) {
                ex.printStackTrace();
            }
        }
         return users;
    }

        @Override
        public void cleanUsersTable () {
            Session session = null;
            Transaction transaction = null;
            try {
                SessionFactory sessionFactory = Util.getSessionFactory();
                session = sessionFactory.openSession();
                transaction = session.beginTransaction();
                session.createQuery("delete from User").executeUpdate();
                transaction.commit();
            } catch (HibernateException e) {
                try{
                    transaction.rollback();
                }catch (Exception t){
                    t.printStackTrace();
                }
                e.printStackTrace();
            } finally {
                try {
                    session.close();
                } catch (HibernateException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
