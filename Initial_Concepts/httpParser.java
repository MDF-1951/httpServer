import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;
public class HttpParser{
	
	
	public String readLine(InputStream input) throws IOException
	{
		StringBuilder sb = new StringBuilder();
		
		int current;
		int previous = -1;
		
		while((current=input.read())!=-1)
		{
			if(current=='\n' && previous=='\r')
			{
				sb.deleteCharAt(sb.length()-1);
				
				return sb.toString();
			}
			
			sb.append((char)current);
			previous = current;
		}
		
		if(sb.length()>0)
		{
			return sb.toString();
		}
		
		return null;
	}


    public HttpRequest parse(InputStream input) throws IOException
    {
        


        String line = readLine(input);
        
        System.out.println(
                "Request Line Received: [" + line + "]"
        );

        if(line == null || line.isEmpty())
        {
            throw new IOException("Invalid Http Request");
        }
		
        String parts[] = line.split(" ");

        if(parts.length!=3)
        {
            throw new IOException("Invalid Http Request Line");
        }

        HttpRequest obj = new HttpRequest();

        obj.setMethod(parts[0]);
        obj.setPath(parts[1]);
        obj.setVersion(parts[2]);

        System.out.println(obj.getMethod());
        System.out.println(obj.getPath());
        System.out.println(obj.getVersion());


        while(true)
        {
            line = readLine(input);

            if(line.isEmpty())
                break;
            
            String[] partss = line.split(":",2);
            
            if(partss.length==2)
            	obj.getHeaders().put(partss[0].trim().toLowerCase(Locale.ROOT),partss[1].trim());
            
        }

        //obj.setHeaders(map);

        /*Map<String,String> gmap = obj.getHeaders();

        for(Map.Entry<String,String> entry : gmap.entrySet())
        {
            System.out.print(entry.getKey()+"-");
            System.out.println(entry.getValue());
        }
        */
        
        String contentlength = obj.getHeaders().get("content-length");
        
        if(contentlength!=null)
        {
        	int length;
        	
        	try {
        		length = Integer.parseInt(contentlength);
        	}catch(NumberFormatException e)
        	{
        		throw new IOException("Invalid Content-Length");
        	}
        	
        	byte[] body = new byte[length];
        	
        	int totalread = 0;
        	
        	while(totalread<length)
        	{
        		int bytesRead = input.read(body, totalread, length-totalread);
        		
        		if(bytesRead==-1)
        		{
        			throw new IOException("Unexpected end of request body");
        		}
        		
        		totalread += bytesRead;
        	}
        	
        	obj.setBody(new String(body,StandardCharsets.UTF_8));
        }

        return obj;

    }

    
}