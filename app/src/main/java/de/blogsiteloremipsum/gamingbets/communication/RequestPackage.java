package de.blogsiteloremipsum.gamingbets.communication;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import de.blogsiteloremipsum.gamingbets.classes.Ticket;

/**
 * Created by Andre on 21.04.2016.
 */
public class RequestPackage {

    private String uri;
    private String method = "GET";
    private Map<String, String> params = new HashMap<>();
    private String user=null;
    private String ticket=null;
    private String bet=null;
    private String ticketmessage = null;

    public RequestPackage(){
        uri = "http://134.255.218.20:8080/gamingBetRestServer/api";
        //uri = "http://192.168.204.1:8080/gamingBetRestServer/api";
        //uri="http://localhost:8080/gamingBetRestServer/api";
    }

    public String getTicketmessage() {
        return ticketmessage;
    }

    public void setTicketmessage(String ticketmessage) {
        this.ticketmessage = ticketmessage;
    }

    public String getTicket() {
        return ticket;
    }

    public String getBet() {
        return bet;
    }

    public void setBet(String bet) {
        this.bet = bet;
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

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }


}
