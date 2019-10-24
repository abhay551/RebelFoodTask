package com.abhay.rebelfoodtask.ui.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.abhay.rebelfoodtask.data.Repository;
import com.abhay.rebelfoodtask.data.model.User;

import java.util.List;

class UsersViewModel extends ViewModel {

    private Repository repository;

    private LiveData<List<User>> userList;

    private LiveData<List<User>> favoriteList;

    UsersViewModel(Repository repository) {
        this.repository = repository;
        userList = repository.getUserList();
    }

    LiveData<List<User>> getUserList() {
        return userList;
    }

    LiveData<List<User>> getFavoriteList() {
        favoriteList = repository.getFavoriteList();
        return favoriteList;
    }

    void updateFavorites(User user) {
        repository.updateFavorite(user);
    }
}
