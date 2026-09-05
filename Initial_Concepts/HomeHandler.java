

public class HomeHandler implements Handler{

    public void handle(HttpRequest req, HttpResponse res) throws Exception
    {
        res.setHeaders("Content-Type",
                "text/html; charset=UTF-8");
        
        res.send("<html>" +
                "<body>" +
                "<h1>Welcome to My Java Server</h1>" +
                "</body>" +
                "</html>");
    }
}