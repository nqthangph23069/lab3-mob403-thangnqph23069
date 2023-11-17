package com.example.lab3.bai1;

import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpHandler {
    private static final String TAG = HttpHandler.class.getSimpleName();
    public HttpHandler (){

    }
    public String makeServiceCall (String reqUrl){
        String reponse = null;
        try {
            URL url = new URL(reqUrl);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream in = new BufferedInputStream(httpURLConnection.getInputStream());
            reponse = convertStreamToString(in);

        }catch (Exception e){
            e.printStackTrace();
            Log.e(TAG, "Exception" + e.getMessage());

        }
        return reponse;
    }

    private String convertStreamToString(InputStream is) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line;
        try {
            while ((line = bufferedReader.readLine()) != null){
                sb.append(line).append("\n");
            }
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            try {
                is.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        return sb.toString();
    }
}
