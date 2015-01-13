package com.sting2me.common.util.config;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;

/**
 * calss ConfigProperties defines the prefix, name and required for 
 * specified option in properties 
 * @author peter
 *
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ConfigProperties {

	public String name() default "";
	public boolean required() default false;
	public String prefix() default "default";
}
