package com.abhay.rebelfoodtask.data.local;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.abhay.rebelfoodtask.data.model.User;

import java.util.List;

@Dao
public interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(User user);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertAll(List<User> user);

    @Query("SELECT * from user")
    LiveData<List<User>> getAllList();

    @Query("SELECT * from user WHERE isFavorite ==1")
    LiveData<List<User>> getFavoriteList();

    @Query("SELECT COUNT(*) from user WHERE id =:id")
    int getCountFromUserId( int id);

    @Query("UPDATE user SET isFavorite = :favorite WHERE id =:id")
    void updatesFavorites(int favorite, int id);

    @Delete
    void delete(User user);

    @Query("DELETE FROM user")
    void clearTable();
}
