package refactoring.servlet;

import refactoring.data.Product;
import refactoring.data.dao.ProductDao;
import refactoring.html.HtmlBuilder;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * @author akirakozov
 */
public class AddProductServlet extends AlwaysOkServlet {
    private final ProductDao productDao;

    public AddProductServlet(final ProductDao productDao) {
        this.productDao = productDao;
    }


    @Override
    protected void process(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        long price = Long.parseLong(request.getParameter("price"));

        try {
            productDao.insert(new Product(name, price));
            response.getWriter().println("OK");
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
