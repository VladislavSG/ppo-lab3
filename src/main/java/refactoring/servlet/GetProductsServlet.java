package refactoring.servlet;

import refactoring.data.Product;
import refactoring.data.dao.ProductDao;
import refactoring.html.HtmlBuilder;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.sql.SQLException;

/**
 * @author akirakozov
 */
public class GetProductsServlet extends AlwaysOkServlet {
    private final ProductDao productDao;

    public GetProductsServlet(final ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    protected void process(HttpServletRequest request, HttpServletResponse response) {
        try {
            HtmlBuilder builder = new HtmlBuilder();
            productDao.getProducts().stream()
                    .map(Product::toSimpleString)
                    .forEach(builder::addLine);
            response.getWriter().print(builder.build());
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
