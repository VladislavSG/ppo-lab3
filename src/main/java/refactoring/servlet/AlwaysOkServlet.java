package refactoring.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class AlwaysOkServlet extends HttpServlet {
    protected abstract void process(HttpServletRequest request, HttpServletResponse response);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        process(request, response);
        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
