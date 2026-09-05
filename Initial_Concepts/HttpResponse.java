import java.util.*;
import java.io.*;
import java.nio.charset.StandardCharsets;


public class HttpResponse{

    private final OutputStream outputStream;

    private int statusCode = 200;
    private String statMess = "OK";

    Map<String,String> map = new LinkedHashMap<>();

    public HttpResponse(OutputStream outputStream)
    {
        this.outputStream = outputStream;
    }

    public void setStatus(int n, String mess)
    {
        this.statusCode = n;
        this.statMess = mess;
    }

    public void setHeaders(String name,String value)
    {
        map.put(name,value);
    }

    public void send(String body) throws IOException
    {
        byte[] bodyBytes = body.getBytes(StandardCharsets.UTF_8);

        setHeaders("Content-Length",String.valueOf(bodyBytes.length));

        StringBuilder response = new StringBuilder();

        response.append("HTTP/1.1 ")
                .append(statusCode)
                .append(" ")
                .append(statMess)
                .append("\r\n");
        
        for(Map.Entry<String,String> m : map.entrySet())
        {
            response.append(m.getKey())
                    .append(": ")
                    .append(m.getValue())
                    .append("\r\n");
        }

        response.append("\r\n");

        outputStream.write(response.toString().getBytes(StandardCharsets.UTF_8));

        outputStream.write(bodyBytes);

        outputStream.flush();
    }



}