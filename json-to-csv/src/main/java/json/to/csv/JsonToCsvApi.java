/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package json.to.csv;

//import com.mashape.unirest.http.HttpResponse;
//import com.mashape.unirest.http.Unirest;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;
import javax.swing.text.html.parser.Entity;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.commons.io.FileUtils;
//import org.apache.http.HttpRequest;
//import org.apache.http.client.HttpClient;
import org.json.CDL;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Akash
 */
public class JsonToCsvApi {
    

    
    public static Response getResponse() 
    {
        OkHttpClient client = new OkHttpClient();

        
        Request request = new Request.Builder()
  .url("http://apitest.giddh.com/company/newsncindore15000172022770ygv88/trial-balance?refresh=true")
  .get()
  .addHeader("Auth-Key", "WBYPV0XQMaYnUc3RldcJnkDhJhE42JHB7q-bQ942xlMpt0qc72ClEI7q6oxr0rzNMLUIarqftZ55TlKZkpp2yEE7sMAmgM5s3R8h4bYUKbM=")
  .addHeader("Cache-Control", "no-cache")
  .addHeader("Postman-Token", "9cb57484-b56c-4cbe-8a59-0a40b7310214")
  .build();
        Response response=null;
       try{
         response = client.newCall(request).execute();
       }
       catch(IOException e)
       {
           System.out.println(e);
       }
       return response;
    }
    private static String convertStreamToString(InputStream is) {

        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
    StringBuilder sb = new StringBuilder();

    String line = null;
    try {
        while ((line = reader.readLine()) != null) {
            sb.append(line + "\n");
        }
    } catch (IOException e) {
        e.printStackTrace();
    } finally {
        try {
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    return sb.toString();
}
}
