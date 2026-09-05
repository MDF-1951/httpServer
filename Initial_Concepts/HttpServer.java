import java.util.*;
import java.io.*;
import java.net.*;
import java.io.IOException;

public class HttpServer{


    private final int port;
    private final Router router;

    public HttpServer(int port,Router router)
    {
        this.port = port;
        this.router=router;
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

            BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                    s.getInputStream()));
            
            HttpParser parser = new HttpParser();
            
            HttpRequest req = parser.parse(reader);

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

            i++;
        }


    }
}