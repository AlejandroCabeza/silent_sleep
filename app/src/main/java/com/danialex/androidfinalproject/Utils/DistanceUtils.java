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

package com.danialex.androidfinalproject.Utils;

import android.location.Location;

import com.google.android.gms.maps.model.LatLng;

/**
 * Utilities for distance calculation
 * @author Daniel Sanchez Quirós
 * @author Manuel Alejandro Cabeza Romero
 */
public class DistanceUtils {

    /**
     * Calculates distance between two GPS locations
     * @param a Location A
     * @param b Location B
     * @return Distance in metres between A and B
     */
    public static float calculateDistanceBetweenGpsLocations(LatLng a, LatLng b)
    {
        Location aLoc = new Location("A");aLoc.setLatitude(a.latitude);aLoc.setLongitude(a.longitude);
        Location bLoc = new Location("B");bLoc.setLatitude(b.latitude);bLoc.setLongitude(b.longitude);
        return aLoc.distanceTo(bLoc);
    }
}
