package org.example;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Main {
    public static void main(String[] args) throws IOException {
  String urlstring="https://api.open-meteo.com/v1/forecast?latitude=52.52&longitude=13.41&current=temperature_2m,wind_speed_10m&hourly=temperature_2m,relative_humidity_2m,wind_speed_10m";
        URL url=null;
        HttpURLConnection connection=null;
       try {

           url=new URL(urlstring);
            } catch (MalformedURLException e) {
           System.out.println("Problem in URL");
       }

    //    System.out.println("Hello world!");
        int responsecode=0;
try{
     connection= (HttpURLConnection) url.openConnection();
     responsecode=connection.getResponseCode();
   }catch (IOException e){
        throw new RuntimeException(e);
}
        if(responsecode ==200){
            BufferedReader in=new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder apiData=new StringBuilder();
            String readLine= null;  // EOF-> file end

            while((readLine = in.readLine())!=null){
                apiData.append(readLine);

            }
            try {
                 in.close();
            }catch (IOException e){
                throw new IOException(e);
            }
            System.out.println(apiData.toString());
            JSONObject jsonApiResponse= new JSONObject(apiData.toString());
            System.out.println(jsonApiResponse.get("longitude"));
        }else{
            System.out.println("API call fails ");
        }

    }
}