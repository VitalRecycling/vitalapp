package com.example.android.vitalrecycling;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class Tab3FragmentR extends Fragment {

    private static final String TAG = "Tab3FragmentR";

    private Button LocationBtn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.tab3_fagmentsr,container,false);

        LocationBtn = view.findViewById(R.id.RecycleLocBtn);

        LocationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getActivity(), "TESTING BUTTON CLICK 3", Toast.LENGTH_SHORT).show();
                Intent i;
                i = new Intent(getActivity(), MapsActivity.class);
                startActivity(i);
            }
        });

        return view;
    }
}
