package controller;

import factory.BasketServiceFactory;
import factory.OrderServiceFactory;
import model.Order;
import model.User;
import service.interfaces.BasketService;
import service.interfaces.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

@WebServlet("/user/code")
public class ConfirmCodeServlet extends HttpServlet {

    private static final OrderService orderService = OrderServiceFactory.getInstance();
    private static final BasketService basketService = BasketServiceFactory.getInstance();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String userCode = request.getParameter("code");
            Order order = (Order) request.getSession().getAttribute("order");
            User user = (User) request.getSession().getAttribute("user");
            String sendCode = (String) request.getSession().getAttribute("sendCode");
            if (userCode.equals(sendCode) && Objects.nonNull(order) && Objects.nonNull(user)) {
                Optional<Long> orderId = orderService.addOrder(order);
                if (orderId.isPresent()) {
                    request.setAttribute("message",
                            "Order #" + orderId.get() + " successfully placed");
                    basketService.clear(user);
                    request.getRequestDispatcher("/code.jsp").forward(request, response);
                } else {
                    request.setAttribute("message", "Such order not exist");
                    request.getRequestDispatcher("/code.jsp").forward(request, response);
                }
            } else {
                request.setAttribute("message", "Incorrect code!!!");
                request.getRequestDispatcher("/code.jsp").forward(request, response);
            }
        } catch (NullPointerException e) {
            request.setAttribute("message", "Wrong data!!!");
            request.getRequestDispatcher("/code.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("/code.jsp");
    }
}
