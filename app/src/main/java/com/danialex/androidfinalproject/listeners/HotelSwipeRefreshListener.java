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

package com.danialex.androidfinalproject.listeners;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.Toast;

import com.danialex.androidfinalproject.adapters.HotelCardsListAdapter;
import com.danialex.androidfinalproject.interfaces.IOnHotelListRetrievedCallback;
import com.danialex.androidfinalproject.managers.ModelDependencyManager;
import com.danialex.androidfinalproject.models.Hotel;

import java.util.List;

/**
 * Swipe Refresh Listener for the Hotel List Recycler View
 * @author Daniel Sanchez Quirós
 * @author Manuel Alejandro Cabeza Romero
 */
public class HotelSwipeRefreshListener implements SwipeRefreshLayout.OnRefreshListener, IOnHotelListRetrievedCallback {

    private Context mContext;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private HotelCardsListAdapter mHotelCardsListAdapter;

    /**
     * Constructor for HotelSwipeRefreshListener
     * @param context Context of the application
     * @param swipeRefreshLayout Swipe Refresh Layout to which this Listener is assigned
     * @param hotelCardsListAdapter Adapter of the Recycler View that this layout is going to update
     */
    public HotelSwipeRefreshListener(Context context, SwipeRefreshLayout swipeRefreshLayout, HotelCardsListAdapter hotelCardsListAdapter) {
        mContext = context;
        mSwipeRefreshLayout = swipeRefreshLayout;
        mHotelCardsListAdapter = hotelCardsListAdapter;
    }

    @Override
    public void onRefresh() {
        refreshItems();
    }

    /**
     * Load and/or update the items to be displayed.
     */
    private void refreshItems() {
        //Load Items

        ModelDependencyManager.getHotelManager().getHotels(this);
    }

    @Override
    public void onHotelListLoadedCallback(List<Hotel> hotelList) {
        mHotelCardsListAdapter.setHotelList(hotelList);
        mHotelCardsListAdapter.notifyDataSetChanged();

        mSwipeRefreshLayout.setRefreshing(false);
        Toast.makeText(mContext, "Hotels Loaded", Toast.LENGTH_SHORT).show();
    }
}
