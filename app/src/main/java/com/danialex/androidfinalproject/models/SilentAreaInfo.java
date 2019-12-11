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

package com.danialex.androidfinalproject.models;

/**
 * Stores information of the noise in a GPS area
 * @author Daniel Sanchez Quirós
 * @author Manuel Alejandro Cabeza Romero
 */
public class SilentAreaInfo
{
    /**
     * Levels of noise that can be in an area
     */
    public enum NoiseLevel
    {
        LOW,
        MEDIUM,
        HIGH
    }

    /**
     * Latitude of the area center
     */
    double     mLatitude;

    /**
     * Longitude of the area center
     */
    double     mLongitude;

    /**
     * Radius of the area
     */
    int        mRadius;

    /**
     * Noise level found in the area
     */
    NoiseLevel mNoiseLevel;

    /**
     * Constructor of SilentAreaInfo
     * @param latitude Latitude of the area center
     * @param longitude Longitude of the area center
     * @param radius Radius of the area
     * @param noiseLevel Noise level found in the area
     */
    public SilentAreaInfo(double latitude, double longitude, int radius, NoiseLevel noiseLevel) {
        mLatitude   = latitude;
        mLongitude  = longitude;
        mRadius     = radius;
        mNoiseLevel = noiseLevel;
    }


    public double getLatitude() {
        return mLatitude;
    }

    public double getLongitude() {
        return mLongitude;
    }

    public int getRadius() {
        return mRadius;
    }

    public NoiseLevel getNoiseLevel() {
        return mNoiseLevel;
    }
}