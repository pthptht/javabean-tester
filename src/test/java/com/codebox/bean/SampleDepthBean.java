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

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;

import lombok.Data;

/**
 * Instantiates a new sample depth bean.
 */
@Data
public class SampleDepthBean {

    /** The list. */
    private List<String> list;

    /** The map. */
    private Map<String, String> map;

    /** The concurrent map. */
    private ConcurrentMap<String, String> concurrentMap;

    /** The string. */
    private String string;

    /** The string array. */
    private String[] stringArray;

    /** The boolean wrapper. */
    private Boolean booleanWrapper;

    /** The int wrapper. */
    private Integer intWrapper;

    /** The long wrapper. */
    private Long longWrapper;

    /** The double wrapper. */
    private Double doubleWrapper;

    /** The float wrapper. */
    private Float floatWrapper;

    /** The character wrapper. */
    private Character characterWrapper;

    /** The byte wrapper. */
    private Byte byteWrapper;

    /** The byte array. */
    private Byte[] byteArray;

    /** The boolean primitive. */
    private boolean booleanPrimitive;

    /** The int primitive. */
    private int intPrimitive;

    /** The long primitive. */
    private long longPrimitive;

    /** The double primitive. */
    private double doublePrimitive;

    /** The float primitive. */
    private float floatPrimitive;

    /** The char primitive. */
    private char charPrimitive;

    /** The byte primitive. */
    private byte bytePrimitive;

    /** The date. */
    private Date date;

}
