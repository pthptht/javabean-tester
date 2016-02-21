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

import com.codebox.enums.LoadData;
import com.codebox.enums.LoadType;
import com.codebox.instance.ClassInstance;
import com.codebox.instance.ConstructorInstance;

import lombok.Data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Builds values from given type.
 */
@Data
public class ValueBuilder {

    /** The load data. */
    private LoadData loadData;

    /**
     * Builds the value.
     *
     * @param <T>
     *            the generic type
     * @param clazz
     *            the clazz
     * @param loadType
     *            the load type
     * @return the object
     */
    public <T> Object buildValue(final Class<T> clazz, final LoadType loadType) {
        // Next check for a no-arg constructor
        final Constructor<?>[] ctrs = clazz.getConstructors();
        for (final Constructor<?> ctr : ctrs) {
            if (ctr.getParameterTypes().length == 0 && clazz != String.class) {
                if (this.loadData == LoadData.ON) {
                    // Load Underlying Data
                    JavaBeanTesterWorker<T, Object> beanTesterWorker = new JavaBeanTesterWorker<T, Object>(clazz);
                    beanTesterWorker.setLoadData(this.loadData);
                    beanTesterWorker.getterSetterTests(new ClassInstance<T>().newInstance(clazz));
                    return null;
                }
                // The class has a no-arg constructor, so just call it
                return ConstructorInstance.newInstance(ctr);
            }
        }

        // Specific rules for common classes
        Object returnObject = null;
        switch (loadType) {
            case ALTERNATE_DATA:
                returnObject = setAlternateValues(clazz);
                break;
            case NULL_DATA:
                returnObject = setNullValues(clazz);
                break;
            case STANDARD_DATA:
            default:
                returnObject = setStandardValues(clazz);
                break;
        }
        if (returnObject != null || loadType == LoadType.NULL_DATA) {
            return returnObject;

        } else if (clazz.isAssignableFrom(List.class)) {
            return new ArrayList<Object>();

        } else if (clazz.isAssignableFrom(Map.class)) {
            return new HashMap<Object, Object>();

        } else if (clazz.isAssignableFrom(ConcurrentMap.class)) {
            return new ConcurrentHashMap<Object, Object>();

        } else if (clazz == Logger.class) {
            return LoggerFactory.getLogger(clazz);

            // XXX Add additional rules here

        } else {

            // XXX Don't fail this...until alternative solution is determined
            // Assert.fail(String.format(
            // "Unable to build an instance of class '%s', please add some code to the '%s' class to do this.",
            // clazz.getName(), JavaBeanTester.class.getName()));
            return null;
        }
    }

    /**
     * Set using alternate test values.
     *
     * @param <T>
     *            the type parameter associated with the class under test.
     * @param clazz
     *            the class under test.
     * @return Object the Object to use for test.
     */
    private static <T> Object setAlternateValues(final Class<T> clazz) {
        return setValues(clazz, "ALT_VALUE", 1, Boolean.FALSE, Integer.valueOf(2), Long.valueOf(2),
                Double.valueOf(2.0), Float.valueOf(2.0F), Character.valueOf('N'), Byte.valueOf((byte) 2));
    }

    /**
     * Set using null test values.
     *
     * @param <T>
     *            the type parameter associated with the class under test.
     * @param clazz
     *            the class under test.
     * @return Object the Object to use for test.
     */
    private static <T> Object setNullValues(final Class<T> clazz) {
        return setValues(clazz, null, 0, null, null, null, null, null, null, null);
    }

    /**
     * Set using standard test values.
     *
     * @param <T>
     *            the type parameter associated with the class under test.
     * @param clazz
     *            the class under test.
     * @return Object the Object to use for test.
     */
    private static <T> Object setStandardValues(final Class<T> clazz) {
        return setValues(clazz, "TEST_VALUE", 1, Boolean.TRUE, Integer.valueOf(1), Long.valueOf(1),
                Double.valueOf(1.0), Float.valueOf(1.0F), Character.valueOf('Y'), Byte.valueOf((byte) 1));
    }

    /**
     * Set Values for object.
     *
     * @param <T>
     *            the type parameter associated with the class under test.
     * @param clazz
     *            the class instance under test.
     * @param string
     *            value of string object.
     * @param arrayLength
     *            amount of array objects to create.
     * @param booleanValue
     *            value of boolean object.
     * @param integerValue
     *            value of integer object.
     * @param longValue
     *            value of long object.
     * @param doubleValue
     *            value of double object.
     * @param floatValue
     *            value of float object.
     * @param characterValue
     *            value of character object.
     * @param byteValue
     *            value of character object.
     * @return Object value determined by input class. If not found, returns null.
     */
    private static <T> Object setValues(final Class<T> clazz, final String string, final int arrayLength,
            final Boolean booleanValue, final Integer integerValue, final Long longValue, final Double doubleValue,
            final Float floatValue, final Character characterValue, final Byte byteValue) {
        if (clazz == String.class) {
            return string;
        } else if (clazz.isArray()) {
            return Array.newInstance(clazz.getComponentType(), arrayLength);
        } else if (clazz == boolean.class || clazz == Boolean.class) {
            if (clazz == boolean.class && booleanValue == null) {
                return Boolean.FALSE;
            }
            return booleanValue;
        } else if (clazz == int.class || clazz == Integer.class) {
            if (clazz == int.class && integerValue == null) {
                return Integer.valueOf(-1);
            }
            return integerValue;
        } else if (clazz == long.class || clazz == Long.class) {
            if (clazz == long.class && longValue == null) {
                return Long.valueOf(-1);
            }
            return longValue;
        } else if (clazz == double.class || clazz == Double.class) {
            if (clazz == double.class && doubleValue == null) {
                return Double.valueOf(-1.0);
            }
            return doubleValue;
        } else if (clazz == float.class || clazz == Float.class) {
            if (clazz == float.class && floatValue == null) {
                return Float.valueOf(-1.0F);
            }
            return floatValue;
        } else if (clazz == char.class || clazz == Character.class) {
            if (clazz == char.class && characterValue == null) {
                return Character.valueOf('\u0000');
            }
            return characterValue;
        } else if (clazz == byte.class || clazz == Byte.class) {
            if (clazz == byte.class && byteValue == null) {
                return Byte.valueOf((byte) -1);
            }
            return byteValue;
        }
        return null;
    }
}
