package com.example.AMS;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Weather extends AppCompatActivity {

    String district = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        Button find = (Button)findViewById(R.id.submit);
        Spinner distSpinner = findViewById(R.id.city);

        final TextView tempr = findViewById(R.id.temp);
        final TextView desc = findViewById(R.id.desc);
        final TextView pressure = findViewById(R.id.clouds);
        final TextView sunrise = findViewById(R.id.sunrise);
        final TextView sunset = findViewById(R.id.sunset);
        final TextView windd = findViewById(R.id.wind);

        ArrayAdapter<CharSequence> distAdapter = ArrayAdapter.createFromResource(this, R.array.districts, android.R.layout.simple_spinner_item);
        distAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        distSpinner.setAdapter(distAdapter);

        distSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                district = "" + parent.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        final String apiKey = "0c04195e9ee49de1fa8aaf631bc5c835";

        find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String city = district;
                String url = "https://api.openweathermap.org/data/2.5/weather?q=" + city +"&appid=" + apiKey;
                Log.i("city", "" + city);

                final RequestQueue requestQueue = Volley.newRequestQueue(Weather.this);
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,

                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {

                                try {
                                    JSONArray weather = response.getJSONArray("weather");
                                    JSONObject main = response.getJSONObject("main");
                                    JSONObject wind = response.getJSONObject("wind");
                                    JSONObject sys = response.getJSONObject("sys");
                                    System.out.println(weather);
                                    System.out.println(main);
                                    System.out.println(sys);
                                    System.out.println(wind);
                                    JSONObject temp = weather.getJSONObject(0);
                                    String describ = temp.getString("main");
                                    String humid = main.getString("humidity");
                                    String speed = wind.getString("speed");
                                    String sunr = sys.getString("sunrise");
                                    String suns = sys.getString("sunset");
                                    String tempString = main.getString("temp");
                                    desc.setText(describ);
                                    tempr.setText(tempString.substring(0,2) + " °C");
                                    pressure.setText(humid);
                                    sunrise.setText(sunr);
                                    sunset.setText(suns);
                                    windd.setText(speed);
                                    Toast.makeText(getApplicationContext(), "Updated", Toast.LENGTH_SHORT).show();


                                } catch (JSONException e) {
                                    Toast.makeText(getApplicationContext(), "No results found", Toast.LENGTH_SHORT).show();
                                    desc.setText("No results found ..");
                                    e.printStackTrace();
                                }

                            }
                        },

                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.i("Error", ""+error);
                            }
                        }

                );
                requestQueue.add(jsonObjectRequest);

            }
        });
    }
}
