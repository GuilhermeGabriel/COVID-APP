package net.yan.covid.activity;


import android.content.ClipData;
import android.content.Intent;

import android.os.Bundle;

import android.text.SpannableString;
import android.text.style.TextAppearanceSpan;
import android.view.Menu;
import android.view.MenuItem;

import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.material.navigation.NavigationView;

import net.yan.covid.R;
import net.yan.covid.model.Covid;

import net.yan.covid.ui.home.HomeFragment;
import net.yan.covid.ui.home.Update;


import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {


    public Update update;
    public SearchView searchView;
    private Adapter adp;
    private Fragment df;
    private Button botao;
    private TextView texto;
    private Covid covid;
    private RecyclerView recyclerView;
    private List<Covid> lista = new ArrayList<>();
    private AppBarConfiguration mAppBarConfiguration;
    public  MenuItem item;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startActivity(new Intent(this, AnimaActivity.class));
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        Menu menu = navigationView.getMenu();
        MenuItem tools = menu.findItem(R.id.com);
        SpannableString s = new SpannableString(tools.getTitle());
        s.setSpan(new TextAppearanceSpan(this, R.style.TextAppearance44), 0, s.length(), 0);
        tools.setTitle(s);
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_share)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    public void updateApi(Update listener) {
        update = listener;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        item = menu.findItem(R.id.search);
        searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                update.updatefrag(newText);
                //getSupportFragmentManager().beginTransaction().attach().commit()
                return false;
            }
        });
        item.setChecked(true);

        return true;

    }


    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }



}
