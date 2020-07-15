package ru.training.taskListWebService.dao;

import ru.training.taskListWebService.model.User;

import java.sql.*;

public class UserDao {

    public User findUserByLogin(String login) throws SQLException {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/usersdb?useUnicode=true&serverTimezone=UTC",
                "root", "root");

        User user = null;
        if (con != null) {
            try {
                PreparedStatement pr = con.prepareStatement("SELECT * FROM users where email=?");
                pr.setString(1,login);

                ResultSet resultSet = pr.executeQuery();
                if(resultSet.next()) {
                    user = new User();
                    user.setUserId(resultSet.getInt("idusers"));
                    user.setLogin(login);
                    user.setPassword(resultSet.getString("password"));
                    user.setUserName(resultSet.getString("username"));

                    pr.close();
                    con.close();
                    return user;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return user;
    }

    public void registerNewUser(User user) throws SQLException {

        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/usersdb?useUnicode=true&serverTimezone=UTC",
                "root", "root");

        if (con != null) {
            try {
                PreparedStatement pr = con.prepareStatement("insert into users (idusers,email,password,username) " +
                        "values (?,?,?,?)");
                pr.setString(1,String.valueOf(user.getUserId()));
                pr.setString(2,user.getLogin());
                pr.setString(3,user.getPassword());
                pr.setString(4,user.getUserName());

                pr.executeUpdate();
                pr.close();
                con.close();

            } catch (SQLException e) {
                e.printStackTrace();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
