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

package com.danialex.androidfinalproject.managers;

import com.danialex.androidfinalproject.interfaces.IHotelManager;
import com.danialex.androidfinalproject.interfaces.IOnHotelListRetrievedCallback;
import com.google.android.gms.maps.model.LatLng;

/**
 * Provides access to the data in the Hotel Model
 * @author Daniel Sanchez Quirós
 * @author Manuel Alejandro Cabeza Romero
 */
public class HotelManager implements IHotelManager {

    String mClickedHotel;

    /**
     * Constructor for HotelManager
     */
    public HotelManager() {
        mClickedHotel = null;
    }

    @Override
    public String getClickedHotelId() {
        return mClickedHotel;
    }

    @Override
    public void setClickedHotelId(String clickedHotelId) {
        mClickedHotel = clickedHotelId;
    }

    @Override
    public void getHotels(IOnHotelListRetrievedCallback callbackInstance) {
        LatLng latLng = RepositoryDependencyManager.getLocationRepository().getLocation();
        if(latLng == null) latLng = new LatLng(40.416728, -3.703492);
        RepositoryDependencyManager.getHotelRepository().getHotels(latLng.latitude, latLng.longitude, 200000, callbackInstance);
    }
}
