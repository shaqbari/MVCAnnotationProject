package com.sist.controller;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*
 *     @ =====> ElementType.TYPE
 *     public class A
 *     {
 *         @ ===> ElementType.FIELD
 *         B b;
 *         
 *         @ ==> ElementType.CONSTRUCTOR
 *         public A(@ B b)==> ElementType.PARAMETER
 *         {
 *         }
 *         
 *         @ ===> ElementType.METHOD
 *         public void disp()
 *         {
 *         }
 *     }
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface RequestMapping {
	public String value();
}
