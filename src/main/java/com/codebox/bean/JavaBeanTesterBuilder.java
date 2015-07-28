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
package com.codebox.bean;

import com.codebox.enums.CanEquals;
import com.codebox.enums.LoadData;

import java.beans.IntrospectionException;

/**
 * The Class JavaBeanTesterBuilder.
 *
 * @param <T>
 *            the generic type
 * @param <E>
 *            the element type
 */
public class JavaBeanTesterBuilder<T, E> {

    /** The worker. */
    private JavaBeanTesterWorker<T, E> worker;

    /**
     * Instantiates a new java bean tester builder.
     *
     * @param clazz
     *            the clazz
     */
    JavaBeanTesterBuilder(Class<T> clazz) {
        worker = new JavaBeanTesterWorker<T, E>(clazz);
    }

    /**
     * Instantiates a new java bean tester builder.
     *
     * @param clazz
     *            the clazz
     * @param extension
     *            the extension
     */
    JavaBeanTesterBuilder(Class<T> clazz, Class<E> extension) {
        worker = new JavaBeanTesterWorker<T, E>(clazz, extension);
    }

    /**
     * Check equals.
     *
     * @return the java bean tester builder
     */
    public JavaBeanTesterBuilder<T, E> checkEquals() {
        return checkEquals(true);
    }

    /**
     * Check equals.
     *
     * @param value
     *            the value
     * @return the java bean tester builder
     */
    public JavaBeanTesterBuilder<T, E> checkEquals(boolean value) {
        worker.setCheckEquals(value ? CanEquals.ON : CanEquals.OFF);
        return this;
    }

    /**
     * Load data.
     *
     * @return the java bean tester builder
     */
    public JavaBeanTesterBuilder<T, E> loadData() {
        return loadData(true);
    }

    /**
     * Load data.
     *
     * @param value
     *            the value
     * @return the java bean tester builder
     */
    public JavaBeanTesterBuilder<T, E> loadData(boolean value) {
        worker.setLoadData(value ? LoadData.ON : LoadData.OFF);
        return this;
    }

    /**
     * Skip.
     *
     * @param propertyNames
     *            the property names
     * @return the java bean tester builder
     */
    public JavaBeanTesterBuilder<T, E> skip(String... propertyNames) {
        if (propertyNames != null) {
            for (String propertyName : propertyNames) {
                worker.getSkipThese().add(propertyName);
            }
        }
        return this;
    }

    /**
     * Test.
     *
     * @throws IllegalAccessException
     *             the illegal access exception
     * @throws IntrospectionException
     *             the introspection exception
     * @throws InstantiationException
     *             the instantiation exception
     */
    public void test() throws IllegalAccessException, IntrospectionException, InstantiationException {
        worker.test();
    }

    /**
     * Tests the equals/hashCode/toString methods of the specified class.
     *
     * @throws IllegalAccessException
     *             thrown if the clazz.newIntances() method throws this exception for the class under test.
     * @throws IntrospectionException
     *             thrown if the load method throws this exception for the class under test.
     * @throws InstantiationException
     *             thrown if the clazz.newInstance() method throws this exception for the class under test.
     */
    public void testObjectMethods() throws IllegalAccessException, IntrospectionException, InstantiationException {
        worker.equalsHashCodeToStringSymmetricTest();
    }

    /**
     * Getter Setter Tests.
     *
     * @param instance
     *            the instance of class under test.
     * @throws IntrospectionException
     *             thrown if the Introspector.getBeanInfo() method throws this exception for the class under test.
     */
    public void testInstance(T instance) throws IntrospectionException {
        worker.getterSetterTests(instance);
    }

    /**
     * Test equals.
     *
     * @param instance
     *            the instance
     * @param expected
     *            the expected
     * @throws IntrospectionException
     *             the introspection exception
     */
    public void testEquals(T instance, T expected) throws IntrospectionException {
        worker.equalsTests(instance, expected);
    }
}
