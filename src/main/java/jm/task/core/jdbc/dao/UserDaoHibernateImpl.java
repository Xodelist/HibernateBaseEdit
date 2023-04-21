package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {


    public UserDaoHibernateImpl() {
    }


    @Override
    public void createUsersTable() {
        try (SessionFactory factory = Util.getSessionFactory();
             Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            session.createSQLQuery("CREATE TABLE if not exists users" +
                    "(id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY UNIQUE," +
                    "name VARCHAR(20) NOT NULL ," +
                    "lastname VARCHAR(20) NOT NULL ," +
                    "age INT NOT NULL )").executeUpdate();
            session.getTransaction().commit();
        }
    }

    @Override
    public void dropUsersTable() {
        try (SessionFactory factory = Util.getSessionFactory();
             Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            session.createSQLQuery("DROP TABLE if exists users").executeUpdate();
            session.getTransaction().commit();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (SessionFactory factory = Util.getSessionFactory();
             Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            session.save(new User(name,lastName,age));
            session.getTransaction().commit();
            System.out.println("User с именем " + name + " добавлен в базу данных");
        }
    }

    @Override
    public void removeUserById(long id) {
        try (SessionFactory factory = Util.getSessionFactory();
             Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Query query = session.createQuery("DELETE User WHERE id = :id");
            query.setParameter("id", id);
            query.executeUpdate();
            session.getTransaction().commit();
        }
    }

    @Override
    public List<User> getAllUsers() {
        try (SessionFactory factory = Util.getSessionFactory();
             Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            return session.createQuery("FROM User ").getResultList();
        }


    }

    @Override
    public void cleanUsersTable() {
        try (SessionFactory factory = Util.getSessionFactory();
             Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            session.createQuery("DELETE User").executeUpdate();
            session.getTransaction().commit();
        }
    }
}
