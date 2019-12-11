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
 * Interface that defines the methods of a Hotel Manager
 * @author Daniel Sanchez Quirós
 * @author Manuel Alejandro Cabeza Romero
 */
public interface IHotelManager {

    /**
     * Retrieves the list of hotels and executes the callback method of the Callback Interface
     * @param callbackInstance Instance of the Callback Interface
     */
    void getHotels(IOnHotelListRetrievedCallback callbackInstance);

    /**
     * Gets the ID of the last clicked hotel
     * @return ID of the last clicked hotel
     */
    String getClickedHotelId();

    /**
     * Saves the ID of the last clicked hotel
     * @param clickedHotelId ID of the last clicked hotel
     */
    void setClickedHotelId(String clickedHotelId);
}
