package com.example.a1nagar.flowercatalog;

import android.net.Uri;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpManager {

    public String getData( String uri)
    {
        BufferedReader reader = null;

        try {
            URL url = new URL(uri);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            StringBuilder sb = new StringBuilder();
            reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;

            while ( (line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            return sb.toString();

        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            if(reader!=null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    return  null;
                }
            }
        }



    }

}