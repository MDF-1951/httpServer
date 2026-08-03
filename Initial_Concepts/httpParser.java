import java.net.ServerSocket;
import java.net.Socket;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
public class httpParser{



    public static void main(String args[]) throws Exception
    {
        ServerSocket ss = new ServerSocket(8080);

        System.out.println("Waiting for Connection");

        Socket socket = ss.accept();

        System.out.println("Client Connected");

        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        String line = reader.readLine();

        String parts[] = line.split(" ");

        httpObj obj = new httpObj();

        obj.setMethod(parts[0]);
        obj.setPath(parts[1]);
        obj.setVersion(parts[2]);

        System.out.println(obj.getMethod());
        System.out.println(obj.getPath());
        System.out.println(obj.getVersion());

        Map<String,String> map = new HashMap<>();

        while(true)
        {
            line = reader.readLine();

            if(line.isEmpty())
                break;
            
            String[] partss = line.split(":",2);

            map.put(partss[0],partss[1]);
        }

        obj.setHeaders(map);

        Map<String,String> gmap = obj.getHeaders();

        for(Map.Entry<String,String> entry : gmap.entrySet())
        {
            System.out.print(entry.getKey()+"-");
            System.out.println(entry.getValue());
        }

    }

    
}