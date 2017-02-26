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

import org.junit.Ignore;
import org.junit.Test;

import java.time.LocalDate;

public class JunitAcceptanceTest {
    private final LocalDate today = LocalDate.now();
    private final LocalDate tomorrow = today.plusDays(1);

    @Test
    public void thisTestShouldBeIgnored() throws Exception {
        TimeBomb.ignoreUntil(tomorrow);
    }

    @Ignore(value = "Obviously this can't be run in a CI build")
    @Test
    public void thisTestShouldFail() {
        TimeBomb.ignoreUntil(today);
    }
}