package com.example.maps;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap googleMap;
    private boolean mapReady = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_template);

        Button button1 = (Button) findViewById(R.id.button1);
        Button button2 = (Button) findViewById(R.id.button2);
        Button button3 = (Button) findViewById(R.id.button3);
        Button streetView = (Button) findViewById(R.id.street_view_button);
        Button animateMaps = (Button) findViewById(R.id.animate_maps_button);

        button1.setText(R.string.map);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mapReady) googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            }
        });

        button2.setText(R.string.satellite);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mapReady) googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
            }
        });

        button3.setText(R.string.hybrid);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mapReady) googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
            }
        });

        streetView.setVisibility(View.VISIBLE);
        streetView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, StreetViewActivity.class));
            }
        });

        animateMaps.setVisibility(View.VISIBLE);
        animateMaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, AnimateMapsActivity.class));
            }
        });

        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map_fragment);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        mapReady = true;
        CameraPosition cameraPosition = CameraPosition.builder()
                                                    .target(Constants.GOOGLE)
                                                    .zoom(17)
                                                    .build();
        this.googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }
}
