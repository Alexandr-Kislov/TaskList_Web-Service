package ru.training.taskListWebService.servlets;

import ru.training.taskListWebService.dao.TaskDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AllTasksServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        TaskDao taskDao = new TaskDao();

        String login = (String) request.getSession(false).getAttribute("login");

        request.setAttribute("tasks", taskDao.findTasksByEmail(login));
        request.getRequestDispatcher("/mainPage.jsp").forward(request, response);
    }
}
