import java.util.*;
import java.io.*;
import java.net.*;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HttpServer{


    private final int port;
    private final Router router;

    private final ExecutorService executor = Executors.newFixedThreadPool(4);

    public HttpServer(int port,Router router)
    {
        this.port = port;
        this.router=router;
    }

    public void handleClient(Socket s) throws Exception
    {
        /*BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                    s.getInputStream()));
        */
    	
    	System.out.println(
                "Handling client on thread: "
                + Thread.currentThread().getName()
        );
            
        HttpParser parser = new HttpParser();
            
        HttpRequest req = parser.parse(s.getInputStream());

        HttpResponse res = new HttpResponse(s.getOutputStream());

        Handler handler = router.getPath(req.getMethod(),req.getPath());

        if(handler==null)
        {
            res.setStatus(404,"Not Found");

            res.setHeaders("Content-Type","text/plain; charset=UTF-8");

            res.send("404 - Resource Not Found");
        }
        else
        {
            handler.handle(req,res);
        }

        s.close();

    }
    
    public void start() throws Exception
    {
        ServerSocket ss = new ServerSocket(port);

        System.out.println("Server Started and Waiting for Connection");

        int i=1;

        while(true)
        {
            Socket s = ss.accept();
            System.out.println("Client "+i+" Connected");

            executor.submit(() -> {

            	try {

            	    handleClient(s);

            	} catch (IOException e) {

            	    System.out.println(
            	            "Client connection ended or request was invalid: "
            	                    + e.getMessage()
            	    );

            	} catch (Exception e) {

            	    e.printStackTrace();

            	}
            });



            i++;
        }


    }
}