package com.bishal.datafrominternet.viewmodel;


import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import com.bishal.datafrominternet.model.CrewModel;
import com.bishal.datafrominternet.network.APIService;
import com.bishal.datafrominternet.network.RetroInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CrewViewModel extends ViewModel {

    private MutableLiveData<List<CrewModel>> crewList;

    public CrewViewModel(){
        crewList = new MutableLiveData<>();
    }

    public MutableLiveData<List<CrewModel>> getCrewListObserver() {
        return crewList;

    }

    public void makeApiCall() {
        APIService apiService = RetroInstance.getRetroClient().create(APIService.class);
        Call<List<CrewModel>> call = apiService.getCrewList();
        call.enqueue(new Callback<List<CrewModel>>() {
            @Override
            public void onResponse(Call<List<CrewModel>> call, Response<List<CrewModel>> response) {
                crewList.postValue(response.body());
            }

            @Override
            public void onFailure(Call<List<CrewModel>> call, Throwable t) {
                crewList.postValue(null);
            }
        });
    }
}
