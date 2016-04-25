package de.blogsiteloremipsum.gamingbets.communication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Andre on 21.04.2016.
 */
public class HttpManager {

    public static String getData(RequestPackage p) {

        BufferedReader in = null;
        String uri = p.getUri();

        switch(p.getMethod()){
            case "GET":
                uri+= "?" + p.getEncodedParams();
                break;
            case "PUT": break;
            case "DELETE": break;
            case "POST": break;
        }

        try{
            URL url = new URL(uri);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod(p.getMethod());

            StringBuilder sb =  new StringBuilder();
            in = new BufferedReader(new InputStreamReader(con.getInputStream()));

            String inputLine ="";
            while ((inputLine = in.readLine()) != null)
                sb.append(inputLine + "\n");

            return sb.toString();

        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }finally {
            if(in!=null){
                try{
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
