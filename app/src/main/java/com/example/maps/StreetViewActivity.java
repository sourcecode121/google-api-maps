package com.example.maps;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.maps.OnStreetViewPanoramaReadyCallback;
import com.google.android.gms.maps.StreetViewPanorama;
import com.google.android.gms.maps.StreetViewPanoramaFragment;
import com.google.android.gms.maps.model.StreetViewPanoramaCamera;

/**
 * Created by Anand on 22/11/2016.
 */

public class StreetViewActivity extends AppCompatActivity implements OnStreetViewPanoramaReadyCallback {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_street_view);

        StreetViewPanoramaFragment fragment = (StreetViewPanoramaFragment) getFragmentManager()
                                                .findFragmentById(R.id.street_view_fragment);
        fragment.getStreetViewPanoramaAsync(this);
    }

    @Override
    public void onStreetViewPanoramaReady(StreetViewPanorama streetViewPanorama) {
        streetViewPanorama.setPosition(Constants.GOOGLE);
        StreetViewPanoramaCamera camera = new StreetViewPanoramaCamera.Builder().zoom(10).build();
        streetViewPanorama.animateTo(camera, 5000);
    }
}
