package controller;

import factory.ProductServiceFactory;
import model.Product;
import org.apache.log4j.Logger;
import service.interfaces.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet(value = "/product")
public class EditProductServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(EditProductServlet.class);
    private static ProductService productService = ProductServiceFactory.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        if (id != null) {
            Optional<Product> product = productService.getProductById(Long.parseLong(id));
            if (product.isPresent()) {
                request.setAttribute("product", product.get());
            }
        }
        request.getRequestDispatcher("/edit_product.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        try {
            String name = request.getParameter("name");
            String description = request.getParameter("description");
            Double price = Double.parseDouble(request.getParameter("price"));
            Optional<Product> productById = productService.getProductById(Long.parseLong(id));
            if (!name.isEmpty() && !description.isEmpty() && price > 0) {
                if (productById.isPresent()) {
                    Product newProduct = new Product(
                            productById.get().getId(), name, description, price);
                    productService.updateProduct(newProduct);
                    response.sendRedirect("/user/products");
                }
            } else {
                response.sendRedirect("/product?id=" + id);
            }
        } catch (NumberFormatException e) {
            logger.info("Invalid input format.");
            response.sendRedirect("/product?id=" + id);
        }
    }
}
