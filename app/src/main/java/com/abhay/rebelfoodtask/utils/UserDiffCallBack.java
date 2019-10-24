package com.abhay.rebelfoodtask.utils;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;

import com.abhay.rebelfoodtask.data.model.User;

import java.util.List;

public class UserDiffCallBack extends DiffUtil.Callback {

    private final List<User> oldUserList;
    private final List<User> newUserList;

    public UserDiffCallBack(List<User> oldUserList, List<User> newUserList) {
        this.oldUserList = oldUserList;
        this.newUserList = newUserList;
    }

    @Override
    public int getOldListSize() {
        return oldUserList.size();
    }

    @Override
    public int getNewListSize() {
        return newUserList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldUserList.get(oldItemPosition).getId() == newUserList.get(
                newItemPosition).getId();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        final User oldUser = oldUserList.get(oldItemPosition);
        final User newUser = newUserList.get(newItemPosition);
        return oldUser.isFavorite() == newUser.isFavorite();
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        // Implement method if you're going to use ItemAnimator
        return super.getChangePayload(oldItemPosition, newItemPosition);
    }
}