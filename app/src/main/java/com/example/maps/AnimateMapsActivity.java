package com.example.maps;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

public class AnimateMapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap googleMap;
    private boolean mapReady = false;
    private CameraPosition cameraPosition;
    private MarkerOptions london;
    private MarkerOptions losAngeles;
    private MarkerOptions newYork;
    private CircleOptions londonCircle;
    private CircleOptions losAngelesCircle;
    private CircleOptions newYorkCircle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_template);

        // Create markers
        london = new MarkerOptions().position(Constants.LONDON).title(getString(R.string.london))
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_launcher));
        losAngeles = new MarkerOptions().position(Constants.LA).title(getString(R.string.los_angeles))
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_launcher));
        newYork = new MarkerOptions().position(Constants.NEW_YORK).title(getString(R.string.new_york))
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_launcher));

        // Create circles
        londonCircle = new CircleOptions().center(Constants.LONDON).radius(50000).strokeColor(Color.GREEN);
        losAngelesCircle = new CircleOptions().center(Constants.LA).radius(50000).strokeColor(Color.GREEN);
        newYorkCircle = new CircleOptions().center(Constants.NEW_YORK).radius(50000).strokeColor(Color.GREEN);

        Button button1 = (Button) findViewById(R.id.button1);
        Button button2 = (Button) findViewById(R.id.button2);
        Button button3 = (Button) findViewById(R.id.button3);

        button1.setText(R.string.london);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mapReady) updateCamera(Constants.LONDON);
            }
        });

        button2.setText(R.string.los_angeles);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mapReady) updateCamera(Constants.LA);
            }
        });

        button3.setText(R.string.new_york);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mapReady) updateCamera(Constants.NEW_YORK);
            }
        });

        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map_fragment);
        mapFragment.getMapAsync(this);
    }

    private void updateCamera(LatLng landmark) {
        cameraPosition = CameraPosition.builder().target(landmark).zoom(7).build();
        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition), 5000, null);
    }

    @Override
    public void onMapReady(GoogleMap map) {
        googleMap = map;

        // Add markers
        googleMap.addMarker(london);
        googleMap.addMarker(losAngeles);
        googleMap.addMarker(newYork);

        // Add polylines
        googleMap.addPolyline(new PolylineOptions().geodesic(true)
                .add(Constants.LONDON).add(Constants.NEW_YORK).add(Constants.LA));

        // Add circles
        googleMap.addCircle(londonCircle);
        googleMap.addCircle(losAngelesCircle);
        googleMap.addCircle(newYorkCircle);

        mapReady = true;
        cameraPosition = CameraPosition.builder().target(Constants.LONDON).zoom(7).build();
        googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }
}
