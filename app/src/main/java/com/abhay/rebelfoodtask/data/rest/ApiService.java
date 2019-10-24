package com.abhay.rebelfoodtask.data.rest;


import com.abhay.rebelfoodtask.data.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET("users")
    Call<List<User>> getUsers();

}
