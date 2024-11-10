package com.example.projetointegrador;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class ListaVans extends AppCompatActivity implements View.OnClickListener {

    LinearLayout vanLayout;
    ProgressBar pgBar;
    Button addVan;
    List<vanCheckBox> vansList;

    private boolean isFirstLoad = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lista_vans);

        vanLayout = findViewById(R.id.myvans);
        pgBar = findViewById(R.id.pgBar);
        addVan = findViewById(R.id.btnAdd);

        addVan.setOnClickListener(this);

        vansList = new ArrayList<>();

        pgBar.setVisibility(View.VISIBLE);

        if (isFirstLoad) {
            loadData();
            isFirstLoad = false;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!isFirstLoad) {
            loadData();
        }
    }

    public void loadData() {
        DataBase db = new DataBase(this);
        String action = "?action=getdata&wkname=Vans";

        pgBar.setVisibility(View.VISIBLE);
        db.makeRequest(action, new DataBase.VolleyCallback() {
            @Override
            public void onSuccess(List<List<String>> result) {
                pgBar.setVisibility(View.INVISIBLE);
                vanLayout.removeAllViews();  // Limpeza das views antes do loop
                vansList.clear();  // Limpeza da lista de vans antes do loop

                for (int i = 0; i < result.size(); i++) {
                    List<String> row = result.get(i);
                    String vanUser = row.get(0);

                    // Verificar duplicidade de forma mais direta
                    boolean alreadyExists = false;
                    for (vanCheckBox existingVan : vansList) {
                        if (existingVan.user.equals(vanUser)) {
                            alreadyExists = true;
                            break;
                        }
                    }

                    // Só adiciona se não for duplicado
                    if (!alreadyExists) {
                        vanCheckBox vanDescription = new vanCheckBox(ListaVans.this);
                        vanDescription.setText(row.get(1));
                        vanDescription.user = vanUser;

                        vansList.add(vanDescription);

                        // Criação de Layout Interno
                        LinearLayout intenLayout = new LinearLayout(ListaVans.this);
                        intenLayout.addView(vanDescription);

                        // Adiciona ao Layout Principal
                        vanLayout.addView(intenLayout);

                        Log.d("ListaVans Adicionado", "Adicionando usuário: " + vanUser);
                    }
                }

                // Verificação se todos os itens foram processados
                Log.d("ListaVans Finalizado", "Processamento finalizado.");
            }
        });
    }

    @Override
    public void onClick(View view) {
        if (view == addVan) {
            Intent addVanPage = new Intent(this, addVanPage.class);
            startActivity(addVanPage);
        }
    }
}
