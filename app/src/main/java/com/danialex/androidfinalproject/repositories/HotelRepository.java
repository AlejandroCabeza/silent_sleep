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

package com.danialex.androidfinalproject.repositories;

import com.danialex.androidfinalproject.interfaces.IHotelRepository;
import com.danialex.androidfinalproject.interfaces.IOnHotelListRetrievedCallback;
import com.danialex.androidfinalproject.models.HotelsRequest;
import com.danialex.androidfinalproject.services.GooglePlacesService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Repository that retrieves information on Hotels
 * @author Daniel Sanchez Quirós
 * @author Manuel Alejandro Cabeza Romero
 */
public class HotelRepository implements IHotelRepository{

    @Override
    public void getHotels(double latitude, double longitude, double radius, IOnHotelListRetrievedCallback callbackInstance) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://maps.googleapis.com")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();

        GooglePlacesService service = retrofit.create(GooglePlacesService.class);

        try {
            Call<HotelsRequest> hotelRequest = service.getHotelsInMadrid(latitude + "," + longitude, radius);
            hotelRequest.enqueue(new HotelEnqueueCallback(callbackInstance));
        }
        catch (Exception e) {
            System.out.println("Error retrieving request from get hotels.\n" +
                    "Error trace:\n" +
                    e);
        }
    }

    /**
     * Manages the asynchronous request to Retrofit, proving a callback when the request ends
     */
    private class HotelEnqueueCallback implements Callback<HotelsRequest> {

        /**
         * Callback Interface instance
         */
        private IOnHotelListRetrievedCallback mCallbackInstance;

        /**
         * Constructor for SilentAreasEnqueueCallback
         * @param callbackInstance Callback Interface instance
         */
        public HotelEnqueueCallback(IOnHotelListRetrievedCallback callbackInstance) {
            mCallbackInstance = callbackInstance;
        }

        @Override
        public void onResponse(Call<HotelsRequest> call, Response<HotelsRequest> response) {
            mCallbackInstance.onHotelListLoadedCallback(response.body().getHotelList());
        }

        @Override
        public void onFailure(Call<HotelsRequest> call, Throwable t) {
            mCallbackInstance.onHotelListLoadedCallback(null);
        }
    }
}
