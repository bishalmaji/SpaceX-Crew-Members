package com.bishal.datafrominternet.network;


import com.bishal.datafrominternet.model.CrewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIService {

    @GET("v4/crew")
    Call<List<CrewModel>> getCrewList();
}
