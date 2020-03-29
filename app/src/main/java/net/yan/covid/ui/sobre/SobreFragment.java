package net.yan.covid.ui.sobre;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;


import net.yan.covid.R;
import net.yan.covid.activity.MainActivity;

import java.util.List;

public class SobreFragment extends Fragment {

    private String descricao = "App criado com base nos dados fornecidos pela Microsoft sobre os casos de corona vírus no mundo.";
    private String email = "yancamilo8@gmail.com";

    private TextView insta;
    private TextView face;
    private TextView emaill;
    private TextView github;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_sobre, container, false);

        insta = root.findViewById(R.id.card_insta);
        face = root.findViewById(R.id.card_face);
        emaill = root.findViewById(R.id.card_email);
        github = root.findViewById(R.id.card_git);

        emaill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enviarEmail();
            }
        });
        face.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                facebook();
            }
        });
        github.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirGit();
            }
        });
        insta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirInsta();
            }
        });


        return root;
    }






    public void enviarEmail() {
        //String endereco = "https://www.facebook.com/yan.camilo.925";
        //Intent it = new Intent(Intent.ACTION_VIEW, Uri.parse(endereco));
        //Intent sendIntent = new Intent(Intent.ACTION_SEND);
        //startActivity(it);

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL, new String[] {"yancamilo8@gmail.com"});
        intent.setType("message/rfc822");
        //intent.setType("application/pdf");
        //intent.setType("image/*");
        //intent.setType("text/plain");
        startActivity(Intent.createChooser(intent, "Compartilhar"));
    }
    public void facebook(){
        String endereco = "http://www.facebook.com/yan.camilo.925";
        Uri uri = Uri.parse(endereco);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
    public void abrirGit(){
        String endereco = "https://github.com/YanCamiloDev/COVID-APP";
        Uri uri = Uri.parse(endereco);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    public void abrirInsta(){
        Uri uri = Uri.parse("http://instagram.com/_u/yan_s_camilo");
        Intent insta = new Intent(Intent.ACTION_VIEW, uri);
        insta.setPackage("com.instagram.android");

        if (isIntentAvailable(getContext(), insta)){
            startActivity(insta);
        } else{
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/")));
        }
    }


    private boolean isIntentAvailable(Context ctx, Intent intent) {
        final PackageManager packageManager = ctx.getPackageManager();
        List<ResolveInfo> list = packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
        return ((List) list).size() > 0;
    }
}