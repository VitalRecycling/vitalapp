package com.example.android.vitalrecycling;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import android.support.v7.widget.RecyclerView;

public class Tab1FragmentR extends Fragment {
    private static final String TAG = "Tab1FragmentR";

    private RecyclerView mMaterialList;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private Button btnTEST;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.tab1_fagmentsr,container,false);

        String[] materials = {"Aluminum Cans & Scrap: " , "   Foil", "   Pie Tins", "   Soda Cans",
                "Bi-Metal, Steel, & Tin Cans: ", "   Metal lids", "   fruit, vegetable, & pet food cans, etc.",
                "Paper: ", "   Magazines", "   Office paper", "   Newspapers",
                "Plastic: ", "   Recycling Number #1 - #7", "   Milk Jugs", "   Shampoo Bottles", "   Soda Bottles", "   Misc. Bottles"};

        /*
        btnTEST = view.findViewById(R.id.btnTEST);
        btnTEST.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "TESTING BUTTON CLICK 1", Toast.LENGTH_SHORT).show();

            }
        });
        */

        mMaterialList = view.findViewById(R.id.materialList);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mMaterialList.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(getActivity());
        mMaterialList.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)
        mAdapter = new RecycleViewAdapter(materials);
        mMaterialList.setAdapter(mAdapter);


        return view;

    }

}
