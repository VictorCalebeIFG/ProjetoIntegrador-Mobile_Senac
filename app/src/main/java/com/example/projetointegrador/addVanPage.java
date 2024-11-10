package com.example.projetointegrador;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;
import java.util.Random;

public class addVanPage extends AppCompatActivity implements View.OnClickListener {

    Button btnenviar;
    EditText mlVanDesc;

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_van_page);

        btnenviar = findViewById(R.id.btnenviar);
        btnenviar.setOnClickListener(this);

        mlVanDesc = findViewById(R.id.mlVanDesc);


    }

    public void appendData(String data) {
        DataBase db = new DataBase(this);
        String action = "?action=append&data=" + data + "&wkname=Vans";
        db.makeRequest(action, new DataBase.VolleyCallback() {
            @Override
            public void onSuccess(List<List<String>> result) {
                Log.d("ListaVans Databse", result.toString());
                finish();

            }
        });
    }

    //WebAppURL?action=append&data=a,b,c&wkname=worksheetname

    //generate a random stringcode with 5 characters


    public String generateRandomString(int length) {
            Random random = new Random();
            StringBuilder sb = new StringBuilder(length);

            for (int i = 0; i < length; i++) {
                int index = random.nextInt(CHARACTERS.length());
                sb.append(CHARACTERS.charAt(index));
            }

            return sb.toString();
        }

    @Override
    public void onClick(View view) {

        if (view == btnenviar) {

            String editText = generateRandomString(5)+','+mlVanDesc.getText().toString();
            appendData(editText);

            //wait 2 seconds
            try {
                Thread.sleep(2000);
                finish();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }
}



