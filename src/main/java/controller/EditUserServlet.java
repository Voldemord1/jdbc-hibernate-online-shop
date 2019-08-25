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
import java.util.Optional;

@WebServlet(value = "/admin/user")
public class EditUserServlet extends HttpServlet {

    private static UserService userService = UserServiceFactory.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        if (id != null) {
            Optional<User> user = userService.getUserById(Long.parseLong(id));
            if (user.isPresent()) {
                request.setAttribute("user", user.get());
            }
        }
        request.getRequestDispatcher("/user.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        String role = request.getParameter("role");
        Optional<User> userById = userService.getUserById(Long.parseLong(id));
        if (!email.isEmpty() && !password.isEmpty() && !confirmPassword.isEmpty()) {
            if (password.equals(confirmPassword) && userById.isPresent()) {
                String salt = HashUtil.getSalt();
                String hashPassword = HashUtil.getHash(password, salt);
                User newUser = new User(userById.get().getId(), email, hashPassword, role);
                newUser.setSalt(salt);
                userService.updateUser(newUser);
            } else {
                request.setAttribute("message", "Passwords not equals! Try again.");
                request.getRequestDispatcher("/user.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("message", "All fields must be filled!!!");
            request.getRequestDispatcher("/user.jsp").forward(request, response);
        }
        response.sendRedirect("/admin/users");
    }
}
