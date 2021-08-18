package com.bishal.datafrominternet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
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
        db  = AppDatabase.getDbInstance(this.getApplicationContext());
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter =  new CrewAdapter(this, crewModelList, this);
        recyclerView.setAdapter(adapter);
        CrewViewModel viewModel = ViewModelProviders.of(this).get(CrewViewModel.class);
        viewModel.getMoviesListObserver().observe(this, new Observer<List<CrewModel>>() {
            @Override
            public void onChanged(List<CrewModel> crewModels) {
                if(crewModels != null) {
                    crewModelList = crewModels;
                    if (db.userDao().getAllUsers().isEmpty()){
                        db.userDao().insertUser(crewModels);
                        loadUserList();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "No Internet Connection", Toast.LENGTH_SHORT).show();
                }
            }
        });
        viewModel.makeApiCall();
        loadUserList();
    }
    private void loadUserList() {
        AppDatabase db = AppDatabase.getDbInstance(this.getApplicationContext());
        crewModelList =db.userDao().getAllUsers();
        adapter.setMovieList(crewModelList);
    }
    @Override
    public void onMovieClick(CrewModel movie) {
        Toast.makeText(this, "Crew Name : " +movie.getName(), Toast.LENGTH_SHORT).show();
    }
}