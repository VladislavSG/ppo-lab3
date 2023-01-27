package refactoring.html;

public class HtmlBuilder {
    private final StringBuilder builder = new StringBuilder();

    public HtmlBuilder() {
        builder.append("<html><body>");
    }

    public HtmlBuilder addHeader(String header) {
        builder.append("<h1>").append(header).append("</h1>");
        return this;
    }

    public HtmlBuilder add(String html) {
        builder.append(html);
        return this;
    }

    public HtmlBuilder addLine(String html) {
        builder.append(html).append("</br>");
        return this;
    }

    public String build() {
        builder.append("</body></html>");
        return builder.toString();
    }
}
