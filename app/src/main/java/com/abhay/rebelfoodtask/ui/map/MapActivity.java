package com.abhay.rebelfoodtask.ui.map;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.abhay.rebelfoodtask.R;
import com.abhay.rebelfoodtask.data.model.User;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {

    public static final String USER_DATA_KEY = "user_data";
    private User user;

    @BindView(R.id.address)
    TextView address;

    public static Intent newInstance(Context context) {
        return new Intent(context, MapActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        ButterKnife.bind(this);
        getSupportActionBar().setTitle("Address Location");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getBundleData();
        setMap();
    }

    private void getBundleData() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            user = (User) getIntent().getParcelableExtra(USER_DATA_KEY);
        }
    }

    private void setMap() {
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        if (user != null) {
            String string = String.format("Address : %s ,%s - %s", user.getAddress().getCity(), user.getAddress().getStreet(), user.getAddress().getZipcode());
            address.setText(string);
            double lat = Double.parseDouble(user.getAddress().getGeo().getLat());
            double lng = Double.parseDouble(user.getAddress().getGeo().getLng());
            LatLng latLng = new LatLng(lat, lng);
            googleMap.addMarker(new MarkerOptions().position(latLng)
                    .title(string));
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                 onBackPressed();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
