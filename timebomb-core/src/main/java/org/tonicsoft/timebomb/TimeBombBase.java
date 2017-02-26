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

package org.tonicsoft.timebomb;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;

public class TimeBombBase implements ITimeBomb {
    private final TestIgnorer testIgnorer;

    public TimeBombBase(TestIgnorer testIgnorer) {
        this.testIgnorer = testIgnorer;
    }

    @Override
    public void blowUpAfter(LocalDate date) {
        blowUpIf(LocalDate.now().isAfter(date));
    }

    @Override
    public void blowUpAfter(LocalDateTime dateTime) {
        blowUpIf(LocalDateTime.now().isAfter(dateTime));

    }

    @Override
    public void blowUpAfter(OffsetDateTime offsetDateTime) {
        blowUpIf(OffsetDateTime.now().isAfter(offsetDateTime));
    }

    @Override
    public void blowUpAfter(ZonedDateTime zonedDateTime) {
        blowUpIf(ZonedDateTime.now().isAfter(zonedDateTime));
    }

    @Override
    public void blowUpAfter(long epochMillis) {
        blowUpIf(epochMillis < System.currentTimeMillis());
    }

    private static void blowUp() {
        throw new RuntimeException("TimeBomb has blown up.");
    }

    private void blowUpIf(boolean condition) {
        if (condition) {
            blowUp();
        }
    }

    @Override
    public void ignoreUntil(LocalDate date) {
        ignoreIf(LocalDate.now().isBefore(date), date.toString());
    }

    @Override
    public void ignoreUntil(LocalDateTime dateTime) {
        ignoreIf(LocalDateTime.now().isBefore(dateTime), dateTime.toString());
    }

    @Override
    public void ignoreUntil(OffsetDateTime offsetDateTime) {
        ignoreIf(OffsetDateTime.now().isBefore(offsetDateTime), offsetDateTime.toString());
    }

    @Override
    public void ignoreUntil(ZonedDateTime zonedDateTime) {
        ignoreIf(ZonedDateTime.now().isBefore(zonedDateTime), zonedDateTime.toString());
    }

    @Override
    public void ignoreUntil(long epochMillis) {
        ignoreIf(System.currentTimeMillis() < epochMillis, "" + epochMillis);
    }

    private void ignore(String ignoredUntil) {
        testIgnorer.ignoreCurrentlyRunningTest("Test ignored until " + ignoredUntil + ".");
    }

    private void ignoreIf(boolean condition, String ignoredUntil) {
        if (condition) {
            ignore(ignoredUntil);
        } else {
            blowUp();
        }
    }
}
