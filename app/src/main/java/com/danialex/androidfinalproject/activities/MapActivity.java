/*
 * Copyright 2015 Danialex.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE­2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.danialex.androidfinalproject.activities;

import android.Manifest;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.danialex.androidfinalproject.R;
import com.danialex.androidfinalproject.interfaces.IOnHotelListRetrievedCallback;
import com.danialex.androidfinalproject.interfaces.IOnSilentAreaInfoRetrievedCallback;
import com.danialex.androidfinalproject.managers.ModelDependencyManager;
import com.danialex.androidfinalproject.models.Hotel;
import com.danialex.androidfinalproject.models.SilentAreaInfo;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Daniel Sanchez Quirós
 * @author Manuel Alejandro Cabeza Romero
 */
public class MapActivity extends AppCompatActivity implements OnMapReadyCallback, IOnSilentAreaInfoRetrievedCallback, IOnHotelListRetrievedCallback {

    GoogleMap mMap;
    FloatingActionButton mLayersButton;
    boolean mShowHideLayers;
    ArrayList<Circle> mLayers;

    static int strokeRed = Color.argb(255, 229,115,115);
    static int fillRed = Color.argb(64, 229,57,53);
    static int strokeRYellow = Color.argb(255, 255,245,157);
    static int fillYellow = Color.argb(64, 255,235,59);
    static int strokeGreen = Color.argb(255, 165,214,167);
    static int fillGreen = Color.argb(64, 76,175,80);


    View.OnClickListener mLayerButtonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (mShowHideLayers) {
                mLayersButton.setImageDrawable(getDrawable(R.drawable.eye_off));
                mShowHideLayers = false;
            } else {
                mLayersButton.setImageDrawable(getDrawable(R.drawable.eye));
                mShowHideLayers = true;
            }
            if (mLayers.size() <= 0) update();
            showHideLayers();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapFragment);
        ModelDependencyManager     .init(this);
        mMap = null;
        mShowHideLayers = true;
        mLayers = new ArrayList<>();
        mLayersButton = (FloatingActionButton) findViewById(R.id.floatingMapButton);
        if (mLayersButton != null) mLayersButton.setOnClickListener(mLayerButtonClickListener);
        mapFragment.getMapAsync(this);
    }

    /**
     * Initializes the activity
     */
    private void init() {
        ModelDependencyManager.getHotelManager().getHotels(this);
        ModelDependencyManager.getAreaManager().getSilentArea(this);
    }

    @Override
    protected void onStart() {
        ModelDependencyManager.onStart();
        super.onStart();
    }

    @Override
    protected void onStop() {
        ModelDependencyManager.onStop();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        ModelDependencyManager.end(this);
        super.onDestroy();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        int permission = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
        if (permission == android.content.pm.PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
        }
        init();
    }

    @Override
    protected void onRestart() {
        ModelDependencyManager.onStart();
        super.onRestart();
    }


    /**
     * Create a circle for the map
     * @param strokeColor Colour of the circle border
     * @param fillColor Colour of the circle interior
     * @param radius Radius of the circle
     * @return CircleOptions containing the circle
     */
    private static CircleOptions getCircle(int strokeColor, int fillColor, int radius) {
        return new CircleOptions()
                .radius(radius)
                .strokeColor(strokeColor)
                .fillColor(fillColor);
    }

    /**
     * Creates a marker for the map
     * @param title Title of the marker
     * @param lat Latitude location of the marker
     * @param lang Longitude location of the marker
     * @param selected If the marker is selected or not
     * @return MarkerOptions containing the marker
     */
    private static MarkerOptions getMarker(String title, String text, double lat, double lang, boolean selected) {
        return new MarkerOptions()
                .title(title)
                .snippet(text)
                .position(new LatLng(lat, lang))
                .icon(BitmapDescriptorFactory.defaultMarker(
                        selected ? BitmapDescriptorFactory.HUE_CYAN : BitmapDescriptorFactory.HUE_VIOLET));
    }

    /**
     * Renders the Markers of the hotels in the map
     * @param hotelList List of hotels to be rendered
     */
    private void renderHotels(List<Hotel> hotelList) {
        if (mMap != null) {
            Marker selectedMarker = null;
            for (int i = 0; i < hotelList.size(); ++i) {
                Hotel h = hotelList.get(i);
                boolean selected = h.getId().equals(ModelDependencyManager.getHotelManager().getClickedHotelId());

                MarkerOptions markerOptions = getMarker(h.getName(),
                        h.getVicinity(),
                        h.getGeometry().getLocation().getLatitude(),
                        h.getGeometry().getLocation().getLongitude(),
                        selected);
                Marker marker = mMap.addMarker(markerOptions);
                if(selected)selectedMarker = marker;
                if (selected) marker.showInfoWindow();
            }
            if(selectedMarker != null) mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(selectedMarker.getPosition(), 10));
        }
    }

    /**
     * Renders the Areas of Noise in the map
     * @param areas List of areas to be rendered
     */
    private void renderAreas(List<SilentAreaInfo> areas) {
        if(mMap != null) {
            for (SilentAreaInfo area : areas) {
                int strokeColor = strokeRYellow;
                int fillColor = fillYellow;
                switch (area.getNoiseLevel()) {
                    case LOW:
                        strokeColor = strokeGreen;
                        fillColor = fillGreen;
                        break;
                    case MEDIUM:
                        strokeColor = strokeRYellow;
                        fillColor = fillYellow;
                        break;
                    case HIGH:
                        strokeColor = strokeRed;
                        fillColor = fillRed;
                        break;
                }
                CircleOptions options = getCircle(strokeColor, fillColor, area.getRadius())
                        .center(new LatLng(area.getLatitude(), area.getLongitude()));
                Circle circle = mMap.addCircle(options);
                mLayers.add(circle);
            }
        }
    }

    /**
     * Toggles between show or hide the noise areas
     */
    private void showHideLayers() {
        for (Circle c : mLayers) c.setVisible(mShowHideLayers);
    }

    @Override
    public void onSilentAreaInfoRetrieve(List<SilentAreaInfo> areaInfo) {
        renderAreas(areaInfo);
    }

    /**
     * Updates the list of silent areas and hotels
     */
    private void update()
    {
        ModelDependencyManager.getAreaManager().getSilentArea(this);
        ModelDependencyManager.getHotelManager().getHotels(this);
    }

    @Override
    public void onHotelListLoadedCallback(List<Hotel> hotelList) {
        renderHotels(hotelList);
    }
}
