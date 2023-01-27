package refactoring.servlet;

import refactoring.data.Product;
import refactoring.data.dao.ProductDao;
import refactoring.html.HtmlBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;
import java.util.function.BiConsumer;

/**
 * @author akirakozov
 */
public class QueryServlet extends AlwaysOkServlet {
    private static final Map<String, BiConsumer<HtmlBuilder, ProductDao>> operations = Map.of(
            "max", (builder, dao) -> {
                try {
                    builder.addHeader("Product with max price:");
                    builder.addLine(dao
                            .maxPriceProduct()
                            .map(Product::toSimpleString)
                            .orElse("No products"));
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            },
            "min", (builder, dao) -> {
                try {
                    builder.addHeader("Product with min price:");
                    builder.addLine(dao
                            .minPriceProduct()
                            .map(Product::toSimpleString)
                            .orElse("No products"));
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            },
            "sum", (builder, dao) -> {
                try {
                    builder.addHeader("Summary price:");
                    builder.addLine(Long.toString(dao.sumPriceProducts()));
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            },
            "count", (builder, dao) -> {
                try {
                    builder.addHeader("Number of products:");
                    builder.addLine(Long.toString(dao.getProductsCount()));
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
    );
    private final ProductDao productDao;

    public QueryServlet(final ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    protected void process(HttpServletRequest request, HttpServletResponse response) {
        String command = request.getParameter("command");
        HtmlBuilder builder = new HtmlBuilder();

        operations.getOrDefault(
                command,
                (b, dao) -> b.add("Unknown command: " + command)
        ).accept(builder, productDao);

        try {
            response.getWriter().print(builder.build());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
