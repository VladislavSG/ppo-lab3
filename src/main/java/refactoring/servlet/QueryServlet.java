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
public class QueryServlet extends HttpServlet {
    private final ProductDao productDao;

    public QueryServlet(final ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String command = request.getParameter("command");

        if ("max".equals(command)) {
            try {
                PrintWriter writer = response.getWriter();
                writer.println("<html><body>");
                writer.println("<h1>Product with max price: </h1>");
                writer.println(productDao
                        .maxPriceProduct()
                        .map(Product::toHtml)
                        .orElse("No products"));
                writer.println("</body></html>");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else if ("min".equals(command)) {
            try {
                PrintWriter writer = response.getWriter();
                writer.println("<html><body>");
                writer.println("<h1>Product with min price: </h1>");
                writer.println(productDao
                        .minPriceProduct()
                        .map(Product::toHtml)
                        .orElse("No products"));
                writer.println("</body></html>");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else if ("sum".equals(command)) {
            try {
                PrintWriter writer = response.getWriter();
                writer.println("<html><body>");
                writer.println("Summary price: ");
                writer.println(productDao.sumPriceProducts());
                writer.println("</body></html>");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else if ("count".equals(command)) {
            try {
                PrintWriter writer = response.getWriter();
                writer.println("<html><body>");
                writer.println("Number of products: ");
                writer.println(productDao.getProductsCount());
                writer.println("</body></html>");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            response.getWriter().println("Unknown command: " + command);
        }

        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);
    }

}
