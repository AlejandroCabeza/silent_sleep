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

package com.danialex.androidfinalproject.interfaces;

import com.google.android.gms.maps.model.LatLng;

/**
 * Interface that defines the methods of a Location Repository
 * @author Daniel Sanchez Quirós
 * @author Manuel Alejandro Cabeza Romero
 */
public interface ILocationRepository<T> {

    /**
     * Subscribes an activity to the location repository
     * @param activity Activity to be subscribed
     */
    void   subscribe  (T activity);

    /**
     * Unsubscribes an activity from the location repository
     * @param activity Activity to be unsubscribed
     */
    void   unsubscribe(T activity);

    /**
     * Gets the actual location in GPS coordinates
     * @return Actual location
     */
    LatLng getLocation();

    /**
     * Notifies the location repository that a subscribed activity has started listening
     */
    void   connect    ();

    /**
     * Notifies the location repository that a subscribed activity has stopped listening
     */
    void   disconnect ();
}
