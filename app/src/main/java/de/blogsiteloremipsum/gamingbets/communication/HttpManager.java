package de.blogsiteloremipsum.gamingbets.communication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import org.apache.commons.codec.binary.Base64;

/**
 * Created by Andre on 21.04.2016.
 */
public class HttpManager {

    public static String getData(RequestPackage p) {

        BufferedReader in = null;
        String uri = p.getUri();
        if((p.getMethod().equals("GET"))){
            uri+=  p.getEncodedParams();
        }
        try{
            URL url = new URL(uri);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod(p.getMethod());
            System.out.println(url);
            if(p.getMethod().equals("POST")||p.getMethod().equals("PUT")){
                con.setRequestProperty("Content-Type", "application/json");
                con.setRequestProperty("Accept", "application/json");
                con.setRequestProperty("Accept-Charset", "UTF-8");
                System.out.println(url);
                System.out.println(con.getRequestMethod());

                OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
                if(p.getUser()!=null){
                    wr.write(p.getUser());
                }
                if(p.getBet()!=null){
                    wr.write(p.getBet());
                }
                if(p.getTicket()!=null){
                    wr.write(p.getTicket());
                }
                if(p.getTicketmessage()!=null) {
                    wr.write(p.getTicketmessage());
                }
                wr.flush();

            }


            int statusCode = con.getResponseCode();
            InputStream is = null;
            System.out.println(statusCode);
            if (statusCode >= 200 && statusCode < 400) {
                // Create an InputStream in order to extract the response object
                is = con.getInputStream();
                StringBuilder sb =  new StringBuilder();
                in = new BufferedReader(new InputStreamReader(is));

                String inputLine ="";
                while ((inputLine = in.readLine()) != null)
                    sb.append(inputLine + "\n");

                return sb.toString();
            }
            else {
                is = con.getErrorStream();
                StringBuilder sb =  new StringBuilder();
                in = new BufferedReader(new InputStreamReader(is));

                String inputLine ="";
                while ((inputLine = in.readLine()) != null){
                    sb.append(inputLine + "\n");
                }

                System.out.println(sb);
                return null;
            }

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
