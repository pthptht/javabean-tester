/**
 * JavaBean Tester (https://github.com/hazendaz/javabean-tester)
 *
 * Copyright (c) 2012 - 2015 Hazendaz.
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

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import mockit.Expectations;
import mockit.Mocked;
import mockit.Tested;

/**
 * The Class ConstructorInstanceTest.
 */
public class ConstructorInstanceTest {

    /** The constructor instance. */
    @Tested
    ConstructorInstance constructorInstance;

    /** The mock constructor. */
    @Mocked
    Constructor<?>      mockConstructor;

    /**
     * Inits the.
     */
    @Before
    public void init() {
        this.constructorInstance = new ConstructorInstance();
    }

    /**
     * New instance instantiation exception.
     *
     * @throws InstantiationException
     *             the instantiation exception
     * @throws IllegalAccessException
     *             the illegal access exception
     * @throws InvocationTargetException
     *             the invocation target exception
     */
    @Test(expected = AssertionError.class)
    public void newInstanceInstantiationException() throws InstantiationException, IllegalAccessException,
            InvocationTargetException {
        Assert.assertNotNull(new Expectations() {
            {
                ConstructorInstanceTest.this.mockConstructor.newInstance();
                this.result = new InstantiationException();
            }
        });
        ConstructorInstance.newInstance(this.mockConstructor);
    }

    /**
     * New instance illegal access exception.
     *
     * @throws InstantiationException
     *             the instantiation exception
     * @throws IllegalAccessException
     *             the illegal access exception
     * @throws InvocationTargetException
     *             the invocation target exception
     */
    @Test(expected = AssertionError.class)
    public void newInstanceIllegalAccessException() throws InstantiationException, IllegalAccessException,
            InvocationTargetException {
        Assert.assertNotNull(new Expectations() {
            {
                ConstructorInstanceTest.this.mockConstructor.newInstance();
                this.result = new IllegalAccessException();
            }
        });
        ConstructorInstance.newInstance(this.mockConstructor);
    }

    /**
     * New instance invocation target exception.
     *
     * @throws InstantiationException
     *             the instantiation exception
     * @throws IllegalAccessException
     *             the illegal access exception
     * @throws InvocationTargetException
     *             the invocation target exception
     */
    @Test(expected = AssertionError.class)
    public void newInstanceInvocationTargetException() throws InstantiationException, IllegalAccessException,
            InvocationTargetException {
        Assert.assertNotNull(new Expectations() {
            {
                ConstructorInstanceTest.this.mockConstructor.newInstance();
                this.result = new InvocationTargetException(this.withInstanceOf(Exception.class));
            }
        });
        ConstructorInstance.newInstance(this.mockConstructor);
    }

}
