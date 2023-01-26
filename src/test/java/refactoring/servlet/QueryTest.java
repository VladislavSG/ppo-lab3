package refactoring.servlet;

import html.Utils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class QueryTest {
    private final QueryServlet servlet = new QueryServlet();
    private AutoCloseable closeble;

    @Mock
    private HttpServletRequest servletRequest;

    @Mock
    private HttpServletResponse servletResponse;

    @BeforeEach
    public void init() {
        closeble = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    public void close() throws Exception {
        closeble.close();
    }

    @Test
    public void testGetProducts() throws IOException {
        when(servletRequest.getParameter("command"))
                .thenReturn("max");

        StringWriter stringWriter = new StringWriter();
        PrintWriter printer = new PrintWriter(stringWriter);
        when(servletResponse.getWriter())
                .thenReturn(printer);

        servlet.doGet(servletRequest, servletResponse);

        verify(servletResponse).setStatus(HttpServletResponse.SC_OK);
        verify(servletResponse).setContentType("text/html");
        printer.flush();
        assertTrue(Utils.checkHtml(stringWriter.toString()));
    }
}