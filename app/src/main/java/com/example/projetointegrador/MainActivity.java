package com.example.projetointegrador;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

import java.util.List;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button butLogin;
    Button butCads;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        butLogin    = findViewById(R.id.butLogin);
        butCads     = findViewById(R.id.butCads);

        butLogin.setOnClickListener(this);
        butCads.setOnClickListener(this);



        //String url = "?action=getdata&wkname=Data";

        //DataBase db = new DataBase(this);
        /*
        db.makeRequest(url, new DataBase.VolleyCallback() {
            @Override
            public void onSuccess(List<List<String>> result) {
                // Aqui você pode continuar o processamento dos dados, mas eles já estarão no Logcat
            }
        });
        */
    }

    @Override
    public void onClick(View view) {

        if (view == butLogin){
            Intent loginScreen = new Intent(this,Login.class);
            startActivity(loginScreen);
        }

    }
}