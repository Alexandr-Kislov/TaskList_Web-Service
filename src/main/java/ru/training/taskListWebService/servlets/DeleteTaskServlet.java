package ru.training.taskListWebService.servlets;

import ru.training.taskListWebService.dao.TaskDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class DeleteTaskServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        int taskId = Integer.parseInt(request.getParameter("id"));

        TaskDao taskDao = new TaskDao();
        taskDao.deleteTaskById(taskId);

        response.sendRedirect(request.getContextPath() + "/tasks");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
