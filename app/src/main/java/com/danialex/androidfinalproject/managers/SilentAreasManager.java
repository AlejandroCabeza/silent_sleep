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

package com.danialex.androidfinalproject.managers;

import com.danialex.androidfinalproject.interfaces.IOnSilentAreaRetrievedCallback;
import com.danialex.androidfinalproject.interfaces.IOnSilentAreaInfoRetrievedCallback;
import com.danialex.androidfinalproject.interfaces.ISilentAreasManager;
import com.danialex.androidfinalproject.interfaces.ISilentAreasRepository;
import com.danialex.androidfinalproject.models.SilentArea;
import com.danialex.androidfinalproject.models.SilentAreaInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Provides access to the data in the Silent Area Nodel
 * @author Daniel Sanchez Quirós
 * @author Manuel Alejandro Cabeza Romero
 */
public class SilentAreasManager implements ISilentAreasManager, IOnSilentAreaRetrievedCallback {

    private ISilentAreasRepository mRepo;
    private ArrayList<IOnSilentAreaInfoRetrievedCallback> mCallbacks;

    /**
     * Constructor for SilentAreasManager
     */
    public SilentAreasManager() {
        mRepo = RepositoryDependencyManager.getAreaRepository();
        mCallbacks = new ArrayList<>();
    }

    @Override
    public void getSilentArea(IOnSilentAreaInfoRetrievedCallback callbackInstance) {
        mCallbacks.add(callbackInstance);
        mRepo.getSilentAreas(this);
    }

    @Override
    public void onSilentAreaRetrieve(List<SilentArea> areaInfo) {
        ArrayList<SilentAreaInfo> ret = new ArrayList<>();
        if(areaInfo != null) {
            for (SilentArea area : areaInfo) {
                SilentAreaInfo.NoiseLevel level;
                if (area.getNoiseValues().get(0).getDecibelsAverage() < 55)
                    level = SilentAreaInfo.NoiseLevel.LOW;
                else if (area.getNoiseValues().get(0).getDecibelsAverage() < 65)
                    level = SilentAreaInfo.NoiseLevel.MEDIUM;
                else level = SilentAreaInfo.NoiseLevel.HIGH;
                ret.add(new SilentAreaInfo(area.getLatitude(), area.getLongitude(), 1500, level));
            }
        }

        for(IOnSilentAreaInfoRetrievedCallback callback : mCallbacks)
        {
            callback.onSilentAreaInfoRetrieve(ret);
        }
        mCallbacks.clear();
    }
}
