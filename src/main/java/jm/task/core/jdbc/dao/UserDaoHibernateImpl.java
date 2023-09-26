package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    private static final SessionFactory sessionFactory = Util.getSession();

    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {                                    //создать таблицу Users
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            String createTableQuery = "CREATE TABLE IF NOT EXISTS users (" +
                    "id INT PRIMARY KEY AUTOINCREMENT," +
                    "name VARCHAR(100) NOT NULL," +
                    "lastName VARCHAR(100) NOT NULL," +
                    "age TINYINT NOT NULL" +
                    ")";
            session.createNativeQuery(createTableQuery).executeUpdate();

            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void dropUsersTable() {                                      //удалить таблицу Users
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            session.createNativeQuery("DROP TABLE IF EXISTS users").executeUpdate();

            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {      //сохранить пользователя
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            User user = new User(name, lastName, age);
            session.save(user);

            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }

    }

    @Override
    public void removeUserById(long id) {                               //удалить пользователя по id
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            User user = session.get(User.class, id);
            session.delete(user);

            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public List<User> getAllUsers() {                                   //получить всех польхователей
        Transaction transaction = null;
        List people = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            people = session.createQuery("FROM User").getResultList();

            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }

        return people;
    }

    @Override
    public void cleanUsersTable() {                                     //очистить таблицу пользователей
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            String deleteQuery = "DELETE FROM users";
            int rowsDeleted = session.createNativeQuery(deleteQuery).executeUpdate();

            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }
}
