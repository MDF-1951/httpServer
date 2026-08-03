import java.util.*;
public class httpObj{

    String method;
    String path;
    String version;

    Map<String,String> headers;

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
    public void setHeaders(Map<String,String> map)
    {
        this.headers=map;
    }

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
}