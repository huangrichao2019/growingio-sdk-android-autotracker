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

package com.gio.test.three.autotrack;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.gio.test.three.ModuleEntry;
import com.gio.test.three.autotrack.activity.DialogTestActivity;
import com.gio.test.three.autotrack.activity.HideFragmentActivity;
import com.gio.test.three.autotrack.activity.LambdaActivity;
import com.gio.test.three.autotrack.activity.NavFragmentActivity;
import com.gio.test.three.autotrack.activity.NestedFragmentActivity;
import com.gio.test.three.autotrack.activity.RecyclerViewImpActivity;
import com.gio.test.three.autotrack.activity.TabFragmentActivity;
import com.gio.test.three.autotrack.activity.WebViewActivity;
import com.gio.test.three.autotrack.activity.X5WebViewActivity;
import com.growingio.android.sdk.autotrack.GrowingAutotracker;
import com.growingio.android.sdk.autotrack.IgnorePolicy;
import com.growingio.android.sdk.autotrack.util.ViewAttributeUtil;
import com.growingio.android.sdk.track.interfaces.ResultCallback;

import java.util.HashMap;

@ModuleEntry("无埋点SDK测试")
public class AutotrackEntryActivity extends Activity {
    private static final String TAG = "AutotrackEntryActivity";

    private static final String GO_TO_NESTED_FRAGMENT_ACTIVITY = "Go To NestedFragmentActivity";
    private static final String GO_TO_HIDE_FRAGMENT_ACTIVITY = "Go To HideFragmentActivity";
    private static final String GO_TO_NAV_FRAGMENT_ACTIVITY = "Go To NavFragmentActivity";
    private static final String GO_TO_TAB_FRAGMENT_ACTIVITY = "Go To TabFragmentActivity";

    private static final String GO_TO_WEB_VIEW_ACTIVITY = "Go To WebViewActivity";
    private static final String GO_TO_X5_WEB_VIEW_ACTIVITY = "Go To X5WebView Activity";
    private static final String GO_TO_X5_WEB_VIEW_DEBUG_ACTIVITY = "Go To X5WebView Debug Activity";
    private static final String GO_TO_DIALOG_TEST_ACTIVITY = "Go To DialogTestActivity";
    private static final String GO_TO_DIALOG_LAMBDA_ACTIVITY = "Go To LambdaActivity";
    private static final String GO_TO_DIALOG_RECYCLER_VIEW_IMP_ACTIVITY = "Go To RecyclerViewImpActivity";


    private static final String[] ITEMS = {
            GO_TO_NESTED_FRAGMENT_ACTIVITY,
            GO_TO_HIDE_FRAGMENT_ACTIVITY,
            GO_TO_NAV_FRAGMENT_ACTIVITY,
            GO_TO_TAB_FRAGMENT_ACTIVITY,
            GO_TO_WEB_VIEW_ACTIVITY,
            GO_TO_X5_WEB_VIEW_DEBUG_ACTIVITY,
            GO_TO_X5_WEB_VIEW_ACTIVITY,
            GO_TO_DIALOG_TEST_ACTIVITY,
            GO_TO_DIALOG_LAMBDA_ACTIVITY,
            GO_TO_DIALOG_RECYCLER_VIEW_IMP_ACTIVITY,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ignoreExample();

        setContentView(R.layout.activity_autotrck_entry);
        ListView listView = findViewById(R.id.content);
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, ITEMS);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = adapter.getItem(position);
                Log.e(TAG, "onItemClick: " + item);
                handleItemClick(item);
            }
        });
    }

    private void ignoreExample() {
        View view = getWindow().getDecorView();

        GrowingAutotracker.getInstance().ignorePage(this, IgnorePolicy.IGNORE_ALL);
        GrowingAutotracker.getInstance().ignoreView(view, IgnorePolicy.IGNORE_SELF);
    }

    private void autotrackExample() {
        View view = getWindow().getDecorView();
        GrowingAutotracker.getInstance().setUniqueTag(view, "current_unique_tag");
        String uid = ViewAttributeUtil.getCustomId(view);
        Log.d(TAG, "unique id = " + uid);

        GrowingAutotracker.getInstance().setPageAlias(this, "autotrack_entry_alias");
        HashMap<String, String> map = new HashMap<>();
        map.put("name", "hello");
        map.put("age", "18");

        GrowingAutotracker.getInstance().setPageAttributes(this, map);

        GrowingAutotracker.getInstance().getDeviceId(new ResultCallback<String>() {
            @Override
            public void onResult(@Nullable String result) {
                Log.d(TAG, "device id = " + result);
            }
        });

        HashMap<String, String> impAttrs = new HashMap<>();
        impAttrs.put("name", "hello");
        impAttrs.put("age", "18");
    }

    private void handleItemClick(String itemString) {
        autotrackExample();

        switch (itemString) {
            case GO_TO_NESTED_FRAGMENT_ACTIVITY:
                startActivity(new Intent(this, NestedFragmentActivity.class));
                break;
            case GO_TO_HIDE_FRAGMENT_ACTIVITY:
                startActivity(new Intent(this, HideFragmentActivity.class));
                break;
            case GO_TO_NAV_FRAGMENT_ACTIVITY:
                startActivity(new Intent(this, NavFragmentActivity.class));
                break;
            case GO_TO_TAB_FRAGMENT_ACTIVITY:
                startActivity(new Intent(this, TabFragmentActivity.class));
                break;
            case GO_TO_WEB_VIEW_ACTIVITY:
                startActivity(new Intent(this, WebViewActivity.class));
                break;
            case GO_TO_X5_WEB_VIEW_DEBUG_ACTIVITY:
                Intent intent = new Intent(this, X5WebViewActivity.class);
                intent.putExtra("LOAD_URL", "http://debugtbs.qq.com");
                startActivity(intent);
                break;
            case GO_TO_X5_WEB_VIEW_ACTIVITY:
                startActivity(new Intent(this, X5WebViewActivity.class));
                break;
            case GO_TO_DIALOG_TEST_ACTIVITY:
                startActivity(new Intent(this, DialogTestActivity.class));
                break;
            case GO_TO_DIALOG_LAMBDA_ACTIVITY:
                startActivity(new Intent(this, LambdaActivity.class));
                break;
            case GO_TO_DIALOG_RECYCLER_VIEW_IMP_ACTIVITY:
                startActivity(new Intent(this, RecyclerViewImpActivity.class));
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + itemString);
        }
    }
}
