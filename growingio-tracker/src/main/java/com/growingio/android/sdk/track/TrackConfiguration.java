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

package com.growingio.android.sdk.track;

import androidx.annotation.NonNull;

import com.growingio.android.sdk.track.base.Configurable;

public class TrackConfiguration implements Cloneable, Configurable {
    private String mProjectId;
    private String mUrlScheme;
    private String mChannel;
    private boolean mLogEnabled;
    private int mCellularDataLimit;
    private long mDataUploadInterval;
    private long mSessionInterval = 30_000;
    private boolean mDataCollectionEnabled = true;
    private boolean mUploadExceptionEnabled = true;

    public String getProjectId() {
        return mProjectId;
    }

    public TrackConfiguration setProjectId(String projectId) {
        this.mProjectId = projectId;
        return this;
    }

    public boolean isDataCollectionEnabled() {
        return mDataCollectionEnabled;
    }

    public TrackConfiguration setDataCollectionEnabled(boolean dataCollectionEnabled) {
        mDataCollectionEnabled = dataCollectionEnabled;
        return this;
    }

    public String getUrlScheme() {
        return mUrlScheme;
    }

    public TrackConfiguration setUrlScheme(String urlScheme) {
        this.mUrlScheme = urlScheme;
        return this;
    }

    public String getChannel() {
        return mChannel;
    }

    public TrackConfiguration setChannel(String channel) {
        this.mChannel = channel;
        return this;
    }

    public boolean isUploadExceptionEnabled() {
        return mUploadExceptionEnabled;
    }

    public TrackConfiguration setUploadExceptionEnabled(boolean uploadExceptionEnabled) {
        this.mUploadExceptionEnabled = uploadExceptionEnabled;
        return this;
    }

    public boolean isLogEnabled() {
        return mLogEnabled;
    }

    public TrackConfiguration setLogEnabled(boolean enabled) {
        this.mLogEnabled = enabled;
        return this;
    }

    public int getCellularDataLimit() {
        return mCellularDataLimit;
    }

    public TrackConfiguration setCellularDataLimit(int cellularDataLimit) {
        this.mCellularDataLimit = cellularDataLimit;
        return this;
    }

    public long getDataUploadInterval() {
        return mDataUploadInterval;
    }

    public TrackConfiguration setDataUploadInterval(long dataUploadInterval) {
        this.mDataUploadInterval = dataUploadInterval;
        return this;
    }

    public long getSessionInterval() {
        return mSessionInterval;
    }

    public TrackConfiguration setSessionInterval(long sessionInterval) {
        this.mSessionInterval = sessionInterval;
        return this;
    }

    @NonNull
    @Override
    public TrackConfiguration clone() {
        TrackConfiguration clone = new TrackConfiguration();
        clone.mProjectId = this.mProjectId;
        clone.mUrlScheme = this.mUrlScheme;
        clone.mChannel = this.mChannel;
        clone.mLogEnabled = this.mLogEnabled;
        clone.mCellularDataLimit = this.mCellularDataLimit;
        clone.mDataUploadInterval = this.mDataUploadInterval;
        clone.mSessionInterval = this.mSessionInterval;
        clone.mUploadExceptionEnabled = this.mUploadExceptionEnabled;
        return clone;
    }
}
