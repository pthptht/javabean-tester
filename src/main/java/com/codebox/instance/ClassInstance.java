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

import com.codebox.bean.ValueBuilder;
import com.codebox.enums.LoadData;
import com.codebox.enums.LoadType;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.junit.jupiter.api.Assertions;

/**
 * The Class Instance.
 *
 * @param <T>
 *            the element type
 */
public class ClassInstance<T> {

    /**
     * New instance. This will get the first available constructor to run the test on. This allows for instances where
     * there is intentionally not a no-arg constructor.
     *
     * @param clazz
     *            the class
     * @return the t
     */
    @SuppressWarnings("unchecked")
    public final T newInstance(final Class<T> clazz) {
        for (final Constructor<?> constructor : clazz.getConstructors()) {
            final Class<?>[] types = constructor.getParameterTypes();

            final Object[] values = new Object[constructor.getParameterTypes().length];

            for (int i = 0; i < values.length; i++) {
                values[i] = this.buildValue(types[i], LoadType.STANDARD_DATA);
            }

            try {
                return (T) constructor.newInstance(values);
            } catch (final InstantiationException | IllegalAccessException | InvocationTargetException e) {
                Assertions
                        .fail(String.format("An exception was thrown while testing the class (new instance) '%s': '%s'",
                                constructor.getName(), e.toString()));
            }
        }
        return null;
    }

    /**
     * Builds the value.
     *
     * @param <R>
     *            the generic type
     * @param returnType
     *            the return type
     * @param loadType
     *            the load type
     * @return the object
     */
    public <R> Object buildValue(final Class<R> returnType, final LoadType loadType) {
        final ValueBuilder valueBuilder = new ValueBuilder();
        valueBuilder.setLoadData(LoadData.ON);
        return valueBuilder.buildValue(returnType, loadType);
    }

}
