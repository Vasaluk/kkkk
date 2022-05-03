package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        UserService hg = new UserServiceImpl();

        hg.createUsersTable();

        hg.saveUser("Name1", "LastName1", (byte) 20);
        hg.saveUser("Name2", "LastName2", (byte) 25);
        hg.saveUser("Name3", "LastName3", (byte) 31);
        hg.saveUser("Name4", "LastName4", (byte) 38);

        hg.removeUserById(12);

        List<User> fff = hg.getAllUsers();
        fff.forEach(x -> System.out.println(x));

        hg.cleanUsersTable();
        hg.dropUsersTable();
    }
}
