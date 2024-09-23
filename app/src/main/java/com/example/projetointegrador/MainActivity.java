package com.example.projetointegrador;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        String url = "https://script.google.com/macros/s/AKfycbz9T2lWp8J9sGtGWmzKh2MabQlDEyjEEF7TnXpL4osTGU964CLKKsctEYTlkndE6dy-/exec?action=getdata&wkname=Data";


        final TextView textView = (TextView) findViewById(R.id.data);

        RequestQueue queue = Volley.newRequestQueue(this);

// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            StringBuilder formattedResponse = new StringBuilder();

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONArray userArray = jsonArray.getJSONArray(i);
                                String username = userArray.getString(0);
                                String password = userArray.getString(1);
                                formattedResponse.append("User: ").append(username).append(", Password: ").append(password).append("\n");
                            }

                            textView.setText(formattedResponse.toString());
                        } catch (Exception e) {
                            e.printStackTrace();
                            textView.setText("Error parsing response!");
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                textView.setText("That didn't work!");
            }
        });

// Add the request to the RequestQueue.
        queue.add(stringRequest);



    }
}