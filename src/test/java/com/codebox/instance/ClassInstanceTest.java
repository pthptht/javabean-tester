/*
 * JavaBean Tester (https://github.com/hazendaz/javabean-tester)
 *
 * Copyright 2012-2021 Hazendaz.
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

import java.lang.reflect.InvocationTargetException;

import mockit.Expectations;
import mockit.Mocked;
import mockit.Tested;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

/**
 * The Class ClassInstanceTest.
 */
// TODO 1/12/2019 JWL Class is not mockable
@Disabled
public class ClassInstanceTest {

    /** The class instance. */
    @Tested
    ClassInstance<Object> classInstance;

    /** The mock clazz. */
    @Mocked
    Class<Object> mockClazz;

    /**
     * New instance instantiation exception.
     *
     * @throws InstantiationException
     *             the instantiation exception
     * @throws IllegalAccessException
     *             the illegal access exception
     * @throws NoSuchMethodException
     *             the no such method exception
     * @throws InvocationTargetException
     *             the invocation target exception
     */
    @Test
    void newInstanceInstantiationException()
            throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Assertions.assertNotNull(new Expectations() {
            {
                ClassInstanceTest.this.mockClazz.getDeclaredConstructor().newInstance();
                this.result = new InstantiationException();
            }
        });
        this.classInstance.newInstance(this.mockClazz);
    }

    /**
     * New instance illegal access exception.
     *
     * @throws InstantiationException
     *             the instantiation exception
     * @throws IllegalAccessException
     *             the illegal access exception
     * @throws NoSuchMethodException
     *             the no such method exception
     * @throws InvocationTargetException
     *             the invocation target exception
     */
    @Test
    void newInstanceIllegalAccessException()
            throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Assertions.assertNotNull(new Expectations() {
            {
                ClassInstanceTest.this.mockClazz.getDeclaredConstructor().newInstance();
                this.result = new IllegalAccessException();
            }
        });
        this.classInstance.newInstance(this.mockClazz);
    }

}
