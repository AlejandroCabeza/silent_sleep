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

import java.util.ArrayList;

/**
 * Represents a noise measurement station in the map
 * Stores info about the noise values in that map zone
 * @author Daniel Sanchez Quirós
 * @author Manuel Alejandro Cabeza Romero
 */
@SuppressWarnings("unused")
public class SilentArea {

    /**
     * Id that identifies the map zone
     */
    private int                    mId;

    /**
     * Latitude of the map zone center
     */
    private double                 mLatitude;

    /**
     * Longitude of the map zone center
     */
    private double                 mLongitude;

    /**
     * Noise values in that map zone
     */
    private ArrayList<NoiseValues> mNoiseValues;

    /**
     * Constructor of SilentArea
     * @param id ID that identifies the map zone
     * @param latitude Latitude of the map zone center
     * @param longitude Longitude of the map zone center
     * @param noiseValues Noise values in that map zone
     */
    public SilentArea(int id, double latitude, double longitude, ArrayList<NoiseValues> noiseValues) {
        mId          = id;
        mLatitude    = latitude;
        mLongitude   = longitude;
        mNoiseValues = noiseValues;
    }

    public int getId() {
        return mId;
    }

    public double getLatitude() {
        return mLatitude;
    }

    public double getLongitude() {
        return mLongitude;
    }

    public ArrayList<NoiseValues> getNoiseValues() {
        return mNoiseValues;
    }

    /**
     * Adds a NoiseValues object to the list
     * @param noiseValues Object to add
     */
    public void addNoiseValues(NoiseValues noiseValues) {
        mNoiseValues.add(noiseValues);
    }

    @Override
    public String toString() {
        String str = "";
        str += "ID: "            + mId          + " | ";
        str += "Latitude: "      + mLatitude    + " | ";
        str += "Longitude: "     + mLongitude   + " | ";
        str += "Noise Values:\n";

        for (NoiseValues noiseValues : mNoiseValues) {
            str += noiseValues + "\n";
        }

        return str;
    }

    /**
     * Period of the day of the different
     */
    public enum Period {
        Total,
        Day,
        Evening,
        Night;

        /**
         * Translates the string values that identify the day period when the noise information was gotten
         * @param string String value to translate
         * @return Equivalent enum value
         */
        public static Period getValueFromString(String string) {
            switch (string)
            {
                case "T":
                    return Total;
                case "D":
                    return Day;
                case "E":
                    return Evening;
                case "N":
                    return Night;
                default:
                    return null;
            }
        }
    }

    /**
     * Stores information about the different levels of noise found during a concrete day period
     */
    public static class NoiseValues {

        private Period mPeriod;
        private double mDecibelsAverage;
        private double mDecibels01;
        private double mDecibels10;
        private double mDecibels50;
        private double mDecibels90;
        private double mDecibels99;

        /**
         * Constructor of NoiseValues
         * @param mPeriod Period of the day when the values were recorded
         * @param mDecibelsAverage Average of the decibels found during a long time period
         * @param mDecibels01 Sound pressure exceeded during 1%  of recording time
         * @param mDecibels10 Sound pressure exceeded during 10% of recording time
         * @param mDecibels50 Sound pressure exceeded during 50% of recording time
         * @param mDecibels90 Sound pressure exceeded during 90% of recording time
         * @param mDecibels99 Sound pressure exceeded during 99% of recording time
         */
        public NoiseValues(Period mPeriod, double mDecibelsAverage, double mDecibels01, double mDecibels10, double mDecibels50, double mDecibels90, double mDecibels99) {
            this.mPeriod          = mPeriod;
            this.mDecibelsAverage = mDecibelsAverage;
            this.mDecibels01      = mDecibels01;
            this.mDecibels10      = mDecibels10;
            this.mDecibels50      = mDecibels50;
            this.mDecibels90      = mDecibels90;
            this.mDecibels99      = mDecibels99;
        }

        public Period getPeriod() {
            return mPeriod;
        }

        public double getDecibelsAverage() {
            return mDecibelsAverage;
        }

        public double getDecibels01() {
            return mDecibels01;
        }

        public double getDecibels10() {
            return mDecibels10;
        }

        public double getDecibels50() {
            return mDecibels50;
        }

        public double getDecibels90() {
            return mDecibels90;
        }

        public double getDecibels99() {
            return mDecibels99;
        }

        @Override
        public String toString() {
            String str = "";

            str += "Period: "  + mPeriod          + " | ";
            str += "Average: " + mDecibelsAverage + " | ";
            str += "01: "      + mDecibels01      + " | ";
            str += "10: "      + mDecibels10      + " | ";
            str += "50: "      + mDecibels50      + " | ";
            str += "90: "      + mDecibels90      + " | ";
            str += "99: "      + mDecibels99;

            return str;
        }
    }
}
