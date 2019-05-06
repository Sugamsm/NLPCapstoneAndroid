package com.mankadproject.sunycapstone;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

public class JsonParser {

    private static JSONObject jsonObject = null;
    private static String json = "";

    public JSONObject getJSONFromUrl(String url) {
        URLConnection urlConnection;
        URL link;
        try {
            link = new URL(url);
            urlConnection = link.openConnection();
            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);
            urlConnection.setConnectTimeout(3000);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        try {
            InputStream inputStream = urlConnection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            inputStream.close();
            reader.close();
            json = sb.toString();
            //Log json for extra info
        } catch (Exception e) {
            System.out.println("Buffer Error" + e.toString());
        }
        try {
            jsonObject = new JSONObject(json);
        } catch (JSONException e) {
            System.out.println("JSON Parser" + e.toString());
        }
        return jsonObject;
    }
}

