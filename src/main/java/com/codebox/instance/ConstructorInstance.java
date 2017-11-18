/**
 * JavaBean Tester (https://github.com/hazendaz/javabean-tester)
 *
 * Copyright (c) 2012 - 2017 Hazendaz.
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

/**
 * The Constructor Instance.
 */
public final class ConstructorInstance {

    /**
     * Prevent Instantiation of a new constructor instance.
     */
    private ConstructorInstance() {
        // Prevent Instantiation
    }

    /**
     * New instance.
     *
     * @param constructor
     *            the instance
     * @return the Object
     */
    public static Object newInstance(final Constructor<?> constructor) {
        try {
            return constructor.newInstance();
        } catch (InstantiationException e) {
            Assert.fail(String.format("An exception was thrown while testing the constructor '%s': '%s'",
                    constructor.getName(), e.toString()));
        } catch (IllegalAccessException e) {
            Assert.fail(String.format("An exception was thrown while testing the constructor '%s': '%s'",
                    constructor.getName(), e.toString()));
        } catch (InvocationTargetException e) {
            Assert.fail(String.format("An exception was thrown while testing the constructor '%s': '%s'",
                    constructor.getName(), e.toString()));
        }
        return null;
    }

    /**
     * Constructor inaccessibility test.
     *
     * @param clazz
     *            the clazz
     */
    public static void inaccessible(final Class<?> clazz) {
        final Constructor<?>[] ctors = clazz.getDeclaredConstructors();
        Assert.assertEquals("Utility class should only have one constructor", 1, ctors.length);
        final Constructor<?> ctor = ctors[0];
        Assert.assertFalse("Utility class constructor should be inaccessible", ctor.isAccessible());
        // Make accessible in order to test following assert.
        ctor.setAccessible(true);
        final Object object = ConstructorInstance.newInstance(ctor); 
        Assert.assertEquals("You would expect the constructor to return the expected type", clazz,
                object == null ? "null" : object.getClass());
    }
}
