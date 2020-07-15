package ru.training.taskListWebService.servlets;

import org.apache.commons.codec.digest.DigestUtils;
import ru.training.taskListWebService.dao.UserDao;
import ru.training.taskListWebService.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            User user = new User();
            user.setLogin(request.getParameter("login"));
            user.setPassword(DigestUtils.md5Hex(request.getParameter("password")));

            UserDao userDao = new UserDao();
            User findUser = userDao.findUserByLogin(user.getLogin());

            if (user.getPassword().equals(findUser.getPassword())) {

                final HttpSession userSession = request.getSession();
                userSession.setAttribute("login", request.getParameter("login"));
                userSession.setAttribute("userName", findUser.getUserName());

                response.sendRedirect(request.getContextPath() + "/tasks");
            }
            else {
                response.setCharacterEncoding("UTF-8");
                response.setContentType("text/HTML");
                response.getWriter().write("Неверный логин или пароль");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("html/loginPage.html");
        dispatcher.forward(request, response);
    }
}
