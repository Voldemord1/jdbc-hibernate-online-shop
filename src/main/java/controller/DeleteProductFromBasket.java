package controller;

import factory.BasketServiceFactory;
import factory.ProductServiceFactory;
import factory.UserServiceFactory;
import model.Product;
import model.User;
import service.interfaces.BasketService;
import service.interfaces.ProductService;
import service.interfaces.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

@WebServlet(value = "/user/basket/product/delete")
public class DeleteProductFromBasket extends HttpServlet {

    private static final ProductService productService = ProductServiceFactory.getInstance();
    private static final BasketService basketService = BasketServiceFactory.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        String productId = request.getParameter("id");
        if (Objects.nonNull(productId) && Objects.nonNull(user)) {
            Optional<Product> product = productService.getProductById(Long.parseLong(productId));
            if (product.isPresent()) {
                basketService.removeProduct(product.get(), user);
            }
        }
        response.sendRedirect("/user/checkout");
    }
}
