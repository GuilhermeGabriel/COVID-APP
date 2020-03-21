package net.yan.covid.ui.home;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.airbnb.lottie.L;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import net.yan.covid.R;
import net.yan.covid.activity.AnimaActivity;
import net.yan.covid.activity.Dados;
import net.yan.covid.activity.MainActivity;

import net.yan.covid.adapter.Adapter;
import net.yan.covid.adapter.ClickListener;
import net.yan.covid.model.Covid;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {


    private Button botao;
    private TextView texto;
    private Covid covid;
    public String value = null;
    private RecyclerView recyclerView;
    private List<Covid> lista = new ArrayList<>();
    private JSONArray object;
    private Adapter adapter;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_pais, container, false);

        ((MainActivity) getActivity()).updateApi(new Update() {
            @Override
            public void updatefrag(String text) {
                adapter.getFilter().filter(text);
            }
        });


        MyTask myTask = new MyTask();
        myTask.execute();
        recyclerView = root.findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(container.getContext(), 2));
        recyclerView.addOnItemTouchListener(new ClickListener(container.getContext(), recyclerView, new ClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                covid = new Covid();
                covid = lista.get(position);
                Intent intent = new Intent(view.getContext(), Dados.class);
                intent.putExtra("object", covid);
                startActivity(intent);
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        }));

        return root;
    }


    @Override
    public void onStart() {
        super.onStart();
    }

    class MyTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected Void doInBackground(Void... voids) {
            getData();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

        }
    }


    public void getData() {
        RequestQueue queue = Volley.newRequestQueue(getContext());
        String url2 = "https://pomber.github.io/covid19/timeseries.json/";
        String url = "https://corona.lmao.ninja/countries/";
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray object = new JSONArray(response);
                            for (int i = 0; i < object.length(); i++) {
                                covid = new Covid();
                                JSONObject js = object.getJSONObject(i);
                                covid.setCases(js.getString("cases"));
                                covid.setCoutry(js.getString("country"));
                                covid.setCritical(js.getString("critical"));
                                covid.setRecovery(js.getString("recovered"));
                                covid.setTodayCase(js.getString("todayCases"));
                                covid.setDeaths(js.getString("deaths"));
                                covid.setTodayDeaths(js.getString("todayDeaths"));
                                covid.setActive(js.getString("active"));
                                covid.setpMillions(js.getString("casesPerOneMillion"));
                                Log.d("AA", covid.getCoutry());
                                lista.add(covid);
                            }
                            if (lista.size() == 0) {
                                alerta("Dados", "Dados em processo de alteração, volte mais tarde.");
                            }
                            configRec();
                        } catch (JSONException e) {
                            alerta("Dados", "Dados em processo de alteração, volte mais tarde.");
                            Log.d("ERROR", e.getMessage());
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                NetworkResponse error_code = error.networkResponse;

                if (error_code == null) {
                    // Sem conexão a internet no aparelho
                    alerta("Atenção", "Você precisa está conectado.");

                } else {
                    // Sem conexão ao servidor
                        alerta("Servidos", "Servidor indisponível no momento, volte mais tarde.");
                }
                Log.d("ERROR", error.getMessage());
            }
        });

        queue.add(stringRequest);
    }

    public void alerta(String title, String mess) {

        AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
        alert.setCancelable(true);
        alert.setTitle(title);
        alert.setMessage(mess);
        alert.setPositiveButton("Fechar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try {
                    getActivity().finish();
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
            }
        });
        alert.create().show();

    }

    public void configRec() {
        adapter = new Adapter(lista);
        adapter.getFilter().filter(value);
        recyclerView.setAdapter(adapter);
    }

    public void fil(String v) {
        String f = v;
        value = f;
    }


    public void abrirSplash() {
        Intent intent = new Intent(getContext(), AnimaActivity.class);
        startActivity(intent);
    }

    public void dialogo() {
        AlertDialog dialog;
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.activity_anima, null));
        builder.setCancelable(true);
        dialog = builder.create();
        dialog.show();
    }
}