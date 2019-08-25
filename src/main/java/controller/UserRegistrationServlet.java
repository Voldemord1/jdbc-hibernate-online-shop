package controller;

import factory.UserServiceFactory;
import model.User;
import service.interfaces.UserService;
import utils.HashUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class UserRegistrationServlet extends HttpServlet {

    private static final UserService userService = UserServiceFactory.getInstance();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String repeatPassword = request.getParameter("repeatPassword");
        String role = request.getParameter("role");
        if (!email.isEmpty() && !password.isEmpty()
                && !repeatPassword.isEmpty() && !role.isEmpty()) {
            if (!userService.isUserExist(email)) {
                if (password.equals(repeatPassword)) {
                    String salt = HashUtil.getSalt();
                    User user = new User(email, HashUtil.getHash(password, salt), role);
                    user.setSalt(salt);
                    userService.addUser(user);
                    response.sendRedirect("/");
                } else {
                    request.setAttribute("message", "Passwords not equals! Try again...");
                    request.getRequestDispatcher("/register.jsp").forward(request, response);
                }
            } else {
                request.setAttribute("message", "User " + email + " is already registered.");
                request.getRequestDispatcher("/register.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("message", "All fields must be filled!!!");
            request.getRequestDispatcher("/register.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/register.jsp").forward(request, response);
    }
}
