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

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public abstract class StaticMethodsTest {
    private final Class<?> underTest;

    protected StaticMethodsTest(Class<?> underTest) {
        this.underTest = underTest;
    }

    @Test
    public void mainClassHasOneStaticFieldForTimeBombInstance() throws NoSuchFieldException {
        Field staticField = underTest.getField("TIME_BOMB");

        assertTrue(Modifier.isStatic(staticField.getModifiers()));
        assertTrue(Modifier.isPublic(staticField.getModifiers()));
        assertTrue(Modifier.isFinal(staticField.getModifiers()));
        assertEquals(ITimeBomb.class, staticField.getType());
    }

    @Test
    public void mainClassHasOneStaticMethodToMatchEveryInterfaceMethod() throws NoSuchMethodException {
        for (Method method : ITimeBomb.class.getMethods()) {
            Method staticMethod = underTest.getMethod(method.getName(), method.getParameterTypes());
            assertTrue(staticMethod.getName(), Modifier.isStatic(staticMethod.getModifiers()));
            assertTrue(staticMethod.getName(), Modifier.isPublic(staticMethod.getModifiers()));

            assertParameterNamesAreTheSame(method.getParameters(), staticMethod.getParameters());
        }
    }

    private void assertParameterNamesAreTheSame(Parameter[] expectedParameters, Parameter[] actualParameters) {
        for (int i = 0; i < expectedParameters.length; i++) {
            assertEquals(expectedParameters[i].getName(), actualParameters[i].getName());
        }
    }
}
