package com.abhay.rebelfoodtask.utils;

import android.content.Context;

import com.abhay.rebelfoodtask.data.Repository;
import com.abhay.rebelfoodtask.data.local.AppDatabase;
import com.abhay.rebelfoodtask.data.rest.ApiClient;
import com.abhay.rebelfoodtask.data.rest.ApiService;
import com.abhay.rebelfoodtask.ui.main.UserViewModelFactory;

public class InjectorUtils {

    private InjectorUtils() {
    }

    private static Repository provideRepository(Context context) {
        AppDatabase database = AppDatabase.getInstance(context.getApplicationContext());
        AppExecutors executors = AppExecutors.getInstance();
        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        return Repository.getInstance(apiService, database.userDao(), executors);
    }

    public static UserViewModelFactory provideUserViewModelFactory(Context context) {
        Repository repository = provideRepository(context.getApplicationContext());
        return new UserViewModelFactory(repository);
    }
}
