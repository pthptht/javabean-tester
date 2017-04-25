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

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.codebox.bean.SampleBean;

import javassist.CannotCompileException;
import javassist.NotFoundException;

/**
 * The Class ExtensionBuilderTest.
 */
// TODO 11/26/15 For some reason this fails on travis-ci java 7 only even though real code worked days ago.
@Ignore
public class ExtensionBuilderTest {

    /** The class. */
    private Class<SampleBean> clazz;

    /** The extension. */
    private Class<SampleBean> extension;

    /**
     * Inits the.
     */
    @Before
    public void init() {
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
    public void extensionBuilder() throws NotFoundException, CannotCompileException {
        this.extension = (Class<SampleBean>) new ExtensionBuilder<SampleBean>().generate(this.clazz);
        Assert.assertNotEquals(this.clazz, this.extension);
    }
}
