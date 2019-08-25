package controller;

import model.User;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(value = "/exit")
public class ExitServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(ExitServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        session.invalidate();
        logger.info("User " + user + " exit.");
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }
}
