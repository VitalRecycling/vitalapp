package com.example.android.vitalrecycling;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class Tab2Fragment extends Fragment {
    private static final String TAG = "Tab2Fragment";

    private Button btnTEST;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.tab2_fagments,container,false);

        btnTEST = (Button) view.findViewById(R.id.btnTEST2);

        btnTEST.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "TESTING BUTTON CLICK 2", Toast.LENGTH_SHORT).show();

            }
        });
        return view;
    }
}
