package ru.training.taskListWebService.servlets;

import ru.training.taskListWebService.dao.TaskDao;
import ru.training.taskListWebService.model.Task;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class EditTaskServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        Task task = new Task();
        task.setTaskId(Integer.parseInt(request.getParameter("id")));
        task.setName(request.getParameter("name"));
        task.setDescription(request.getParameter("description"));
        task.setExecutor(request.getParameter("executor"));
        task.setDeadline(request.getParameter("deadline"));
        task.setStatus(!request.getParameter("status").equals("false"));
        task.setLogin(request.getParameter("login"));

        TaskDao taskDao = new TaskDao();
        taskDao.updateTask(task);

        response.sendRedirect(request.getContextPath() + "/tasks");
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        int taskId = Integer.parseInt(request.getParameter("id"));

        TaskDao taskDao = new TaskDao();
        request.setAttribute("tasks", taskDao.findTaskById(taskId));
        request.getRequestDispatcher("/editTaskPage.jsp").forward(request, response);

    }
}

