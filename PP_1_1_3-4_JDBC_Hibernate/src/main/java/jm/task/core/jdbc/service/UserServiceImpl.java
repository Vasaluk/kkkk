package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {
    UserDaoJDBCImpl userDaoJDBCImpl = new UserDaoJDBCImpl();

    public void createUsersTable() throws SQLException, ClassNotFoundException {
        userDaoJDBCImpl.createUsersTable();
    }

    public void dropUsersTable() {
        userDaoJDBCImpl.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException, ClassNotFoundException {
        userDaoJDBCImpl.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) {
        userDaoJDBCImpl.removeUserById(id);
    }

    public List<User> getAllUsers() throws SQLException, ClassNotFoundException {
        return userDaoJDBCImpl.getAllUsers();
    }

    public void cleanUsersTable() {
        userDaoJDBCImpl.cleanUsersTable();
    }
}
