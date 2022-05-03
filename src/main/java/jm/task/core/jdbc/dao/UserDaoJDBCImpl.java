package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String ew = "CREATE TABLE root2 (id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(20), " +
                "lastName VARCHAR(20), age TINYINT)";

        try (Connection conn = Util.getConnection();Statement statement = conn.createStatement()) {
            statement.executeUpdate(ew);

        } catch (SQLException e) {
            System.out.println("An error has occured on Table Creation");
        } catch (ClassNotFoundException e) {
            System.out.println("An Mysql drivers were not found");
        }

    }

    public void dropUsersTable() {
        String ew = "DROP TABLE IF EXISTS root2";

        try (Connection conn = Util.getConnection();Statement statement = conn.createStatement()) {
            statement.executeUpdate(ew);

        } catch (SQLException e) {
            System.out.println("An error has occured on Drop Creation");
        } catch (ClassNotFoundException e) {
            System.out.println("An Mysql drivers were not found");
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        int id1 =0;
        String query = "INSERT INTO root2 (id, name, lastName, age) VALUES (?,?,?,?)";
        try (Connection coc = Util.getConnection();PreparedStatement state = coc.prepareStatement(query)) {

            ResultSet gg = state.executeQuery("select * from root2");

            while (gg.next()) {
                id1 = (int) gg.getLong(1);
            }
            state.setLong(1,++id1);
            state.setString(2,name);
            state.setString( 3,lastName);
            state.setByte( 4,age);
            state.execute();
            System.out.println("User с именем – " + name +" добавлен в базу данных");

        } catch (SQLException e) {
            System.out.println("An error has occured on Save User");

        } catch (ClassNotFoundException e) {
            System.out.println("An Mysql drivers were not found");
        }

    }

    public void removeUserById(long id) {
        String ew = ("DELETE FROM root2 WHERE id = ?");
        try (Connection conn = Util.getConnection();PreparedStatement pre = conn.prepareStatement(ew)) {
            pre.setLong(1, id);
            int result_set = pre.executeUpdate();

        } catch (SQLException e) {
            System.out.println("An error has occured on Remove User By Id");
        } catch (ClassNotFoundException e) {
            System.out.println("An Mysql drivers were not found");
        }
    }

    public List<User> getAllUsers() {
        List<User> rrr = new ArrayList<>();
        String ew = "select * from root2";
        try (Connection conn = Util.getConnection(); Statement state = conn.createStatement();
             ResultSet ress = state.executeQuery(ew)) {
            while (ress.next()) {
                User ee = new User();
                ee.setId(ress.getLong("id"));
                ee.setName(ress.getString("name"));
                ee.setLastName(ress.getString("lastName"));
                ee.setAge(ress.getByte("age"));

                rrr.add(ee);
            }
        }catch (SQLException e) {
            System.out.println("An error has occured on Get All Users");
        } catch (ClassNotFoundException e) {
            System.out.println("An Mysql drivers were not found");
        }
        return rrr;
    }

    public void cleanUsersTable() {
        String query = "Truncate table root2";
        try (Connection coc = Util.getConnection();Statement stmt = coc.createStatement()) {
            stmt.execute(query);

        } catch (SQLException e) {
            System.out.println("An error has occured on Clean Users Table");
        } catch (ClassNotFoundException e) {
            System.out.println("An Mysql drivers were not found");
        }
    }
}
