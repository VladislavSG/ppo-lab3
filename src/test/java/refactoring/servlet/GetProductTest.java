package refactoring.servlet;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import refactoring.data.dao.ProductDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.SQLException;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class GetProductTest {
    private AutoCloseable closeble;

    @Mock
    private HttpServletRequest servletRequest;

    @Mock
    private HttpServletResponse servletResponse;

    @Mock
    private ProductDao productDao;

    @InjectMocks
    private GetProductsServlet servlet;

    @BeforeEach
    public void init() {
        closeble = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    public void close() throws Exception {
        closeble.close();
    }

    @Test
    public void testGetProducts() throws IOException, SQLException {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printer = new PrintWriter(stringWriter);
        when(servletResponse.getWriter())
                .thenReturn(printer);

        servlet.doGet(servletRequest, servletResponse);

        printer.flush();

        verify(servletResponse).setStatus(HttpServletResponse.SC_OK);
        verify(servletResponse).setContentType("text/html");
        verify(productDao).getProducts();
    }
}
