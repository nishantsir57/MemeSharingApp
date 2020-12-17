package com.example.memeshare;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import org.json.JSONObject;

import static com.bumptech.glide.Glide.*;
import static com.example.memeshare.R.string.errorMessage;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RequestQueue requestQueue;
        ImageRequest imageRequest;
    }
    RequestQueue requestQueue;
    ImageRequest imageRequest;

    private void loadMeme()
    {
        ProgressBar loadingImage = (ProgressBar) findViewById(R.id.progressBar);
        loadingImage.setVisibility(View.VISIBLE);
        String url = "https://meme-api.herokuapp.com/gimme";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                    String url = response.getString("url");
                        Glide.with(MainActivity.this).addDefaultRequestListener(
                                new RequestListener<Drawable>() {
                            @Override
                            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                                loadingImage.setVisibility(View.GONE);
                                return false;
                            }

                            @Override
                            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                                loadingImage.setVisibility(View.GONE);
                                return false;
                            }
                        }).load(url).into(R.id.imageView);
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        Toast.makeText(this, getString(errorMessage), Toast.LENGTH_LONG).show();

                    }
                });

// Access the RequestQueue through your singleton class.
        requestQueue.add(imageRequest);
    }
    public void nextMeme(View view) {
        loadMeme();

    }

    public void shareMeme(View view) {
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("text/plain");
        i.putExtra(Intent.EXTRA_TEXT, );

    }
}