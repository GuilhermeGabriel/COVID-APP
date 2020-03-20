package net.yan.covid.ui.sobre;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;


import net.yan.covid.R;

public class SobreFragment extends Fragment {

    private String descricao = "App criado com base nos dados fornecidos pela Microsoft sobre os casos de corona vírus no mundo.";
    private String email = "yancamilo8@gmail.com";


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_sobre, container, false);

        return root;
    }


}