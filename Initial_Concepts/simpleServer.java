import java.net.ServerSocket;
import java.net.Socket;
import java.io.OutputStream;
import java.io.InputStream;
class simpleServer{

    public static void main(String args[]) throws Exception
    {
        ServerSocket ss = new ServerSocket(8080);
        
        System.out.println("Waiting for client");


        Socket socket = ss.accept();

        System.out.println("Client Connected");

        InputStream input = socket.getInputStream();

        int data;

        StringBuilder request = new StringBuilder();

        while((data=input.read())!=-1)
        {
            request.append((char)data);

            if (request.toString().endsWith("\r\n\r\n")) {
                    break;      // End of HTTP headers
            }

        }
        System.out.println(request);

        OutputStream output = socket.getOutputStream();

        String response = 
        "HTTP/1.1 200 OK\r\n"+"Content-Type: text/plain\r\n"+
        "Content-Length: 5\r\n"+"\r\n"+"Hello";

        output.write(response.getBytes());

        //output.flush();

        socket.close();

        ss.close();


    }
}