package com.example.vmac.WatBot;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

public class DiseasesActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diseases);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)

        String[] diseases = new String[DrWatsonApplication.diseasesJson.getDiseases().size()];
        for (int i = 0; i < diseases.length; i++) {
            String s = DrWatsonApplication.diseasesJson.getDiseases().get(i).type;
            String[] splitString = s.split("_");
            StringBuilder builder = new StringBuilder("");
            for (int j = 0; j < splitString.length; j++) {
                splitString[j] = ("" + splitString[j].charAt(0)).toUpperCase() + splitString[j].substring(1, splitString[j].length());
                builder.append(splitString[j] + " ");
            }
            diseases[i] = builder.toString();
        }

        mAdapter = new DiseasesAdapter(getApplicationContext(), diseases);
        mRecyclerView.setAdapter(mAdapter);
    }

}
