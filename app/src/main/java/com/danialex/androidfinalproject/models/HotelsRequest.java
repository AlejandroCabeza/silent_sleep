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
 * Stores the data of the request made to the Google Places API on Hotels
 * @author Daniel Sanchez Quirós
 * @author Manuel Alejandro Cabeza Romero
 */
@SuppressWarnings("unused")
public class HotelsRequest {

    @JsonProperty("html_attributions")
    private ArrayList<String> mHtmlAttributions;

    @JsonProperty("next_page_token")
    private String            mNextPageToken;

    @JsonProperty("results")
    private ArrayList<Hotel>  mHotelList;

    @JsonProperty("status")
    private String mStatus;

    public HotelsRequest() { }

    public ArrayList<String> getHtmlAttributions() {
        return mHtmlAttributions;
    }

    public void setHtmlAttributions(ArrayList<String> htmlAttributions) {
        this.mHtmlAttributions = htmlAttributions;
    }

    public String getNextPageToken() {
        return mNextPageToken;
    }

    public void setNextPageToken(String nextPageToken) {
        this.mNextPageToken = nextPageToken;
    }

    public ArrayList<Hotel> getHotelList() {
        return mHotelList;
    }

    public void setHotelList(ArrayList<Hotel> hotelList) {
        this.mHotelList = hotelList;
    }

    public String getStatus() {
        return mStatus;
    }

    public void setStatus(String status) {
        this.mStatus = status;
    }
}
