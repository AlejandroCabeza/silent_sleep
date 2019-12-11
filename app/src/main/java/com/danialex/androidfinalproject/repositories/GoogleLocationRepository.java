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

package com.danialex.androidfinalproject.repositories;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;

import com.danialex.androidfinalproject.interfaces.ILocationRepository;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

/**
 * Repository that retrieves information on Google Location API
 * @author Daniel Sanchez Quirós
 * @author Manuel Alejandro Cabeza Romero
 */
public class GoogleLocationRepository implements
        ILocationRepository<Activity>,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {

    /**
     * List of activities subscribed to the API information
     */
    ArrayList<Activity>     mSubscribers;

    /**
     * Google API Client
     */
    GoogleApiClient         mApiClient;

    /**
     * Last location the GPS was able to retrieve
     */
    LatLng                  mLastLocation;

    /**
     * Number of activities that are currently connected to the API information
     */
    int                     mActiveActivities;

    /**
     * Constructor for GoogleLocationRepository
     */
    public GoogleLocationRepository() {
        mSubscribers    = new ArrayList<>();
        mApiClient      = null;
        mLastLocation   = null;
        mActiveActivities = 0;
    }

    /**
     * Initializes the Google Api Client with the context of the application
     * @param context Context of the application
     */
    private void initApiClient(Context context)
    {
        mApiClient = new GoogleApiClient.Builder(context)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mApiClient.connect();
    }

    /**
     * Requests the location to the API Client and if found, sets it in the variable for further use
     */
    private void updateLocation()
    {
        ContextCompat.checkSelfPermission(mSubscribers.get(0), Manifest.permission.ACCESS_FINE_LOCATION);
        Location lastLocation = LocationServices.FusedLocationApi.getLastLocation(mApiClient);
        if (lastLocation != null) {
            mLastLocation = new LatLng(lastLocation.getLatitude(), lastLocation.getLongitude());
        }
    }

    @Override
    public void subscribe(Activity activity) {
        mSubscribers.add(activity);
        if(mApiClient == null) initApiClient(activity);
    }

    @Override
    public void unsubscribe(Activity activity) {
        mSubscribers.remove(activity);
    }

    @Override
    public LatLng getLocation() {
        if(mLastLocation == null) updateLocation();
        return mLastLocation;
    }

    @Override
    public void connect() {
        if(mApiClient != null)
        {
            mActiveActivities++;
            if(mActiveActivities > 0 && !mApiClient.isConnected()) mApiClient.connect();
        }
    }

    @Override
    public void disconnect() {
        if(mApiClient != null)
        {
            mActiveActivities--;
            if(mActiveActivities == 0 && mApiClient.isConnected()) mApiClient.disconnect();
        }
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        updateLocation();
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
