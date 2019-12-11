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

package com.danialex.androidfinalproject.activities;

import com.danialex.androidfinalproject.adapters.HotelCardsListAdapter;
import com.danialex.androidfinalproject.interfaces.IOnHotelListRetrievedCallback;
import com.danialex.androidfinalproject.listeners.HotelSwipeRefreshListener;
import com.danialex.androidfinalproject.managers.ModelDependencyManager;
import com.danialex.androidfinalproject.managers.RepositoryDependencyManager;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.danialex.androidfinalproject.R;
import com.danialex.androidfinalproject.models.Hotel;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 *
 * @author Daniel Sanchez Quirós
 * @author Manuel Alejandro Cabeza Romero
 */
public class HotelListActivity extends AppCompatActivity implements IOnHotelListRetrievedCallback {

    @Bind(R.id.activity_hotel_list_swipe_to_refresh)
    SwipeRefreshLayout mSwipeRefreshLayout;

    @Bind(R.id.activity_hotel_list_recycler_view)
    RecyclerView mRecyclerView;

    HotelSwipeRefreshListener mHotelSwipeRefreshListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_list);
        ButterKnife.bind(this);
        mHotelSwipeRefreshListener = null;
        init();
    }

    /**
     * Initializes the application
     */
    private void init() {
        ModelDependencyManager.init(this);
        Toast.makeText(this, R.string.toastRefresh, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        ModelDependencyManager.getHotelManager().getHotels(this);
    }

    @Override
    protected void onRestart() {
        ModelDependencyManager.onStart();
        super.onRestart();
    }

    @Override
    protected void onStop() {
        ModelDependencyManager.onStart();
        super.onStop();
    }


    @Override
    protected void onDestroy() {
        RepositoryDependencyManager.end(this);
        super.onDestroy();
    }

    @Override
    public void onHotelListLoadedCallback(List<Hotel> hotelList) {

        if (hotelList == null) return;

        mRecyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);

        HotelCardsListAdapter adapter = new HotelCardsListAdapter(hotelList);
        mRecyclerView.setAdapter(adapter);
        mHotelSwipeRefreshListener = new HotelSwipeRefreshListener(this, mSwipeRefreshLayout, adapter);
        mSwipeRefreshLayout.setOnRefreshListener(mHotelSwipeRefreshListener);
    }
}
