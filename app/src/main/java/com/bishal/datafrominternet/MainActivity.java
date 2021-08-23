package com.bishal.datafrominternet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.bishal.datafrominternet.adapter.CrewAdapter;
import com.bishal.datafrominternet.database.AppDatabase;
import com.bishal.datafrominternet.model.CrewModel;
import com.bishal.datafrominternet.viewmodel.CrewViewModel;
import java.util.List;

public class MainActivity extends AppCompatActivity implements CrewAdapter.ItemClickListener {

    private List<CrewModel> crewModelList;
    private CrewAdapter adapter;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button refresh = findViewById(R.id.btn_refresh);
        Button delete = findViewById(R.id.btn_delete);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter =  new CrewAdapter(this, crewModelList, this);
        recyclerView.setAdapter(adapter);
        db=AppDatabase.getDbInstance(this.getApplicationContext());
        observerMethod();
        loadUserList();
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Data Refreshed", Toast.LENGTH_SHORT).show();
          observerMethod();
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.userDao().delete();
                adapter.clear();
                Toast.makeText(MainActivity.this, "Data Deleted", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void observerMethod() {
        CrewViewModel viewModel = ViewModelProviders.of(this).get(CrewViewModel.class);
        viewModel.getCrewListObserver().observe(this, new Observer<List<CrewModel>>() {
            @Override
            public void onChanged(List<CrewModel> crewModels) {
                if(crewModels != null) {
                    crewModelList = crewModels;
                    if (db.userDao().getAllUsers().isEmpty()){
                        db.userDao().insertUser(crewModels);
                        loadUserList();
                    }
                }
            }
        });
        viewModel.makeApiCall();
    }

    private void loadUserList() {
        AppDatabase db = AppDatabase.getDbInstance(this.getApplicationContext());
        crewModelList =db.userDao().getAllUsers();
        adapter.setCrewList(crewModelList);
    }
    @Override
    public void onMovieClick(CrewModel crewModel) {
        Toast.makeText(this, "Crew Name : " +crewModel.getName(), Toast.LENGTH_SHORT).show();
    }
}