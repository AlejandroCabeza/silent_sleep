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

import com.danialex.androidfinalproject.models.SilentArea;

import java.util.List;

/**
 * Callback Interface for classes that want to retrieve silent areas
 * @author Daniel Sanchez Quirós
 * @author Manuel Alejandro Cabeza Romero
 */
public interface IOnSilentAreaRetrievedCallback {

    /**
     * Called when the silent area request is completed
     * @param silentAreas List of silent areas retrieved
     */
    void onSilentAreaRetrieve(List<SilentArea> silentAreas);
}
