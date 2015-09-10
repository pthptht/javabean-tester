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

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.codebox.enums.CanEquals;
import com.codebox.enums.LoadData;

/**
 * The Class JavaBeanTesterTest.
 */
@SuppressWarnings("deprecation")
public class JavaBeanTesterTest {

    /** The sample bean. */
    private SampleBean sampleBean;

    /** The expected bean. */
    private SampleBean expectedBean;

    /**
     * Inits the.
     */
    @Before
    public void init() {
        this.sampleBean = new SampleBean();
        this.expectedBean = new SampleBean();
    }

    /**
     * Load_full bean.
     */
    @Test
    public void load_fullBean() {
        JavaBeanTester.load(SampleBean.class, this.sampleBean, LoadData.ON);
        Assert.assertNotNull(this.sampleBean.getDoubleWrapper());
    }

    /**
     * Load_full bean equals.
     */
    @Test
    public void load_fullBeanEquals() {
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

    /**
     * Load_full bean equals short.
     */
    @Test
    public void load_fullBeanEqualsShort() {
        JavaBeanTester.load(SampleBean.class, this.sampleBean, LoadData.ON);
        JavaBeanTester.load(SampleBean.class, this.expectedBean, LoadData.ON);
        JavaBeanTester.equalsTests(this.sampleBean, this.expectedBean, LoadData.ON);
    }

    /**
     * Load_full bean equals skip underlying.
     */
    @Test
    public void load_fullBeanEqualsSkipUnderlying() {
        JavaBeanTester.load(SampleBean.class, this.sampleBean, LoadData.OFF);
        JavaBeanTester.load(SampleBean.class, this.expectedBean, LoadData.OFF);
        JavaBeanTester.equalsTests(this.sampleBean, this.expectedBean, LoadData.OFF);
    }

    /**
     * Load_full bean skip underlying data.
     */
    @Test
    public void load_fullBeanSkipUnderlyingData() {
        JavaBeanTester.load(SampleBean.class, this.sampleBean, LoadData.OFF);
        Assert.assertNotNull(this.sampleBean.getDoubleWrapper());
    }

    /**
     * Load_partial bean equals.
     */
    @Test
    public void load_partialBeanEquals() {
        JavaBeanTester.load(SampleBean.class, this.sampleBean, LoadData.ON);
        JavaBeanTester.load(SampleBean.class, this.expectedBean, LoadData.ON);

        this.sampleBean.setSampleDepthBean(new SampleDepthBean());
        this.expectedBean.setSampleDepthBean(new SampleDepthBean());
        JavaBeanTester.load(SampleDepthBean.class, this.sampleBean.getSampleDepthBean(), LoadData.ON);
        JavaBeanTester.load(SampleDepthBean.class, this.expectedBean.getSampleDepthBean(), LoadData.ON);

        JavaBeanTester.equalsTests(this.sampleBean, this.expectedBean, LoadData.ON);
    }

    /**
     * Load_skip bean properties.
     */
    @Test
    public void load_skipBeanProperties() {
        JavaBeanTester.load(SampleBean.class, this.sampleBean, LoadData.ON, "string");
        Assert.assertNotNull(this.sampleBean.getDoubleWrapper());
        Assert.assertNull(this.sampleBean.getString());
    }

    /**
     * Test_full bean.
     */
    @Test
    public void test_fullBean() {
        JavaBeanTester.test(SampleBean.class, SampleExtensionBean.class, CanEquals.ON, LoadData.ON);
        JavaBeanTester.builder(SampleBean.class, SampleExtensionBean.class).checkEquals().loadData().test();
    }

    /**
     * Test_full bean null ext.
     */
    @Test
    public void test_fullBeanNullExt() {
        JavaBeanTester.test(SampleBean.class, null, CanEquals.ON, LoadData.ON);
        JavaBeanTester.builder(SampleBean.class).checkEquals().loadData().test();
    }

    /**
     * Test_full bean skip underlying data.
     */
    @Test
    public void test_fullBeanSkipUnderlyingData() {
        JavaBeanTester.test(SampleBean.class, SampleExtensionBean.class, CanEquals.ON, LoadData.OFF);
        JavaBeanTester.builder(SampleBean.class, SampleExtensionBean.class).checkEquals().test();
    }

    /**
     * Test_skip bean properties.
     */
    @Test
    public void test_skipBeanProperties() {
        JavaBeanTester.test(SampleBean.class, SampleExtensionBean.class, CanEquals.ON, LoadData.ON, "string");
        JavaBeanTester.builder(SampleBean.class, SampleExtensionBean.class).checkEquals().loadData().skip("string")
                .test();
    }

    /**
     * Test_skip can equals.
     */
    @Test
    public void test_skipCanEquals() {
        JavaBeanTester.test(SampleBean.class, SampleExtensionBean.class, CanEquals.OFF, LoadData.ON);
        JavaBeanTester.builder(SampleBean.class, SampleExtensionBean.class).loadData().test();
    }

}
