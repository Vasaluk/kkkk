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
        try(Session session = Util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            String sql = "CREATE TABLE IF NOT EXISTS root2 " +
                    "(id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                    "name VARCHAR(50) NOT NULL, lastName VARCHAR(50) NOT NULL, " +
                    "age TINYINT NOT NULL)";

            session.createSQLQuery(sql).executeUpdate();

            transaction.commit();
        } catch (HibernateException e) {
            System.out.println("An error has occured on Table Creation");
        }
    }
    @Override
    public void dropUsersTable() {
        try(Session session = Util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            String sql = "DROP TABLE IF EXISTS root2";

            session.createSQLQuery(sql).executeUpdate();

            transaction.commit();
        } catch (HibernateException e) {
            System.out.println("An error has occured on Drop Creation");
        }
    }
    @Override
    public void saveUser(String name, String lastName, byte age) {
        try(Session session = Util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            User hh = new User(name, lastName, age);
            session.save(hh);
            transaction.commit();
            System.out.println("User с именем – " + name +" добавлен в базу данных");
        } catch (HibernateException e) {
            System.out.println("An error has occured on Save User");
        }

    }

    @Override
    public void removeUserById(long id) {
        try(Session session = Util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            session.delete(session.get(User.class, id));

            transaction.commit();
        } catch (HibernateException e) {
            System.out.println("An error has occured on Remove User By Id");
        }
    }
    @Override
    public List<User> getAllUsers() {
        List<User> g = null;
        try(Session session = Util.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            transaction.commit();
            g = session.createQuery("from User ", User.class).list();
        } catch (HibernateException e) {
            System.out.println("An error has occured on Get All Users");
        }
        return g;
    }

    @Override
    public void cleanUsersTable() {

        try(Session session = Util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            String sql = "TRUNCATE TABLE root2";

            session.createSQLQuery(sql).executeUpdate();

            transaction.commit();
        } catch (HibernateException e) {
            System.out.println("An error has occured on Clean Users Table");
        }
    }
}
