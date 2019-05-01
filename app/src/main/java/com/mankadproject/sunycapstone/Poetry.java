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

public class Poetry extends AppCompatActivity implements View.OnClickListener {
    EditText poemInput;
    Button poemSearch;
    TextView poemView;

    ProgressBar progressBar;

    Toolbar toolbar;

    private static String URL = "http://localhost:/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poetry);

        toolbar = findViewById(R.id.appBarLayout);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        progressBar = findViewById(R.id.poetryProgress);

        poemInput = findViewById(R.id.poemInput);
        poemSearch = findViewById(R.id.poemSearch);
        poemView = findViewById(R.id.poemAuto);

        poemSearch.setOnClickListener(this);

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
                String text = poemInput.getText().toString();
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
                Toast.makeText(Poetry.this, "Error, please try later", Toast.LENGTH_LONG).show();
            }
        }
    }
}
