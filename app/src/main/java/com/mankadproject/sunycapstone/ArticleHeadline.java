package com.mankadproject.sunycapstone;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.net.URLEncoder;

public class ArticleHeadline extends AppCompatActivity implements View.OnClickListener {

    EditText articleInput;
    Button articleSearch;
    TextView articleView;

    Toolbar toolbar;

    ProgressBar progressBar;

    private static String URL = "http://192.168.1.87:5000/article";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_headline);

        progressBar = findViewById(R.id.articleProgress);

        toolbar = findViewById(R.id.appBarLayout);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        articleInput = findViewById(R.id.articleInput);
        articleSearch = findViewById(R.id.articleSearch);
        articleView = findViewById(R.id.articleAuto);

        articleSearch.setOnClickListener(this);
        setTitle("Articles Generation");
    }

    @Override
    public void onClick(View v) {
        new LoadData().execute();
    }

    public class LoadData extends AsyncTask<Void, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(Void... voids) {
            try{
                String text = articleInput.getText().toString();
                if (text.isEmpty()) {
                    return null;
                }
                URL += URLEncoder.encode("?text="+text, "UTF-8");
                JsonParser parser = new JsonParser();
                JSONObject jsonObject = parser.getJSONFromUrl(URL);

                if (jsonObject != null) {
                    return jsonObject.getString("data");
                }

            } catch (Exception e) {
                Log.e("Error", e.getMessage());
            }
            return null;
        }

        @Override
        protected void onPostExecute(String data) {
            super.onPostExecute(data);
            progressBar.setVisibility(View.GONE);

            if(data == null) {
                Toast.makeText(ArticleHeadline.this, "Error, please try later", Toast.LENGTH_LONG).show();
            }
        }
    }

}
