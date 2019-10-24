package com.abhay.rebelfoodtask.ui.main;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.abhay.rebelfoodtask.data.Repository;

public class UserViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final Repository mRepository;

    public UserViewModelFactory(Repository repository) {
        this.mRepository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new UsersViewModel(mRepository);
    }
}
