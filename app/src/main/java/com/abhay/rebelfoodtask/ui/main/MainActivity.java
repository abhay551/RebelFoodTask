package com.abhay.rebelfoodtask.ui.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.abhay.rebelfoodtask.R;
import com.google.android.material.tabs.TabLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.tab)
    TabLayout tab;

    @BindView(R.id.pager)
    ViewPager pager;

    public static Intent newInstance(Context context) {
        return new Intent(context, MainActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        getSupportActionBar().setTitle("Home");
        setupViewPager(pager);
        tab.setupWithViewPager(pager);
    }

    private void setupViewPager(ViewPager viewPager) {
        FragmentPagerAdapter adapter = new FragmentPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(UsersFragment.newInstance(), "Users");
        adapter.addFragment(FavoritesFragment.newInstance(), "Favorites");
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(2);
    }
}
