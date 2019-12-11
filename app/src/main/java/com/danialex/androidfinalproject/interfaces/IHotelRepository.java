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

/**
 * Interface that defines the methods of a Hotel Repository
 * @author Daniel Sanchez Quirós
 * @author Manuel Alejandro Cabeza Romero
 */
public interface IHotelRepository {

    /**
     * Retrieves the Hotel list and executes the callback method of the Callback Interface
     * @param latitude Latitude of the area to search in
     * @param longitude Longitude of the area to search in
     * @param radius Radius of the area to search in
     * @param callbackInstance Instance of the Callback Interface to be executed when it finishes
     */
    void getHotels(double latitude, double longitude, double radius, IOnHotelListRetrievedCallback callbackInstance);
}
