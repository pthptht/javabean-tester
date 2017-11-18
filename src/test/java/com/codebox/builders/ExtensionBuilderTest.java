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
package com.codebox.builders;

import com.codebox.bean.SampleBean;

import javassist.CannotCompileException;
import javassist.NotFoundException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The Class ExtensionBuilderTest.
 */
public class ExtensionBuilderTest {

    /** The class. */
    private Class<SampleBean> clazz;

    /** The extension. */
    private Class<SampleBean> extension;

    /**
     * Inits the.
     */
    @BeforeEach
    void init() {
        this.clazz = SampleBean.class;
        this.extension = SampleBean.class;
    }

    /**
     * Extension builder.
     *
     * @throws NotFoundException
     *             the not found exception
     * @throws CannotCompileException
     *             the cannot compile exception
     */
    @SuppressWarnings("unchecked")
    @Test
    void extensionBuilder() throws NotFoundException, CannotCompileException {
        this.extension = (Class<SampleBean>) new ExtensionBuilder<SampleBean>().generate(this.clazz);
        Assertions.assertNotEquals(this.clazz, this.extension);
    }
}
