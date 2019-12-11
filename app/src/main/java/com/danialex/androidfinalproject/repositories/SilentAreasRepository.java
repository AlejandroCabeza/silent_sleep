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

import android.content.Context;

import com.danialex.androidfinalproject.R;
import com.danialex.androidfinalproject.interfaces.IOnSilentAreaRetrievedCallback;
import com.danialex.androidfinalproject.interfaces.ISilentAreasRepository;
import com.danialex.androidfinalproject.models.SilentArea;
import com.danialex.androidfinalproject.services.MadridOpenDataService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Repository that retrieves information on SilentAreas in Madrid
 * @author Daniel Sanchez Quirós
 * @author Manuel Alejandro Cabeza Romero
 */
public class SilentAreasRepository implements ISilentAreasRepository {

    /**
     * ArrayList containing a List of SilentArea retrieved in the request
     * As this data doesn't change too often, it's not retrieved every time a get petition is made
     */
    ArrayList<SilentArea> mResult;

    /**
     * Context of the application needed to access the resources folder
     */
    Context mContext;

    /**
     * Constructor of SilentAreasRepository
     * @param context Context of the application
     */
    public SilentAreasRepository(Context context) {
        mResult = null;
        mContext = context;
    }

    @Override
    public void getSilentAreas(IOnSilentAreaRetrievedCallback callbackInstance) {
        if(mResult != null) callbackInstance.onSilentAreaRetrieve(mResult);
        else requestSilentAreas(callbackInstance);
    }

    /**
     * Makes a request for silent areas to the server
     * @param callbackInstance Callback Interface instance for when the request finishes
     * @return ResponseBody with the request result
     */
    private ResponseBody requestSilentAreas(IOnSilentAreaRetrievedCallback callbackInstance)
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.mambiente.munimadrid.es")
                .build();

        MadridOpenDataService service = retrofit.create(MadridOpenDataService.class);

        try
        {
            Call<ResponseBody> call = service.getSilentAreasInMadrid();
            call.enqueue(new SilentAreasEnqueueCallback(callbackInstance));
        }
        catch (Exception e) {
            System.out.println("Error executing request from update silent areas.\n" +
                    "Error trace:\n" +
                    e);
        }

        return null;
    }

    /**
     * Builds the ArrayList of SilentArea joining the information on silent areas from the server and the converter from area IDs to GPS Location
     * @param responseBody ResponseBody of the request
     */
    private void parseSilentAreasResponseBody(ResponseBody responseBody)
    {
        ArrayList<SilentArea> silentAreas   = new ArrayList<>();
        ArrayList<Integer>    insertedAreas = new ArrayList<>();
        try {
            //Retrieve silent areas info
            if (responseBody == null) return;

            String[] dataLines = responseBody.string().split("\n");

            //Read file containing location IDs
            ArrayList<String[]> idsToLocation = new ArrayList<>();

            try(BufferedReader br = new BufferedReader(new InputStreamReader(mContext.getResources().openRawResource(R.raw.silent_areas_id_to_location)))) {
                String line = br.readLine();

                while (line != null) {
                    idsToLocation.add(line.split(" "));
                    line = br.readLine();
                }
            }

            //Create the object instances containing the data
            for (String line : dataLines) {
                String[] items = line.split(", ");

                int id = Integer.parseInt(items[0]);

                if (!insertedAreas.contains(id)) {

                    double latitude  = Double.NaN;
                    double longitude = Double.NaN;

                    for (int i = 0; i < idsToLocation.size(); ++i) {

                        if (Integer.parseInt(idsToLocation.get(i)[0]) == id) {
                            latitude  = Double.parseDouble(idsToLocation.get(i)[1]);
                            longitude = Double.parseDouble(idsToLocation.get(i)[2]);
                            break;
                        }
                    }

                    if (Double.isNaN(latitude) || Double.isNaN(longitude)) {
                        System.out.println("Error retrieving coordinates from silent areas.");
                        return;
                    }

                    silentAreas  .add(new SilentArea(id, latitude, longitude, new ArrayList<SilentArea.NoiseValues>()));
                    insertedAreas.add(id);
                }

                SilentArea.NoiseValues noiseValues = new SilentArea.NoiseValues(SilentArea.Period.getValueFromString(items[4]),
                        Double.parseDouble(items[5]),
                        Double.parseDouble(items[6]),
                        Double.parseDouble(items[7]),
                        Double.parseDouble(items[8]),
                        Double.parseDouble(items[9]),
                        Double.parseDouble(items[10]));

                for (SilentArea silentArea : silentAreas) {
                    if (silentArea.getId() == id) {
                        silentArea.addNoiseValues(noiseValues);
                    }
                }

            }

        } catch (IOException | java.lang.NullPointerException e) {
            System.out.println("Error processing data from silent areas.\n" +
                    "Error trace:\n" +
                    e);
        }
        mResult = silentAreas;
    }

    /**
     * Manages the asynchronous request to Retrofit, proving a callback when the request ends
     */
    private class SilentAreasEnqueueCallback implements Callback<ResponseBody> {

        /**
         * Callback Interface instance
         */
        private IOnSilentAreaRetrievedCallback mCallbackInstance;

        /**
         * Constructor for SilentAreasEnqueueCallback
         * @param callbackInstance Callback Interface instance
         */
        private SilentAreasEnqueueCallback(IOnSilentAreaRetrievedCallback callbackInstance) {
            mCallbackInstance = callbackInstance;
        }

        @Override
        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
            parseSilentAreasResponseBody(response.body());
            mCallbackInstance.onSilentAreaRetrieve(mResult);
        }

        @Override
        public void onFailure(Call<ResponseBody> call, Throwable t) {
            mCallbackInstance.onSilentAreaRetrieve(null);
        }
    }
}
