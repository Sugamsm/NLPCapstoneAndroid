package com.mankadproject.sunycapstone;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button articles, novels, poems;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.appBarLayout);
        setSupportActionBar(toolbar);

        articles = findViewById(R.id.articles_button);
        novels = findViewById(R.id.novels_button);
        poems = findViewById(R.id.poems_button);

        articles.setOnClickListener(this);
        novels.setOnClickListener(this);
        poems.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.articles_button:
                intent = new Intent(this, ArticleHeadline.class);
                break;

            case R.id.novels_button:
                intent = new Intent(this, NovelStories.class);
                break;

            case R.id.poems_button:
                intent = new Intent(this, Poetry.class);
                break;

            default:
                break;
        }
        if(intent != null) {
            startActivity(intent);
        }
    }
}
