package com.example.projetointegrador;

import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

public class ListaVans extends AppCompatActivity {

    LinearLayout vanLayout;
    ProgressBar pgBar;

    List<vanCheckBox> vansList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lista_vans);

        vanLayout = findViewById(R.id.VanLayout);
        pgBar = findViewById(R.id.pgBar);

        pgBar.setVisibility(vanLayout.VISIBLE);
        loadData();



    }

    public void loadData() {
        DataBase db = new DataBase(this);
        String action = "?action=getdata&wkname=Vans";
        db.makeRequest(action, new DataBase.VolleyCallback() {
            @Override
            public void onSuccess(List<List<String>> result) {
                pgBar.setVisibility(vanLayout.INVISIBLE);
                Log.d("ListaVans Databse", result.toString());
                for (List<String> row : result) {
                    vanCheckBox vanDescription = new vanCheckBox(ListaVans.this);
                    vanDescription.setText(row.get(1));
                    vanDescription.user = row.get(0);
                    LinearLayout intenLayout = new LinearLayout(ListaVans.this);
                    intenLayout.addView(vanDescription);
                    vanLayout.addView(intenLayout);
                    vansList.add(vanDescription);
                }
            }
        });

    }

}