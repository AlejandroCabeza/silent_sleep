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

package com.danialex.androidfinalproject.services;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Interface for Retrofit to create an HTTP Client for the Google Places API
 * @author Daniel Sanchez Quirós
 * @author Manuel Alejandro Cabeza Romero
 */
public interface MadridOpenDataService {

    /**
     * Retrieves a Call object containing a ResponseBody with the info about the silent areas in Madrid
     * @return Call Object with ResponseBody
     */
    @GET("/opendata/ruido.txt")
    Call<ResponseBody> getSilentAreasInMadrid();
}
