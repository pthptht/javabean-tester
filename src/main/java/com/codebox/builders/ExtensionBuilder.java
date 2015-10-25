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
package com.codebox.builders;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtField;
import javassist.CtMethod;
import javassist.NotFoundException;

public class ExtensionBuilder<T> {

    public Class<?> generate(Class<T> clazz) throws NotFoundException, CannotCompileException {
        try {
            // If extension already recreated, return it
            return Class.forName(clazz.getName() + "Extension");
        } catch (@SuppressWarnings("unused") ClassNotFoundException e) {
            // No extension exists, so create it
        }

        final ClassPool pool = ClassPool.getDefault();
        final CtClass cc = pool.makeClass(clazz.getName() + "Extension");

        // add super class
        cc.setSuperclass(resolveCtClass(clazz));

        final Map<String, Class<?>> properties = new HashMap<String, Class<?>>();
        properties.put("jbExtension1", String.class);
        properties.put("jbExtension2", String.class);
        properties.put("jbExtension3", String.class);
        properties.put("jbExtension4", String.class);

        for (final Entry<String, Class<?>> entry : properties.entrySet()) {

            // Add field
            cc.addField(new CtField(resolveCtClass(entry.getValue()), entry.getKey(), cc));

            // Add getter
            cc.addMethod(generateGetter(cc, entry.getKey(), entry.getValue()));

            // Add setter
            cc.addMethod(generateSetter(cc, entry.getKey(), entry.getValue()));
        }

        return cc.toClass();
    }

    private static CtMethod generateGetter(CtClass declaringClass, String fieldName, Class<?> fieldClass)
            throws CannotCompileException {
        final String getterName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
        final StringBuilder sb = new StringBuilder();
        sb.append("public ").append(fieldClass.getName()).append(" ").append(getterName).append("(){")
                .append("return this.").append(fieldName).append(";").append("}");
        return CtMethod.make(sb.toString(), declaringClass);
    }

    private static CtMethod generateSetter(CtClass declaringClass, String fieldName, Class<?> fieldClass)
            throws CannotCompileException {
        final String setterName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
        final StringBuilder sb = new StringBuilder();
        sb.append("public void ").append(setterName).append("(").append(fieldClass.getName()).append(" ")
                .append(fieldName).append(")").append("{").append("this.").append(fieldName).append("=")
                .append(fieldName).append(";").append("}");
        return CtMethod.make(sb.toString(), declaringClass);
    }

    private static CtClass resolveCtClass(Class<?> clazz) throws NotFoundException {
        ClassPool pool = ClassPool.getDefault();
        return pool.get(clazz.getName());
    }

}
