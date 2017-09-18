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

import lombok.Data;

import java.io.Serializable;

/**
 * The Class NonSerializableBean.
 */
@Data
public class NonSerializableBean implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The empty bean which is not serializable. */
    private EmptyBean         emptyBean;

    /**
     * Make bean non serializable.
     */
    public NonSerializableBean() {
        emptyBean = new EmptyBean();
    }

}
