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

package com.growingio.android.sdk.autotrack.events;

import com.growingio.android.sdk.autotrack.events.base.BasePageEvent;

public class PageEvent extends BasePageEvent {
    private static final long serialVersionUID = 1L;

    protected PageEvent(EventBuilder eventBuilder) {
        super(eventBuilder);
    }

    public static class EventBuilder extends BasePageEvent.EventBuilder<PageEvent> {

        EventBuilder() {
            super();
        }

        @Override
        public PageEvent build() {
            return new PageEvent(this);
        }
    }
}
