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

import com.danialex.androidfinalproject.interfaces.ISilentAreasManager;
import com.danialex.androidfinalproject.interfaces.IHotelManager;

/**
 * Provides access to the data models managers
 * @author Daniel Sanchez Quirós
 * @author Manuel Alejandro Cabeza Romero
 */
public class ModelDependencyManager {

    static IHotelManager          mHotelManager = null;
    static ISilentAreasManager    mAreaManager  = null;
    static Activity mActivity = null;

    /**
     * Initializes the managers
     * @param activity Actual activity of the application
     */
    public static void init(Activity activity) {
        mActivity = activity;
        RepositoryDependencyManager.init(activity);
        if(mHotelManager == null) mHotelManager = new HotelManager();
        if(mAreaManager ==  null) mAreaManager  = new SilentAreasManager();
    }

    /**
     * Terminates the managers
     * @param activity Actual activity of the application
     */
    public static void end(Activity activity)
    {
        RepositoryDependencyManager.end(activity);
    }

    /**
     * Starts the managers
     */
    public static void onStart() {
        RepositoryDependencyManager.onStart();
    }

    /**
     * Stops the managers
     */
    public static void onStop() {
        RepositoryDependencyManager.onStop();
    }

    /**
     * Retrieves the Hotel Manager
     * @return Hotel Manager
     */
    public static IHotelManager getHotelManager(){
        if(mHotelManager == null) mHotelManager = new HotelManager();
        return mHotelManager;
    }

    /**
     * Retrieves the Silent Area Manager
     * @return Silent Area Manager
     */
    public static ISilentAreasManager getAreaManager(){
        if(mAreaManager ==  null) mAreaManager = new SilentAreasManager();
        return mAreaManager;
    }
}
