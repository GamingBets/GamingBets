package de.blogsiteloremipsum.gamingbets.communication;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Andre on 21.04.2016.
 */
public class RequestPackage {

    private String uri;
    private String method = "GET";
    private Map<String, String> params = new HashMap<>();
    private String user="";

    public RequestPackage(){
        uri = "http://192.168.204.1:8080/gamingBetRESTServer/api";
    }
    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri += uri;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }

    public void setParam(String key, String value)  {
        params.put(key, value);
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getEncodedParams(){
        StringBuilder sb = new StringBuilder();
        String value = null;
        for (String key: params.keySet()){
            try {
                value = URLEncoder.encode(params.get(key), "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            if(sb.length()>0){
                //sb.append("&");
            }
            sb.append("/" + value);
            //sb.append(key + "=" +value);

        }
        return sb.toString();
    }
}
