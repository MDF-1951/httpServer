import java.util.Map;
import java.util.HashMap;

public class Router{

    private final Map<String,Handler> map = new HashMap<>();

    public void addPath(String method, String path, Handler handler)
    {
        String key = method+": "+path;
        map.put(key,handler);
    }

    public Handler getPath(String method, String path)
    {
        return map.get(method+": "+path);
    }


    
}