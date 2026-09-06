
public class Main{

    public static void main(String args[]) throws Exception
    {
        int port = 8080;
        
        Router router = new Router();

        router.addPath("GET","/",new HomeHandler());

        router.addPath("GET","/users",new UserHandler());
        
        router.addPath("POST", "/users", new UserHandler());
        HttpServer server = new HttpServer(port,router);

        server.start();
    }
}