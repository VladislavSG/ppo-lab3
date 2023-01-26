package html;

import java.util.regex.Pattern;

public class Utils {
    private static final Pattern htmlPattern = Pattern.compile("(?s).*<html>.*<body>.*</body>.*</html>.*");
    public static boolean checkHtml(String html) {
        return htmlPattern.matcher(html).matches();
    }
}
