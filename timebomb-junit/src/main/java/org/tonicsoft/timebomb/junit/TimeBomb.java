/*
Copyright 2017 tonicsoft

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/

package org.tonicsoft.timebomb.junit;

import org.tonicsoft.timebomb.ITimeBomb;
import org.tonicsoft.timebomb.TimeBombBase;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;

public class TimeBomb {
    public static final ITimeBomb TIME_BOMB = new TimeBombBase(new JunitTestIgnorer());

    public static void blowUpAfter(LocalDate date) {
        TIME_BOMB.blowUpAfter(date);
    }

    public static void ignoreUntil(LocalDate date) {
        TIME_BOMB.ignoreUntil(date);
    }

    public static void blowUpAfter(ZonedDateTime zonedDateTime) {
        TIME_BOMB.blowUpAfter(zonedDateTime);
    }

    public static void blowUpAfter(long epochMillis) {
        TIME_BOMB.blowUpAfter(epochMillis);
    }

    public static void ignoreUntil(OffsetDateTime offsetDateTime) {
        TIME_BOMB.ignoreUntil(offsetDateTime);
    }

    public static void blowUpAfter(OffsetDateTime offsetDateTime) {
        TIME_BOMB.blowUpAfter(offsetDateTime);
    }

    public static void blowUpAfter(LocalDateTime dateTime) {
        TIME_BOMB.blowUpAfter(dateTime);
    }

    public static void ignoreUntil(ZonedDateTime zonedDateTime) {
        TIME_BOMB.ignoreUntil(zonedDateTime);
    }

    public static void ignoreUntil(long epochMillis) {
        TIME_BOMB.ignoreUntil(epochMillis);
    }

    public static void ignoreUntil(LocalDateTime dateTime) {
        TIME_BOMB.ignoreUntil(dateTime);
    }
}
