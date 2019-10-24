package com.abhay.rebelfoodtask.ui.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.abhay.rebelfoodtask.R;
import com.abhay.rebelfoodtask.data.model.User;
import com.abhay.rebelfoodtask.utils.UserDiffCallBack;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserAdapter extends RecyclerView.Adapter {

    private final Context context;
    private List<User> userList;
    private UserItemClickListener userItemClickListener;

    public UserAdapter(Context context, List<User> userList,UserItemClickListener userItemClickListener) {
        this.context = context;
        this.userList = userList;
        this.userItemClickListener = userItemClickListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                      int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.row_user, parent, false);
        return new UserViewModel(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        User user = userList.get(position);
        if (user != null) {
            UserViewModel viewHolder = (UserViewModel) holder;
            viewHolder.Name.setText(user.getName());
            viewHolder.mobile.setText(user.getPhone());
            viewHolder.email.setText(user.getEmail());
            viewHolder.company.setText(user.getCompany().getName());
            viewHolder.itemView.setOnClickListener(view -> {
                if (userItemClickListener!=null){
                    userItemClickListener.onUserItemClick(user);
                }
            });
            viewHolder.favorites.setOnClickListener(view -> {
                if (userItemClickListener!=null){
                    userItemClickListener.onFavoritesClick(user);
                }
            });
            if (user.isFavorite()){
                viewHolder.favorites.setImageResource(R.drawable.ic_star_filled);
            }else {
                viewHolder.favorites.setImageResource(R.drawable.ic_star_unfilled);
            }
        }
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public void setItems(List<User> items) {
        final UserDiffCallBack diffCallback = new UserDiffCallBack(this.userList, items);
        final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);

        diffResult.dispatchUpdatesTo(this);
        userList.clear();
        userList.addAll(items);
        diffResult.dispatchUpdatesTo(this);
    }

    static class UserViewModel extends RecyclerView.ViewHolder {

        @BindView(R.id.name)
        TextView Name;

        @BindView(R.id.mobile)
        TextView mobile;

        @BindView(R.id.email)
        TextView email;

        @BindView(R.id.company)
        TextView company;

        @BindView(R.id.favorites)
        ImageView favorites;

        UserViewModel(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }

    public interface UserItemClickListener {
        void onUserItemClick(User user);
        void onFavoritesClick(User user);
    }
}