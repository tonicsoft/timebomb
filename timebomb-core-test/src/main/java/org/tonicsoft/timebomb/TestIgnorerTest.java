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

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public abstract class TestIgnorerTest {
    private final TestIgnorer underTest;
    private final String message = "dnaruuh";
    private final Class<?> ignoringExceptionClass;

    public TestIgnorerTest(TestIgnorer underTest, Class<?> ignoringExceptionClass) {
        this.underTest = underTest;
        this.ignoringExceptionClass = ignoringExceptionClass;
    }

    @Test
    public void shouldThrowExceptionThatCausesCurrentlyRunningTestToBeIgnored() {
        try {
            underTest.ignoreCurrentlyRunningTest(message);
            fail("Should have thrown " + ignoringExceptionClass.getSimpleName());
        } catch (Throwable e) {
            assertEquals(ignoringExceptionClass, e.getClass());
            assertEquals(message, e.getMessage());
        }
    }
}
