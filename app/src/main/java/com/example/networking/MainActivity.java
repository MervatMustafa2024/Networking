package com.example.networking;

import static java.net.URLEncoder.encode;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.networking.databinding.ActivityMainBinding;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    protected RequestQueue queue = null;
    protected String cityName;
    final protected  String API_KEY="31e20d886922cc1856308ed89aae685d";
    private JSONObject response;

    @SuppressLint("SuspiciousIndentation")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

      binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.btnGet.setOnClickListener(e->{
            cityName = binding.edtCity.getText().toString();
            queue = Volley.newRequestQueue(this);
            String stringURL = null;
            try {
                stringURL = "https://api.openweathermap.org/data/2.5/weather?q=" + encode(cityName,  "UTF-8") + "&appid="+API_KEY+ "&units=metric";
            } catch (UnsupportedEncodingException ex) {
                throw new RuntimeException(ex);
            }


            Log.w("mervat1", stringURL);



            JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, stringURL, null,
                    (response) -> {

                        JSONObject mainObject = null;
                        JSONObject weatherObject = null;

                        double current ;
                        double min ;
                        double max ;
                        int humidity ;
                        String iconCode;

                        try {
                            Log.w("mervat","2");
                            mainObject = response.getJSONObject("main");
                            Log.w("mervat","3");
                            current = mainObject.getDouble("temp");
                            min = mainObject.getDouble("temp_min");
                            max = mainObject.getDouble("temp_max");
                            humidity = mainObject.getInt("humidity");


                        } catch (JSONException ex) {
                            Log.w("mervat","4");
                            throw new RuntimeException(ex);
                        }


                        runOnUiThread( ( ) -> {
                            binding.temp.setText("The current temperature is:  " + current);
                            binding.temp.setVisibility(View.VISIBLE);
                            binding.minTemp.setText("The min temperature is:  " + min);
                            binding.minTemp.setVisibility(View.VISIBLE);
                            binding.maxTemp.setText("The min temperature is:  " + max);
                            binding.maxTemp.setVisibility(View.VISIBLE);
                            binding.humidityTemp.setText("The min temperature is:  " + humidity);
                            binding.humidityTemp.setVisibility(View.VISIBLE);
                            // do this for all the textViews...
                        });

                    }, (error) -> { });
            queue.add (request);
            String imageUrl = null;







            ///




        });

    }
}