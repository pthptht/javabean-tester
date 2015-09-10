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

import org.junit.Assert;

/**
 * The Class Instance.
 *
 * @param <T>
 *            the element type
 */
public class ClassInstance<T> {

    /**
     * New instance.
     *
     * @param clazz
     *            the class
     * @return the t
     */
    public final T newInstance(final Class<T> clazz) {
        try {
            return clazz.newInstance();
        } catch (InstantiationException e) {
            Assert.fail(String.format("An exception was thrown while testing the class %s: %s", clazz.getName(),
                    e.toString()));
        } catch (IllegalAccessException e) {
            Assert.fail(String.format("An exception was thrown while testing the class %s: %s", clazz.getName(),
                    e.toString()));
        }
        return null;
    }

}
