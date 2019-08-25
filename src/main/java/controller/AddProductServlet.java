package controller;

import factory.ProductServiceFactory;
import model.Product;
import org.apache.log4j.Logger;
import service.interfaces.ProductService;
import utils.ProductIdGenerator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@WebServlet(value = "/add_product")
public class AddProductServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(AddProductServlet.class);
    private static final ProductService productService = ProductServiceFactory.getInstance();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String name = request.getParameter("name");
            String description = request.getParameter("description");
            Double price = Double.parseDouble(request.getParameter("price"));
            if (Objects.nonNull(name) && Objects.nonNull(description) && price > 0) {
                Product product = new Product(ProductIdGenerator.getId(), name, description, price);
                productService.addProduct(product);
                response.sendRedirect("/user/products");
            } else {
                request.setAttribute("message", "All fields must be filled correctly!!!");
                request.getRequestDispatcher("/add_product.jsp").forward(request, response);
            }
        } catch (NumberFormatException e) {
            request.setAttribute("message", "Invalid input format.");
            logger.info("Invalid input format.");
            request.getRequestDispatcher("/add_product.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/add_product.jsp").forward(request, response);
    }
}
