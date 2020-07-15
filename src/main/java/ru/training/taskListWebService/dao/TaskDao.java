package ru.training.taskListWebService.dao;

import ru.training.taskListWebService.model.Task;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaskDao {

    public int insertTask(Task task) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/usersdb?useUnicode=true&serverTimezone=UTC",
                    "root", "root");
            PreparedStatement pr = con.prepareStatement("insert into tasks (name,description,executor," +
                    "deadline,status,login) values (?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);

            pr.setString(1, task.getName());
            pr.setString(2, task.getDescription());
            pr.setString(3, task.getExecutor());
            pr.setString(4, task.getDeadline());
            pr.setBoolean(5, task.getStatus());
            pr.setString(6, task.getLogin());

            pr.executeUpdate();

            ResultSet rs = pr.getGeneratedKeys();
            rs.next();
            int taskId = rs.getInt(1);
            task.setTaskId(taskId);

            pr.close();
            con.close();
            return taskId;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public void deleteTaskById (int taskId) {

        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/usersdb?useUnicode=true&serverTimezone=UTC",
                    "root", "root");
            PreparedStatement pr = con.prepareStatement("DELETE from tasks where taskId=(?) ");

            pr.setInt(1, taskId);
            pr.executeUpdate();

            pr.close();
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Task parseTaskFromResultSet(ResultSet rs) throws SQLException {

        Task task = new Task();
        task.setTaskId(Integer.parseInt(rs.getString("taskId")));
        task.setName(rs.getString("name"));
        task.setDescription(rs.getString("description"));
        task.setExecutor(rs.getString("executor"));
        task.setDeadline(rs.getString("deadline"));
        task.setStatus(rs.getBoolean("status"));
        task.setLogin(rs.getString("login"));

        return task;
    }

    public List<Task> findTasksByEmail(String login) {
        List<Task> userTasks = new ArrayList<>();

        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/usersdb?useUnicode=true&serverTimezone=UTC",
                    "root", "root");
            PreparedStatement pr = con.prepareStatement("SELECT * from tasks where login=?");
            pr.setString(1, login);

            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                userTasks.add(parseTaskFromResultSet(rs));
            }

            pr.close();
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userTasks;
    }

    public Task findTaskById(int taskId) {
        Task task = null;

        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/usersdb?useUnicode=true&serverTimezone=UTC",
                    "root", "root");
            PreparedStatement pr = con.prepareStatement("SELECT * from tasks where taskId=?");
            pr.setInt(1, taskId);

            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                task = parseTaskFromResultSet(rs);
            }

            pr.close();
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return task;
    }

    public void updateTask(Task task) {

        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/usersdb?useUnicode=true&serverTimezone=UTC",
                    "root", "root");
            PreparedStatement pr = con.prepareStatement("UPDATE tasks set name=?, description=?, executor=?, " +
                    "deadline=?, status=?, login=? where taskId=?");
            pr.setString(1, task.getName());
            pr.setString(2, task.getDescription());
            pr.setString(3, task.getExecutor());
            pr.setString(4, task.getDeadline());
            pr.setBoolean(5, task.getStatus());
            pr.setString(6, task.getLogin());
            pr.setInt(7, task.getTaskId());

            pr.executeUpdate();

            pr.close();
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
