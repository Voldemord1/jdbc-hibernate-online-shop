package controller;

import factory.BasketServiceFactory;
import factory.MailServiceFactory;
import model.Code;
import model.Order;
import model.Product;
import model.User;
import service.interfaces.BasketService;
import service.interfaces.MailService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

@WebServlet("/user/checkout")
public class CheckoutServlet extends HttpServlet {

    private static final MailService mailService = MailServiceFactory.getInstance();
    private static final BasketService basketService = BasketServiceFactory.getInstance();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String city = request.getParameter("city");
        String street = request.getParameter("street");
        String houseNumber = request.getParameter("houseNumber");
        String phoneNumber = request.getParameter("phoneNumber");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        try {
            if (!firstName.isEmpty() && !lastName.isEmpty() && !city.isEmpty()
                    && !street.isEmpty() && !houseNumber.isEmpty() && Objects.nonNull(user)
                    && !phoneNumber.isEmpty() && !basketService.getAllProducts(user).isEmpty()) {
                user.setCode(new Code());
                user.getCode().generateCode();
                mailService.sendConfirmCode(user);
                Order order = new Order(user, user.getCode(),
                        firstName, lastName, city, street, houseNumber, phoneNumber);
                session.setAttribute("sendCode", user.getCode().getValue());
                session.setAttribute("order", order);
            } else {
                request.setAttribute("message", "All fields must be filled!!!");
                request.getRequestDispatcher("/user/checkout").forward(request, response);
            }
        } catch (NullPointerException e) {
            request.setAttribute("message", "Wrong data");
            request.getRequestDispatcher("/user/checkout").forward(request, response);
        }
        response.sendRedirect("/code.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        List<Product> products = basketService.getAllProducts(user);
        request.setAttribute("products", products);
        request.getRequestDispatcher("/checkout.jsp").forward(request, response);
    }
}
