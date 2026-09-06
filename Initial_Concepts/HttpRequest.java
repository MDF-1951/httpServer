import java.util.*;
public class HttpRequest{

    String method;
    String path;
    String version;
    String body;
    
    
    Map<String,String> headers = new HashMap<>();

    public void setMethod(String method)
    {
        this.method=method;
    }
    public void setVersion(String version)
    {
        this.version=version;
    }
    public void setPath(String path)
    {
        this.path=path;
    }
    /*public void setHeaders(Map<String,String> map)
    {
        this.headers=map;
    }*/

    public String getMethod()
    {
        return method;
    }

    public String getPath()
    {
        return path;
    }

    public String getVersion()
    {
        return version;
    }

    public Map<String,String> getHeaders()
    {
        return headers;
    }
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
    
    
}