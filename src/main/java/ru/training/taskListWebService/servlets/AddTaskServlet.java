package ru.training.taskListWebService.servlets;

import ru.training.taskListWebService.dao.TaskDao;
import ru.training.taskListWebService.model.Task;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddTaskServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=UTF-8");

        Task task = new Task();
        task.setName(request.getParameter("name"));
        task.setDescription(request.getParameter("description"));
        task.setExecutor(request.getParameter("executor"));
        task.setDeadline(request.getParameter("deadline"));
        task.setStatus(!request.getParameter("status").equals("false"));

        String login = (String) request.getSession(false).getAttribute("login");
        task.setLogin(login);

        TaskDao taskDao = new TaskDao();
        taskDao.insertTask(task);

        response.sendRedirect(request.getContextPath() + "/tasks");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
