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
package com.codebox.bean;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import mockit.Deencapsulation;

/**
 * The Class JavaBeanTesterTest.
 */
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
        this.sampleBean = new SampleBean(null);
        this.expectedBean = new SampleBean(null);
    }

    /**
     * Load_full bean.
     */
    @Test
    public void load_fullBean() {
        JavaBeanTester.builder(SampleBean.class).loadData().testInstance(this.sampleBean);
        Assert.assertNotNull(this.sampleBean.getDoubleWrapper());
    }

    /**
     * Load_full bean equals.
     */
    @Test
    public void load_fullBeanEquals() {
        JavaBeanTester.builder(SampleBean.class).loadData().testInstance(this.sampleBean);
        JavaBeanTester.builder(SampleBean.class).loadData().testInstance(this.expectedBean);

        this.sampleBean.setSampleDepthBean(new SampleDepthBean());
        this.expectedBean.setSampleDepthBean(new SampleDepthBean());
        this.sampleBean.setEmptyBean(new EmptyBean());
        this.expectedBean.setEmptyBean(new EmptyBean());
        JavaBeanTester.builder(SampleDepthBean.class).loadData().testInstance(this.sampleBean.getSampleDepthBean());
        JavaBeanTester.builder(SampleDepthBean.class).loadData().testInstance(this.expectedBean.getSampleDepthBean());

        JavaBeanTester.builder(SampleBean.class).loadData().testEquals(this.sampleBean, this.expectedBean);
    }

    /**
     * Load_full bean equals short.
     */
    @Test
    public void load_fullBeanEqualsShort() {
        JavaBeanTester.builder(SampleBean.class).loadData().testInstance(this.sampleBean);
        JavaBeanTester.builder(SampleBean.class).loadData().testInstance(this.expectedBean);
        JavaBeanTester.builder(SampleBean.class).loadData().testEquals(this.sampleBean, this.expectedBean);
    }

    /**
     * Load_full bean equals skip underlying.
     */
    @Test
    public void load_fullBeanEqualsSkipUnderlying() {
        JavaBeanTester.builder(SampleBean.class).testInstance(this.sampleBean);
        JavaBeanTester.builder(SampleBean.class).testInstance(this.expectedBean);
        JavaBeanTester.builder(SampleBean.class).testEquals(this.sampleBean, this.expectedBean);
    }

    /**
     * Load_full bean skip underlying data.
     */
    @Test
    public void load_fullBeanSkipUnderlyingData() {
        JavaBeanTester.builder(SampleBean.class).testInstance(this.sampleBean);
        Assert.assertNotNull(this.sampleBean.getDoubleWrapper());
    }

    /**
     * Load_partial bean equals.
     */
    @Test
    public void load_partialBeanEquals() {
        JavaBeanTester.builder(SampleBean.class).loadData().testInstance(this.sampleBean);
        JavaBeanTester.builder(SampleBean.class).loadData().testInstance(this.expectedBean);

        this.sampleBean.setSampleDepthBean(new SampleDepthBean());
        this.expectedBean.setSampleDepthBean(new SampleDepthBean());
        JavaBeanTester.builder(SampleDepthBean.class).loadData().testInstance(this.sampleBean.getSampleDepthBean());
        JavaBeanTester.builder(SampleDepthBean.class).loadData().testInstance(this.expectedBean.getSampleDepthBean());

        JavaBeanTester.builder(SampleBean.class).loadData().testEquals(this.sampleBean, this.expectedBean);
    }

    /**
     * Load_skip bean properties.
     */
    @Test
    public void load_skipBeanProperties() {
        JavaBeanTester.builder(SampleBean.class).loadData().skip("string").testInstance(this.sampleBean);
        Assert.assertNotNull(this.sampleBean.getDoubleWrapper());
        Assert.assertNull(this.sampleBean.getString());
    }

    /**
     * Test_full bean.
     */
    @Test
    public void test_fullBean() {
        JavaBeanTester.builder(SampleBean.class, SampleExtensionBean.class).checkEquals().loadData().test();
    }

    /**
     * Test_full bean null ext.
     */
    @Test
    public void test_fullBeanNullExt() {
        JavaBeanTester.builder(SampleBean.class).checkEquals().loadData().test();
    }

    /**
     * Test_full bean skip underlying data.
     */
    @Test
    public void test_fullBeanSkipUnderlyingData() {
        JavaBeanTester.builder(SampleBean.class, SampleExtensionBean.class).checkEquals().test();
    }

    /**
     * Test_skip bean properties.
     */
    @Test
    public void test_skipBeanProperties() {
        JavaBeanTester.builder(SampleBean.class, SampleExtensionBean.class).checkEquals().loadData().skip("string")
                .test();
    }

    /**
     * Test_skip can equals.
     */
    @Test
    public void test_skipCanEquals() {
        JavaBeanTester.builder(SampleBean.class, SampleExtensionBean.class).loadData().test();
    }

    /**
     * Test_skip all as false.
     */
    @Test
    public void test_skipCanEqualsFalse() {
        JavaBeanTester.builder(SampleBean.class, SampleExtensionBean.class).checkEquals(false).checkSerializable(false)
                .loadData(false).test();
    }

    /**
     * Test_serializable.
     */
    @Test
    public void test_serializable() {
        JavaBeanTester.builder(SerializableBean.class).checkSerializable().test();
    }

    /**
     * Test_non serializable.
     */
    @Test
    public void test_nonSerializable() {
        JavaBeanTester.builder(NonSerializableBean.class).checkSerializable().test();
    }

    /**
     * Test_temporary single mode.
     */
    // TODO 11/26/15 Temporary until we start using internalized extension logic
    @Test
    public void test_temporarySingleMode() {
        JavaBeanTesterBuilder<String, Object> builder = new JavaBeanTesterBuilder<>(String.class);
        JavaBeanTesterWorker<String, Object> worker = Deencapsulation.getField(builder, "worker");
        Assert.assertEquals(String.class, worker.getClazz());
    }

}
