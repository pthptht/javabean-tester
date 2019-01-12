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

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;

import lombok.Value;

/*
    From Lombok docs: https://projectlombok.org/features/Value
    @Value is the immutable variant of @Data; 
    all fields are made and final by default, and setters are not generated. 
    The class itself is also made final by default, because immutability is not something that can be forced onto a subclass.
 */
@Value
public class SampleValueObject {
    EmptyBean emptyBean;
    SampleDepthBean sampleDepthBean;
    List<String> list;
    Map<String, String> map;
    ConcurrentMap<String, String> concurrentMap;
    String string;
    String[] stringArray;
    Boolean booleanWrapper;
    Integer intWrapper;
    Long longWrapper;
    Double doubleWrapper;
    Float floatWrapper;
    Character characterWrapper;
    Byte byteWrapper;
    Byte[] byteArray;
    boolean booleanPrimitive;
    int intPrimitive;
    long longPrimitive;
    double doublePrimitive;
    float floatPrimitive;
    char charPrimitive;
    byte bytePrimitive;
    Date date;
}
