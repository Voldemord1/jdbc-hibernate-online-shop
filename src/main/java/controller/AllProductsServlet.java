package controller;

import factory.BasketServiceFactory;
import factory.ProductServiceFactory;
import model.Product;
import model.User;
import service.interfaces.BasketService;
import service.interfaces.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/user/products")
public class AllProductsServlet extends HttpServlet {

    private static final ProductService productService = ProductServiceFactory.getInstance();
    private static final BasketService basketService = BasketServiceFactory.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        List<Product> allProducts = productService.getAllProducts();
        int productCountOfBasket = basketService.getAllProducts(user).size();
        request.setAttribute("products", allProducts);
        request.setAttribute("productCountOfBasket", productCountOfBasket);
        request.getRequestDispatcher("/products.jsp").forward(request, response);
    }
}
