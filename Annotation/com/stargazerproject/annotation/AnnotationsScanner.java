package com.stargazerproject.annotation;

import com.google.common.base.Optional;
import com.google.common.collect.Multimap;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Map;

/** 
 *  @name 注解扫描
 *  @illustrate 注解扫描的基础功能
 *  @author Felixerio
 *  **/
public interface AnnotationsScanner {
	
	public Optional<Multimap<Class<?>, Map.Entry<String, List<Object>>>> scannerAnnotation(Optional<String> packagesArg, Optional<Class<? extends Annotation>> annotationArg) throws IOException, ClassNotFoundException ;
	
	public Optional<List<String>> acquireAppointAnnotationAttributeValue(Optional<String> packagesArg, Optional<Class<? extends Annotation>> annotationArg, Optional<String> value) throws IOException, ClassNotFoundException;

	public <A extends Annotation> Optional<A> scannerObjectClassAnnotation(Optional<Object> object, Optional<Class<A>> annotationClass);
}
