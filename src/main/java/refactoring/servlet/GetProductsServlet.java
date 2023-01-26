package refactoring.servlet;

import refactoring.data.Product;
import refactoring.data.dao.ProductDao;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

/**
 * @author akirakozov
 */
public class GetProductsServlet extends HttpServlet {
    private final ProductDao productDao;

    public GetProductsServlet(final ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            PrintWriter writer = response.getWriter();

            writer.println("<html><body>");
            productDao.getProducts().stream()
                    .map(Product::toHtml)
                    .forEach(writer::println);
            writer.println("</body></html>");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
