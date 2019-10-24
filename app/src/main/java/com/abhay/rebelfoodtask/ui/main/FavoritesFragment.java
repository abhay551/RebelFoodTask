package com.abhay.rebelfoodtask.ui.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.abhay.rebelfoodtask.R;
import com.abhay.rebelfoodtask.data.model.User;
import com.abhay.rebelfoodtask.ui.map.MapActivity;
import com.abhay.rebelfoodtask.utils.InjectorUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.abhay.rebelfoodtask.ui.map.MapActivity.USER_DATA_KEY;

public class FavoritesFragment extends Fragment implements UserAdapter.UserItemClickListener {

    private Context context;
    private UsersViewModel usersViewModel;

    @BindView(R.id.rv_user_list)
    RecyclerView rvFavoriteList;

    private UserAdapter adapter;

    public static FavoritesFragment newInstance() {
        return new FavoritesFragment();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorites, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        UserViewModelFactory factory = InjectorUtils.provideUserViewModelFactory(context.getApplicationContext());
        usersViewModel = ViewModelProviders.of(this, factory).get(UsersViewModel.class);
        usersViewModel.getFavoriteList().observe(getViewLifecycleOwner(), this::setFavoritesAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    private void setFavoritesAdapter(List<User> userList) {
        if (adapter == null) {
            adapter = new UserAdapter(context, userList, this);
            rvFavoriteList.setAdapter(adapter);
        } else {
            adapter.setItems(userList);
        }
    }

    @Override
    public void onUserItemClick(User user) {
        Intent intent = MapActivity.newInstance(context);
        intent.putExtra(USER_DATA_KEY, (Parcelable) user);
        startActivity(intent);
    }

    @Override
    public void onFavoritesClick(User user) {
        usersViewModel.updateFavorites(user);
    }
}
      