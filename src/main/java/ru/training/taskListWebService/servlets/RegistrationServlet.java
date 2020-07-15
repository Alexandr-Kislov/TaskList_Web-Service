package ru.training.taskListWebService.servlets;

import org.apache.commons.codec.digest.DigestUtils;
import ru.training.taskListWebService.service.EmailValidator;
import ru.training.taskListWebService.dao.UserDao;
import ru.training.taskListWebService.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class RegistrationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User user = new User();
        user.setLogin(request.getParameter("login"));
        user.setPassword(DigestUtils.md5Hex(request.getParameter("password")));
        user.setUserName(request.getParameter("userName"));

        UserDao userDao = new UserDao();
        User findUser = null;

        try {
            findUser = userDao.findUserByLogin(user.getLogin());
            if (findUser == null) {

                EmailValidator emailvalidator = new EmailValidator();
                if (emailvalidator.validate(user.getLogin())) {
                    userDao.registerNewUser(user);
                    response.setCharacterEncoding("UTF-8");
                    response.setContentType("text/HTML");
                    response.getWriter().write("Регистрация прошла успешно. Поздравляем!");
                }
                else {
                    response.setCharacterEncoding("UTF-8");
                    response.setContentType("text/HTML");
                    response.getWriter().write("Введенное значение не является адресом электронной почты. Попробуйте еще раз");
                }
            } else {
                response.setCharacterEncoding("UTF-8");
                response.setContentType("text/HTML");
                response.getWriter().write("Пользователь с таким адресом уже существует");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("html/registrationPage.html");
        dispatcher.forward(request, response);
    }
}
