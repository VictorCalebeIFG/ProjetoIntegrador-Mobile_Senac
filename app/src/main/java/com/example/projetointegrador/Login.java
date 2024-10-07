package com.example.projetointegrador;

import static android.app.PendingIntent.getActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class Login extends AppCompatActivity implements View.OnClickListener {

    Button butEntrar;
    TextView user;
    TextView password;
    ProgressBar pgBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        user        = findViewById(R.id.user);
        password    = findViewById(R.id.password);
        pgBar       = findViewById(R.id.progressBar);


        butEntrar = findViewById(R.id.butEntrar);
        butEntrar.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        String userValue = user.getText().toString();
        String passwordValue = password.getText().toString();

        if(view == butEntrar){
            pgBar.setVisibility(view.VISIBLE);
            String action = "?action=getdata&wkname=Users";
            DataBase db = new DataBase(this);

            db.makeRequest(action, new DataBase.VolleyCallback() {
                @Override
                public void onSuccess(List<List<String>> result) {
                    Log.d("Login Databse",result.toString());
                    String userFound = "";

                    for (List<String> row : result) {
                        if (row.get(0).equals(userValue) & row.get(1).equals(passwordValue)){
                            Login.this.sendMsg("Usuário Encotrado e Senha Correta");
                            userFound = userValue;
                            pgBar.setVisibility(view.INVISIBLE);
                            Login.this.changeToNextPage();
                            break;
                        }
                    }
                    if (userFound.equals("")){
                        pgBar.setVisibility(view.INVISIBLE);
                        Login.this.sendMsg("Usuário Não Econtrado.");
                    }


                }
            });



        }
    }

    public void sendMsg(String msg){
        Toast toast = Toast.makeText(this,msg,Toast.LENGTH_LONG);
        toast.show();

    }

    public void changeToNextPage(){
        Intent vanPage = new Intent(this,ListaVans.class);
        startActivity(vanPage);
    }
}