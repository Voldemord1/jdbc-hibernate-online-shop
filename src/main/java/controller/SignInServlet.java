package controller;

//import factory.UserServiceFactory;
import factory.UserServiceFactory;
        import model.User;
import service.interfaces.UserService;
import utils.HashUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/login")
public class SignInServlet extends HttpServlet {

    private static final UserService userService = UserServiceFactory.getInstance();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        if (!email.isEmpty() && !password.isEmpty()) {
            Optional<User> user = userService.getUserByEmail(email);
            if (user.isPresent()) {
                String hashPassword = HashUtil.getHash(password, user.get().getSalt());
                if(user.get().getPassword().equals(hashPassword)) {
                    HttpSession session = request.getSession();
                    session.setAttribute("user", user.get());
                    response.sendRedirect("/user/products");
                }else{
                    request.setAttribute("message", "Wrong password");
                    request.getRequestDispatcher("/index.jsp").forward(request, response);
                }
            } else {
                request.setAttribute("message",
                        "User " + email + " not exist. Please register.");
                request.getRequestDispatcher("/index.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("message", "All fields must be filled!!!");
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        userService.addUser(
                new User("test@test.ru", "1234567890", "admin"));
        response.sendRedirect("/");
    }
}
