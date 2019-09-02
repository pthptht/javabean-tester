/**
 * JavaBean Tester (https://github.com/hazendaz/javabean-tester)
 *
 * Copyright 2012-2019 Hazendaz.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of The Apache Software License,
 * Version 2.0 which accompanies this distribution, and is available at
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 *
 * Contributors:
 *     CodeBox (Rob Dawson).
 *     Hazendaz (Jeremy Landis).
 */
package com.codebox.instance;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import mockit.Expectations;
import mockit.Mocked;
import mockit.Tested;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

/**
 * The Class ConstructorInstanceTest.
 */
// TODO 1/12/2019 JWL Class is not mockable
@Disabled
public class ConstructorInstanceTest {

    /** The constructor instance. */
    @Tested
    ConstructorInstance constructorInstance;

    /**
     * New instance instantiation exception.
     *
     * @param mockConstructor
     *            the mock constructor
     * @throws InstantiationException
     *             the instantiation exception
     * @throws IllegalAccessException
     *             the illegal access exception
     * @throws InvocationTargetException
     *             the invocation target exception
     */
    @Test
    void newInstanceInstantiationException(@Mocked final Constructor<?> mockConstructor)
            throws InstantiationException, IllegalAccessException, InvocationTargetException {
        Assertions.assertNotNull(new Expectations() {
            {
                mockConstructor.newInstance();
                this.result = new InstantiationException();
            }
        });

        Assertions.assertThrows(InstantiationException.class, () -> {
            ConstructorInstance.newInstance(mockConstructor);
        });
    }

    /**
     * New instance illegal access exception.
     *
     * @param mockConstructor
     *            the mock constructor
     * @throws InstantiationException
     *             the instantiation exception
     * @throws IllegalAccessException
     *             the illegal access exception
     * @throws InvocationTargetException
     *             the invocation target exception
     */
    @Test
    void newInstanceIllegalAccessException(@Mocked final Constructor<?> mockConstructor)
            throws InstantiationException, IllegalAccessException, InvocationTargetException {
        Assertions.assertNotNull(new Expectations() {
            {
                mockConstructor.newInstance();
                this.result = new IllegalAccessException();
            }
        });

        Assertions.assertThrows(IllegalAccessException.class, () -> {
            ConstructorInstance.newInstance(mockConstructor);
        });
    }

    /**
     * New instance invocation target exception.
     *
     * @param mockConstructor
     *            the mock constructor
     * @throws InstantiationException
     *             the instantiation exception
     * @throws IllegalAccessException
     *             the illegal access exception
     * @throws InvocationTargetException
     *             the invocation target exception
     */
    @Test
    void newInstanceInvocationTargetException(@Mocked final Constructor<?> mockConstructor)
            throws InstantiationException, IllegalAccessException, InvocationTargetException {
        Assertions.assertNotNull(new Expectations() {
            {
                mockConstructor.newInstance();
                this.result = new InvocationTargetException(this.withInstanceOf(Exception.class));
            }
        });

        Assertions.assertThrows(InvocationTargetException.class, () -> {
            ConstructorInstance.newInstance(mockConstructor);
        });
    }

}
