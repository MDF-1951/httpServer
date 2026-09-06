

public class UserHandler implements Handler{

    public void handle(HttpRequest req, HttpResponse res) throws Exception
    {

        System.out.println("Handling Request on thread: "+Thread.currentThread().getName());

        //Thread.sleep(10000);
        
        System.out.println(req.getBody());
        
        res.setHeaders("Content-Type","text/plain; charset=UTF-8");

        res.send("Body Received Successfully: "+req.getBody());
    }
}