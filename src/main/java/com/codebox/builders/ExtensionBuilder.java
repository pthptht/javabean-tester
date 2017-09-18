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

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtField;
import javassist.CtMethod;
import javassist.NotFoundException;

/**
 * The Class ExtensionBuilder.
 *
 * @param <T>
 *            the generic type
 */
public class ExtensionBuilder<T> {

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(ExtensionBuilder.class);

    /**
     * Generate.
     *
     * @param clazz
     *            the clazz
     * @return the class
     * @throws NotFoundException
     *             the not found exception
     * @throws CannotCompileException
     *             the cannot compile exception
     */
    public Class<?> generate(Class<T> clazz) throws NotFoundException, CannotCompileException {
        try {
            // If extension already recreated, return it
            return Class.forName(clazz.getName() + "Extension");
        } catch (final ClassNotFoundException e) {
            // No extension exists, so create it
            ExtensionBuilder.LOGGER.trace("No extension exists, so create it", e);
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

    /**
     * Generate getter.
     *
     * @param declaringClass
     *            the declaring class
     * @param fieldName
     *            the field name
     * @param fieldClass
     *            the field class
     * @return the ct method
     * @throws CannotCompileException
     *             the cannot compile exception
     */
    private static CtMethod generateGetter(CtClass declaringClass, String fieldName, Class<?> fieldClass)
            throws CannotCompileException {
        final StringBuilder sb = new StringBuilder();
        sb.append("public ").append(fieldClass.getName()).append(" get").append(fieldName.substring(0, 1).toUpperCase())
                .append(fieldName.substring(1)).append("(){").append("return this.").append(fieldName).append(";")
                .append("}");
        return CtMethod.make(sb.toString(), declaringClass);
    }

    /**
     * Generate setter.
     *
     * @param declaringClass
     *            the declaring class
     * @param fieldName
     *            the field name
     * @param fieldClass
     *            the field class
     * @return the ct method
     * @throws CannotCompileException
     *             the cannot compile exception
     */
    private static CtMethod generateSetter(CtClass declaringClass, String fieldName, Class<?> fieldClass)
            throws CannotCompileException {
        final StringBuilder sb = new StringBuilder();
        sb.append("public void set").append(fieldName.substring(0, 1).toUpperCase()).append(fieldName.substring(1))
                .append("(").append(fieldClass.getName()).append(" ").append(fieldName).append(")").append("{")
                .append("this.").append(fieldName).append("=").append(fieldName).append(";").append("}");
        return CtMethod.make(sb.toString(), declaringClass);
    }

    /**
     * Resolve ct class.
     *
     * @param clazz
     *            the clazz
     * @return the ct class
     * @throws NotFoundException
     *             the not found exception
     */
    private static CtClass resolveCtClass(Class<?> clazz) throws NotFoundException {
        final ClassPool pool = ClassPool.getDefault();
        return pool.get(clazz.getName());
    }

}
