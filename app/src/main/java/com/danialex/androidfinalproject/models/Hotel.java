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

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

/**
 * Represents a hotel in the map
 * Stores information about the hotel
 * @author Daniel Sanchez Quirós
 * @author Manuel Alejandro Cabeza Romero
 */
@SuppressWarnings("unused")
public class Hotel {

    @JsonProperty("geometry")
    private Geometry          mGeometry;

    @JsonProperty("icon")
    private String            mIcon;

    @JsonProperty("id")
    private String            mId;

    @JsonProperty("name")
    private String            mName;

    @JsonProperty("opening_hours")
    private OpeningHours      mOpeningHours;

    @JsonProperty("photos")
    private ArrayList<Photo>  mPhotos;

    @JsonProperty("place_id")
    private String            mPlaceId;

    @JsonProperty("rating")
    private float             mRating;

    @JsonProperty("reference")
    private String            mReference;

    @JsonProperty("scope")
    private String            mScope;

    @JsonProperty("types")
    private ArrayList<String> mTypes;

    @JsonProperty("vicinity")
    private String            mVicinity;

    public Hotel() { }

    public Geometry getGeometry() {
        return mGeometry;
    }

    public void setGeometry(Geometry mGeometry) {
        this.mGeometry = mGeometry;
    }

    public String getIcon() {
        return mIcon;
    }

    public void setIcon(String mIcon) {
        this.mIcon = mIcon;
    }

    public String getId() {
        return mId;
    }

    public void setId(String mId) {
        this.mId = mId;
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public OpeningHours getOpeningHours() {
        return mOpeningHours;
    }

    public void setOpeningHours(OpeningHours mOpeningHours) {
        this.mOpeningHours = mOpeningHours;
    }

    public ArrayList<Photo> getPhotos() {
        return mPhotos;
    }

    public void setPhotos(ArrayList<Photo> mPhotos) {
        this.mPhotos = mPhotos;
    }

    public String getPlaceId() {
        return mPlaceId;
    }

    public void setPlaceId(String mPlaceId) {
        this.mPlaceId = mPlaceId;
    }

    public float getRating() {
        return mRating;
    }

    public void setRating(float mRating) {
        this.mRating = mRating;
    }

    public String getReference() {
        return mReference;
    }

    public void setReference(String mReference) {
        this.mReference = mReference;
    }

    public String getScope() {
        return mScope;
    }

    public void setScope(String mScope) {
        this.mScope = mScope;
    }

    public ArrayList<String> getTypes() {
        return mTypes;
    }

    public void setTypes(ArrayList<String> mTypes) {
        this.mTypes = mTypes;
    }

    public String getVicinity() {
        return mVicinity;
    }

    public void setVicinity(String mVicinity) {
        this.mVicinity = mVicinity;
    }

    @Override
    public String toString() {
        String str = "";

        str += "Geometry: "      + mGeometry + " | ";
        str += "Icon: "          + mIcon+ " | ";
        str += "ID: "            + mId+ " | ";
        str += "Name: "          + mName+ " | ";
        str += "Opening Hours: " + mOpeningHours+ " | ";
        str += "Photos:\n";

        if (mPhotos != null) for (Photo photo : mPhotos) str += photo + " |\n";
        else str += "None | ";

        str += "Place ID: "      + mPlaceId+ " | ";
        str += "Rating: "        + mRating+ " | ";
        str += "Reference: "     + mReference+ " | ";
        str += "Scope: "         + mScope+ " | ";
        str += "Types:\n";

        if (mTypes != null) for (String type : mTypes) str += type + " | ";
        else str += "None | ";

        str += "Vicinity: "      + mVicinity+ " | ";

        return str;
    }

    public static class Geometry {

        @JsonProperty("location")
        private Location mLocation;

        public Geometry() { }

        public Location getLocation() {
            return mLocation;
        }

        public void setLocation(Location location) {
            this.mLocation = location;
        }

        public static class Location {

            @JsonProperty("lat")
            private double mLatitude;

            @JsonProperty("lng")
            private double mLongitude;

            public Location() { }

            public double getLatitude() {
                return mLatitude;
            }

            public double getLongitude() {
                return mLongitude;
            }

            @Override
            public String toString() {
                String str = "";

                str += "Latitude: "  + mLatitude  + " | ";
                str += "Longitude: " + mLongitude;

                return str;
            }
        }

        @Override
        public String toString() {
            String str = "";

            str += "Location " + mLocation;

            return str;
        }
    }

    public static class OpeningHours {

        @JsonProperty("open_now")
        public boolean           mOpenNow;

        @JsonProperty("weekday_text")
        public ArrayList<String> mWeekdayText;

        public OpeningHours() { }

        public boolean isOpenNow() {
            return mOpenNow;
        }

        public void setOpenNow(boolean mOpenNow) {
            this.mOpenNow = mOpenNow;
        }

        public ArrayList<String> getWeekdayText() {
            return mWeekdayText;
        }

        public void setWeekdayText(ArrayList<String> mWeekdayText) {
            this.mWeekdayText = mWeekdayText;
        }

        @Override
        public String toString() {
            String str = "";

            str += "Weekday Text:\n";
            for (String weekdayText : mWeekdayText) str += weekdayText + " | ";
            str += "Open Now: " + mOpenNow;

            return str;
        }
    }

    public static class Photo {

        @JsonProperty("height")
        public float             mHeight;

        @JsonProperty("width")
        public float             mWidth;

        @JsonProperty("html_attributions")
        public ArrayList<String> mHtmlAttributions;

        @JsonProperty("photo_reference")
        public String            mPhotoReference;

        public Photo() { }

        public float getHeight() {
            return mHeight;
        }

        public void setHeight(float mHeight) {
            this.mHeight = mHeight;
        }

        public float getWidth() {
            return mWidth;
        }

        public void setWidth(float mWidth) {
            this.mWidth = mWidth;
        }

        public ArrayList<String> getHtmlAttributions() {
            return mHtmlAttributions;
        }

        public void setHtmlAttributions(ArrayList<String> mHtmlAttributions) {
            this.mHtmlAttributions = mHtmlAttributions;
        }

        public String getPhotoReference() {
            return mPhotoReference;
        }

        public void setPhotoReference(String mPhotoReference) {
            this.mPhotoReference = mPhotoReference;
        }

        @Override
        public String toString() {
            String str = "";

            str += "Height: "            + mHeight + " | ";
            str += "Width: "             + mWidth  + " | ";
            str += "HTML Attributions:\n";
            for (String htmlAttributions : mHtmlAttributions) str += htmlAttributions + " | ";
            str += "Photo Reference: "   + mPhotoReference;

            return str;
        }
    }
}
