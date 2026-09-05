

public class UserHandler implements Handler{

    public void handle(HttpRequest req, HttpResponse res) throws Exception
    {
        res.setHeaders("Content-Type","text/plain; charset=UTF-8");

        res.send("User End Point");
    }
}