/**
 * JavaBean Tester (https://github.com/hazendaz/javabean-tester)
 *
 * Copyright 2012-2019 Hazendaz.
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

import lombok.Value;

/**
 * The Class SampleValueObject.
 * 
 * <p>
 * From Lombok docs: https://projectlombok.org/features/Value
 * 
 * <p>
 * <code>@Value</code> is the immutable variant of <code>@Data</code>; all fields are made and final by default, and
 * setters are not generated. The class itself is also made final by default, because immutability is not something that
 * can be forced onto a subclass.
 */
@Value
public class SampleValueObject {

    /** The empty bean. */
    EmptyBean emptyBean;

    /** The sample depth bean. */
    SampleDepthBean sampleDepthBean;

    /** The list. */
    List<String> list;

    /** The map. */
    Map<String, String> map;

    /** The concurrent map. */
    ConcurrentMap<String, String> concurrentMap;

    /** The string. */
    String string;

    /** The string array. */
    String[] stringArray;

    /** The boolean wrapper. */
    Boolean booleanWrapper;

    /** The int wrapper. */
    Integer intWrapper;

    /** The long wrapper. */
    Long longWrapper;

    /** The double wrapper. */
    Double doubleWrapper;

    /** The float wrapper. */
    Float floatWrapper;

    /** The character wrapper. */
    Character characterWrapper;

    /** The byte wrapper. */
    Byte byteWrapper;

    /** The byte array. */
    Byte[] byteArray;

    /** The boolean primitive. */
    boolean booleanPrimitive;

    /** The int primitive. */
    int intPrimitive;

    /** The long primitive. */
    long longPrimitive;

    /** The double primitive. */
    double doublePrimitive;

    /** The float primitive. */
    float floatPrimitive;

    /** The char primitive. */
    char charPrimitive;

    /** The byte primitive. */
    byte bytePrimitive;

    /** The date. */
    Date date;
}
