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

import android.app.Activity;
import android.content.Context;

import com.danialex.androidfinalproject.interfaces.IHotelRepository;
import com.danialex.androidfinalproject.interfaces.ILocationRepository;
import com.danialex.androidfinalproject.interfaces.ISilentAreasRepository;
import com.danialex.androidfinalproject.repositories.HotelRepository;
import com.danialex.androidfinalproject.repositories.GoogleLocationRepository;
import com.danialex.androidfinalproject.repositories.SilentAreasRepository;

/**
 * provides access to the data repositories managers
 * @author Daniel Sanchez Quirós
 * @author Manuel Alejandro Cabeza Romero
 */
@SuppressWarnings("unchecked")
public final class RepositoryDependencyManager {
    static IHotelRepository mHotelRepo       = null;
    static ISilentAreasRepository mAreaRepo  = null;
    static ILocationRepository mLocationRepo = null;
    static Context mContext = null;

    /**
     * Initializes the managers
     * @param activity Actual activity of the application
     */
    public static void init(Activity activity) {
        mContext = activity;
        if(mLocationRepo == null) mLocationRepo = new GoogleLocationRepository();
        mLocationRepo.subscribe(activity);
        if(mHotelRepo    == null) mHotelRepo    = new HotelRepository         ();
        if(mAreaRepo     == null) mAreaRepo     = new SilentAreasRepository   (mContext);
    }

    /**
     * Terminates the managers
     * @param activity Actual activity of the application
     */
    public static void end(Activity activity)
    {
        mLocationRepo.unsubscribe(activity);
    }

    /**
     * Retrieves the Hotel Repostitory
     * @return Hotel Repository
     */
    public static IHotelRepository getHotelRepository() {
        if(mHotelRepo == null) mHotelRepo = new HotelRepository();
        return mHotelRepo;
    }

    /**
     * Retrieves the Silent Area Repository
     * @return Silent Area Repository
     */
    public static ISilentAreasRepository getAreaRepository(){
        if(mAreaRepo == null) mAreaRepo = new SilentAreasRepository(mContext);
        return mAreaRepo;
    }

    /**
     * Retrieves the Location Repository
     * @return Location Repository
     */
    public static ILocationRepository getLocationRepository() {
        if(mLocationRepo == null) mLocationRepo = new GoogleLocationRepository();
        return mLocationRepo;
    }

    /**
     * Starts the managers
     */
    public static void onStart() {
        getLocationRepository().connect();
    }

    /**
     * Stops the managers
     */
    public static void onStop() {
        getLocationRepository().disconnect();
    }
}
