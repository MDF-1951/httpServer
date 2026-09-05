import java.net.ServerSocket;
import java.net.Socket;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;
public class HttpParser{



    public HttpRequest parse(BufferedReader reader) throws IOException
    {
        


        String line = reader.readLine();

        /*if(line == null || line.isEmpty())
        {
            throw new IOException("Invalid Http Request");
        }*/

        String parts[] = line.split(" ");

        /*if(parts.length!=3)
        {
            throw new IOException("Invalid Http Request Line");
        }*/

        HttpRequest obj = new HttpRequest();

        obj.setMethod(parts[0]);
        obj.setPath(parts[1]);
        obj.setVersion(parts[2]);

        System.out.println(obj.getMethod());
        System.out.println(obj.getPath());
        System.out.println(obj.getVersion());


        while(true)
        {
            line = reader.readLine();

            if(line.isEmpty())
                break;
            
            String[] partss = line.split(":",2);

            obj.getHeaders().put(partss[0],partss[1]);
        }

        //obj.setHeaders(map);

        Map<String,String> gmap = obj.getHeaders();

        for(Map.Entry<String,String> entry : gmap.entrySet())
        {
            System.out.print(entry.getKey()+"-");
            System.out.println(entry.getValue());
        }

        return obj;

    }

    
}