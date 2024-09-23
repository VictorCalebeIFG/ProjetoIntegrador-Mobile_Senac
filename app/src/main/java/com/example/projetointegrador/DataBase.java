package com.example.projetointegrador;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class DataBase {

    String url;
    RequestQueue queue;
    static final String TAG = "DataBaseLog";

    public DataBase(Context context) {
        // Corrigir como obter a URL da string de recursos
        this.url = context.getString(R.string.url);
        this.queue = Volley.newRequestQueue(context);

    }

    public void makeRequest(String action, final VolleyCallback callback){
        String act = url.concat(action);
        List<List<String>> listaDeListas = new ArrayList<>();

        Log.d(TAG, act);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, act, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONArray userArray = jsonArray.getJSONArray(i);
                        List<String> innerList = new ArrayList<>();
                        String username = userArray.getString(0);
                        innerList.add(username);
                        String password = userArray.getString(1);
                        innerList.add(password);
                        listaDeListas.add(innerList);

                        Log.d(TAG, "Username: " + username + ", Password: " + password);
                    }

                    // Chama o callback quando a resposta for processada
                    callback.onSuccess(listaDeListas);

                } catch (Exception e) {
                    e.printStackTrace();
                    Log.d(TAG, "onResponse: erro ao processar resposta");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "Erro na requisição: " + error.getMessage());
            }
        });

        queue.add(stringRequest);
    }

    public interface VolleyCallback {
        void onSuccess(List<List<String>> result);
    }

}
