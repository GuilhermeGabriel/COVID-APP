package net.yan.covid.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import net.yan.covid.R;
import net.yan.covid.model.Covid;

import androidx.appcompat.app.AppCompatActivity;

public class Dados extends AppCompatActivity {

    private TextView pais_d;
    private TextView casos_D;
    private TextView today_Cases;
    private TextView deaths;
    private TextView tdeaths;
    private TextView recovered;
    private TextView critical;
    private TextView atives;
    private TextView pMillion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dados);

        getSupportActionBar().hide();
        pais_d = findViewById(R.id.pais_d);
        casos_D = findViewById(R.id.casos_d);
        today_Cases = findViewById(R.id.tcases_d);
        deaths = findViewById(R.id.deaths_d);
        tdeaths = findViewById(R.id.tdeaths_d);
        recovered = findViewById(R.id.recovered_d);
        critical = findViewById(R.id.critical_d);
        atives = findViewById(R.id.atives_d);
        pMillion = findViewById(R.id.pMilion_d);

        Bundle bundle = getIntent().getExtras();
        Covid covid = (Covid) bundle.getSerializable("object");
        pais_d.setText(pais_d.getText()+covid.getCoutry());
        casos_D.setText(casos_D.getText()+covid.getCases());
        today_Cases.setText(today_Cases.getText()+covid.getTodayCase());
        deaths.setText(deaths.getText()+covid.getDeaths());
        tdeaths.setText(tdeaths.getText()+covid.getTodayDeaths());
        recovered.setText(recovered.getText()+covid.getRecovery());
        critical.setText(critical.getText()+covid.getCritical());
        atives.setText(atives.getText()+covid.getActive());
        pMillion.setText(pMillion.getText()+covid.getpMillions());
        Log.d("RES:", covid.getCoutry());
    }
}
