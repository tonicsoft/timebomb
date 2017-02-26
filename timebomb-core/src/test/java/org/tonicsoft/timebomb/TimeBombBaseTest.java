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

import org.junit.After;
import org.junit.Test;

import java.time.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class TimeBombBaseTest {
    private final LocalDate today = LocalDate.now();
    private final OffsetDateTime now = OffsetDateTime.now();

    private final LocalDate tomorrow = today.plusDays(1);
    private final LocalDateTime futureTime = tomorrow.atStartOfDay();
    private final OffsetDateTime futureOffsetTime = now.plusDays(1);
    private final ZonedDateTime futureZonedTime = futureOffsetTime.toZonedDateTime();
    private final long futureMillis = futureOffsetTime.toEpochSecond() * 1000;

    private final LocalDate yesterday = today.minusDays(1);
    private final LocalDateTime pastTime = yesterday.atStartOfDay();
    private final OffsetDateTime pastOffsetTime = now.minusDays(1);
    private final ZonedDateTime pastZonedTime = pastOffsetTime.toZonedDateTime();
    private final long pastMillis = pastOffsetTime.toEpochSecond() * 1000;

    private final TestIgnorer testIgnorer = mock(TestIgnorer.class);
    private final TimeBombBase underTest = new TimeBombBase(testIgnorer);

    @After
    public void tearDown() throws Exception {
        verifyNoMoreInteractions(testIgnorer);
    }

    @Test
    public void blowUpAfterDoesNothingForDateInFuture() {
        underTest.blowUpAfter(tomorrow);
    }

    @Test
    public void blowUpAfterBlowsUpForDateInPast() {
        assertTestBlowsUp(() -> underTest.blowUpAfter(yesterday));
    }

    @Test
    public void ignoreUntilCallsIgnorerForDateInFuture() {
        assertTestIgnoredWithMessage(
                tomorrow.toString(),
                () -> underTest.ignoreUntil(tomorrow)
        );
    }

    @Test
    public void ignoreUntilBlowsUpForDateInPast() {
        assertTestBlowsUp(() -> underTest.ignoreUntil(yesterday));
    }

    @Test
    public void blowUpAfterDoesNothingForDateTimeInFuture() {
        underTest.blowUpAfter(futureTime);
    }

    @Test
    public void blowUpAfterBlowsUpForDateTimeInPast() {
        assertTestBlowsUp(() -> underTest.blowUpAfter(pastTime));
    }

    @Test
    public void ignoreUntilCallsIgnorerForDateTimeInFuture() {
        assertTestIgnoredWithMessage(
                futureTime.toString(),
                () -> underTest.ignoreUntil(futureTime)
        );
    }

    @Test
    public void ignoreUntilBlowsUpForDateTimeInPast() {
        assertTestBlowsUp(() -> underTest.ignoreUntil(pastTime));
    }

    @Test
    public void blowUpAfterDoesNothingForOffsetDateTimeInFuture() {
        underTest.blowUpAfter(futureOffsetTime);
    }

    @Test
    public void blowUpAfterBlowsUpForOffsetDateTimeInPast() {
        assertTestBlowsUp(() -> underTest.blowUpAfter(pastOffsetTime));
    }

    @Test
    public void ignoreUntilCallsIgnorerForOffsetDateTimeInFuture() {
        assertTestIgnoredWithMessage(
                futureOffsetTime.toString(),
                () -> underTest.ignoreUntil(futureOffsetTime)
        );
    }

    @Test
    public void ignoreUntilBlowsUpForOffsetDateTimeInPast() {
        assertTestBlowsUp(() -> underTest.ignoreUntil(pastOffsetTime));
    }

    @Test
    public void blowUpAfterDoesNothingForZonedDateTimeInFuture() {
        underTest.blowUpAfter(futureZonedTime);
    }

    @Test
    public void blowUpAfterBlowsUpForOZonedDateTimeInPast() {
        assertTestBlowsUp(() -> underTest.blowUpAfter(pastZonedTime));
    }

    @Test
    public void ignoreUntilCallsIgnorerForZonedDateTimeInFuture() {
        assertTestIgnoredWithMessage(
                futureOffsetTime.toString(),
                () -> underTest.ignoreUntil(futureZonedTime)
        );
    }

    @Test
    public void ignoreUntilBlowsUpForZonedDateTimeInPast() {
        assertTestBlowsUp(() -> underTest.ignoreUntil(pastZonedTime));
    }

    @Test
    public void blowUpAfterDoesNothingForEpochMillisInFuture() {
        underTest.blowUpAfter(futureMillis);
    }

    @Test
    public void blowUpAfterBlowsUpForOEpochMillisInPast() {
        assertTestBlowsUp(() -> underTest.blowUpAfter(pastMillis));
    }

    @Test
    public void ignoreUntilCallsIgnorerForEpochMillisInFuture() {
        assertTestIgnoredWithMessage(
                "" + futureMillis,
                () -> underTest.ignoreUntil(futureMillis)
        );
    }

    @Test
    public void ignoreUntilBlowsUpForEpochMillisInPast() {
        assertTestBlowsUp(() -> underTest.ignoreUntil(pastMillis));
    }

    @Test
    public void blowUpAfterDoesNothingForTodaysDate() {
        underTest.blowUpAfter(today);
    }

    @Test
    public void ignoreUntilBlowsUpForTodaysDate() {
        assertTestBlowsUp(() -> underTest.ignoreUntil(today));
    }

    private void assertTestBlowsUp(Runnable action) {
        try {
            action.run();
            fail("should have thrown " + RuntimeException.class.getSimpleName());
        } catch (RuntimeException e) {
            assertEquals("TimeBomb has blown up.", e.getMessage());
        }
    }

    private void assertTestIgnoredWithMessage(String withMessage, Runnable action) {
        action.run();

        verify(testIgnorer, times(1)).ignoreCurrentlyRunningTest("Test ignored until " + withMessage + ".");
    }
}