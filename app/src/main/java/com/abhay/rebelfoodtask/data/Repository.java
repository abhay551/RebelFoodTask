package com.abhay.rebelfoodtask.data;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.abhay.rebelfoodtask.data.local.UserDao;
import com.abhay.rebelfoodtask.data.model.User;
import com.abhay.rebelfoodtask.data.rest.ApiService;
import com.abhay.rebelfoodtask.utils.AppExecutors;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repository {

    private static final Object LOCK = new Object();
    private static Repository sInstance;
    private ApiService apiService;
    private UserDao userDao;
    private AppExecutors appExecutors;

    private Repository(ApiService apiService, UserDao userDao, AppExecutors appExecutors) {
        this.apiService = apiService;
        this.userDao = userDao;
        this.appExecutors = appExecutors;
    }

    public synchronized static Repository getInstance(
            ApiService apiService, UserDao userDao, AppExecutors appExecutors) {
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = new Repository(apiService, userDao,
                        appExecutors);
            }
        }
        return sInstance;
    }

    public LiveData<List<User>> getUserList() {
        Call<List<User>> call = apiService.getUsers();
        LiveData<List<User>> stateLiveData = userDao.getAllList();
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(@NonNull Call<List<User>> call, @NonNull Response<List<User>> response) {
                if (response.body() != null) {
                    appExecutors.diskIO().execute(() -> {
                        userDao.insertAll(response.body());
                    });
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<User>> call, @NonNull Throwable t) {

            }
        });
        return stateLiveData;
    }

    public LiveData<List<User>> getFavoriteList() {
        return userDao.getFavoriteList();
    }

    public void updateFavorite(User user) {
        appExecutors.diskIO().execute(() -> {
            int updateSelection = user.isFavorite() ? 0 : 1;
            int id = user.getId();
            userDao.updatesFavorites(updateSelection, id);
        });
    }
}
