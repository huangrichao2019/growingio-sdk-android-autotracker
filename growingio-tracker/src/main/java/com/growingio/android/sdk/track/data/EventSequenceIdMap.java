/*
 * Copyright (C) 2020 Beijing Yishu Technology Co., Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.growingio.android.sdk.track.data;

import java.io.Serializable;
import java.util.HashMap;

public class EventSequenceIdMap implements Serializable {

    private final HashMap<String, Integer> mData = new HashMap<>();

    public EventSequenceIdMap() {
    }

    public int getSequenceId(String type) {
        Integer sid = mData.get(type);
        if (sid == null) {
            return 1;
        } else {
            return sid;
        }
    }

    public EventSequenceIdMap setSequenceId(String type, int sid) {
        mData.put(type, sid);
        return this;
    }
}
