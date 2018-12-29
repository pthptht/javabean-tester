/**
 * JavaBean Tester (https://github.com/hazendaz/javabean-tester)
 *
 * Copyright (c) 2012 - 2018 Hazendaz.
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

import org.junit.jupiter.api.Test;

/**
 * The Class ObjectMethodsTest.
 */
public class ObjectMethodsTest {

    /**
     * Object methods.
     */
    @Test
    void objectMethods() {
        JavaBeanTester.builder(SampleBean.class).testObjectMethods();
    }

}
