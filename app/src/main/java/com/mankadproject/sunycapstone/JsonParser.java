package com.mankadproject.sunycapstone;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class JsonParser {

    private static JSONObject jsonObject = null;
    private static String json = "";

    public JSONObject getJSONFromUrl(String url) {
        HttpURLConnection urlConnection;
        URL link;
        try {
            link = new URL(url);
            urlConnection = (HttpURLConnection) link.openConnection();
            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);
            urlConnection.setRequestMethod("GET");
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
            json = sb.toString();
            //Log json for extra info
        } catch (Exception e) {
            Log.e("Buffer Error", e.getMessage());
        }
        try {
            jsonObject = new JSONObject(json);
        } catch (JSONException e) {
            Log.e("JSON Parser", e.getMessage());
        }
        return jsonObject;
    }
}

