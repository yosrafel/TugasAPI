package com.example.tugaskrs;

import com.example.tugaskrs.Admin.Model.Dosen;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface DataDosenService {
    @GET("/api/progmob/dosen/{nim_progmob}")
    Call<ArrayList<Dosen>> getDosenAll(@Path("nim_progmob") String nim_progmob);
}
