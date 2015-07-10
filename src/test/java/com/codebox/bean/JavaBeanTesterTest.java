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

import java.beans.IntrospectionException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.codebox.enums.CanEquals;
import com.codebox.enums.LoadData;

public class JavaBeanTesterTest {

    private SampleBean sampleBean;
    private SampleBean expectedBean;

    @Before
    public void init() {
        this.sampleBean = new SampleBean();
        this.expectedBean = new SampleBean();
    }

    @Test
    public void load_fullBean() throws IntrospectionException {
        JavaBeanTester.load(SampleBean.class, this.sampleBean, LoadData.ON);
        Assert.assertNotNull(this.sampleBean.getDoubleWrapper());
    }

    @Test
    public void load_fullBeanEquals() throws IntrospectionException {
        JavaBeanTester.load(SampleBean.class, this.sampleBean, LoadData.ON);
        JavaBeanTester.load(SampleBean.class, this.expectedBean, LoadData.ON);

        this.sampleBean.setSampleDepthBean(new SampleDepthBean());
        this.expectedBean.setSampleDepthBean(new SampleDepthBean());
        this.sampleBean.setEmptyBean(new EmptyBean());
        this.expectedBean.setEmptyBean(new EmptyBean());
        JavaBeanTester.load(SampleDepthBean.class, this.sampleBean.getSampleDepthBean(), LoadData.ON);
        JavaBeanTester.load(SampleDepthBean.class, this.expectedBean.getSampleDepthBean(), LoadData.ON);

        JavaBeanTester.equalsTests(this.sampleBean, this.expectedBean, LoadData.ON);
    }

    @Test
    public void load_fullBeanEqualsShort() throws IntrospectionException {
        JavaBeanTester.load(SampleBean.class, this.sampleBean, LoadData.ON);
        JavaBeanTester.load(SampleBean.class, this.expectedBean, LoadData.ON);
        JavaBeanTester.equalsTests(this.sampleBean, this.expectedBean, LoadData.ON);
    }

    @Test
    public void load_fullBeanEqualsSkipUnderlying() throws IntrospectionException {
        JavaBeanTester.load(SampleBean.class, this.sampleBean, LoadData.OFF);
        JavaBeanTester.load(SampleBean.class, this.expectedBean, LoadData.OFF);
        JavaBeanTester.equalsTests(this.sampleBean, this.expectedBean, LoadData.OFF);
    }

    @Test
    public void load_fullBeanSkipUnderlyingData() throws IntrospectionException {
        JavaBeanTester.load(SampleBean.class, this.sampleBean, LoadData.OFF);
        Assert.assertNotNull(this.sampleBean.getDoubleWrapper());
    }

    @Test
    public void load_partialBeanEquals() throws IntrospectionException {
        JavaBeanTester.load(SampleBean.class, this.sampleBean, LoadData.ON);
        JavaBeanTester.load(SampleBean.class, this.expectedBean, LoadData.ON);

        this.sampleBean.setSampleDepthBean(new SampleDepthBean());
        this.expectedBean.setSampleDepthBean(new SampleDepthBean());
        JavaBeanTester.load(SampleDepthBean.class, this.sampleBean.getSampleDepthBean(), LoadData.ON);
        JavaBeanTester.load(SampleDepthBean.class, this.expectedBean.getSampleDepthBean(), LoadData.ON);

        JavaBeanTester.equalsTests(this.sampleBean, this.expectedBean, LoadData.ON);
    }

    @Test
    public void load_skipBeanProperties() throws IntrospectionException {
        JavaBeanTester.load(SampleBean.class, this.sampleBean, LoadData.ON, "string");
        Assert.assertNotNull(this.sampleBean.getDoubleWrapper());
        Assert.assertNull(this.sampleBean.getString());
    }

    @Test
    public void test_fullBean() throws IntrospectionException, InstantiationException, IllegalAccessException {
        JavaBeanTester.test(SampleBean.class, SampleExtensionBean.class, CanEquals.ON, LoadData.ON);
    }

    @Test
    public void test_fullBeanNullExt() throws IntrospectionException, InstantiationException, IllegalAccessException {
        JavaBeanTester.test(SampleBean.class, null, CanEquals.ON, LoadData.ON);
    }

    @Test
    public void test_fullBeanSkipUnderlyingData() throws IntrospectionException, InstantiationException,
            IllegalAccessException {
        JavaBeanTester.test(SampleBean.class, SampleExtensionBean.class, CanEquals.ON, LoadData.OFF);
    }

    @Test
    public void test_skipBeanProperties() throws IntrospectionException, InstantiationException, IllegalAccessException {
        JavaBeanTester.test(SampleBean.class, SampleExtensionBean.class, CanEquals.ON, LoadData.ON, "string");
    }

    @Test
    public void test_skipCanEquals() throws IntrospectionException, InstantiationException, IllegalAccessException {
        JavaBeanTester.test(SampleBean.class, SampleExtensionBean.class, CanEquals.OFF, LoadData.ON);
    }

}
