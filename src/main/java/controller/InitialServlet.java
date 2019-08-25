package controller;

import factory.ProductServiceFactory;
import factory.UserServiceFactory;
import model.Product;
import model.User;
import service.interfaces.ProductService;
import service.interfaces.UserService;
import utils.HashUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet(value = "/", loadOnStartup = 1)
public class InitialServlet extends HttpServlet {

    private static final UserService userService = UserServiceFactory.getInstance();
    private static final ProductService productService = ProductServiceFactory.getInstance();

    @Override
    public void init() {
        String salt1 = HashUtil.getSalt();
        String salt2 = HashUtil.getSalt();
        String salt3 = HashUtil.getSalt();
        User admin = new User("admin@gmail.com", HashUtil.getHash("123", salt1), "admin");
        User user = new User("user@gmail.com",  HashUtil.getHash("123", salt2), "user");
        User user2 = new User("test@gmail.com",  HashUtil.getHash("123", salt3), "user");
        admin.setSalt(salt1);
        user.setSalt(salt2);
        user2.setSalt(salt3);
        userService.addUser(admin);
        userService.addUser(user);
        userService.addUser(user2);

        Product product1 = new Product("Phone", "iPhone X 128G", 699.0);
        Product product2 = new Product("Watch", "wrist watch", 399.0);
        Product product3 = new Product("MacBook", "laptop", 2699.0);
        Product product4 = new Product("iPad", "tablet", 1100.0);
        productService.addProduct(product1);
        productService.addProduct(product2);
        productService.addProduct(product3);
        productService.addProduct(product4);
    }
}
