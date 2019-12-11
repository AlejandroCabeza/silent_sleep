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

package com.danialex.androidfinalproject.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.danialex.androidfinalproject.R;
import com.danialex.androidfinalproject.Utils.DistanceUtils;
import com.danialex.androidfinalproject.holders.HotelCardViewHolder;
import com.danialex.androidfinalproject.managers.RepositoryDependencyManager;
import com.danialex.androidfinalproject.models.Hotel;
import com.google.android.gms.maps.model.LatLng;

import java.util.List;

/**
 * @author Daniel Sanchez Quirós
 * @author Manuel Alejandro Cabeza Romero
 */
public class HotelCardsListAdapter extends RecyclerView.Adapter<HotelCardViewHolder> {

    List<Hotel> mHotelList = null;

    /**
     * Constructor of the HotelCardsListAdapter
     * @param hotelList List of Hotels to be shown
     */
    public HotelCardsListAdapter(List<Hotel> hotelList) {
        mHotelList = hotelList;
    }

    @Override
    public HotelCardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_hotel_item, parent, false);
        return new HotelCardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HotelCardViewHolder holder, int position) {
        holder.setId          (mHotelList.get(position).getId());
        holder.setNameText    (mHotelList.get(position).getName());
        holder.setImage       (mHotelList.get(position).getPhotos());
        holder.setRatingText  (mHotelList.get(position).getRating());

        Hotel.Geometry.Location location = mHotelList.get(position).getGeometry().getLocation();

        LatLng latLngHotel = new LatLng(location.getLatitude(), location.getLongitude());
        LatLng actualLocation = RepositoryDependencyManager.getLocationRepository().getLocation();

        holder.setDistanceText(DistanceUtils.calculateDistanceBetweenGpsLocations(latLngHotel, actualLocation));
    }

    @Override
    public int getItemCount() {
        return mHotelList.size();
    }

    /**
     * Sets the Hotel List of the Adapter
     * @param mHotelList New hotel list to be shown
     */
    public void setHotelList(List<Hotel> mHotelList) {
        this.mHotelList = mHotelList;
    }
}
