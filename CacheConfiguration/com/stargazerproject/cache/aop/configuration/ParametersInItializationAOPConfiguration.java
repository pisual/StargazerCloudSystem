package com.stargazerproject.cache.aop.configuration;

import com.google.common.base.Optional;
import com.google.gson.Gson;
import com.stargazerproject.annotation.description.NeedInitialization;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.cache.MultimapCache;
import com.stargazerproject.log.LogMethod;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.util.Map;

/** 
 *  @name 针对被NeededInject标注的注解进行参数注入
 *  @illustrate 当BaseCharacteristic的characteristic方法被调用时，对NeededInject标注的注解进行参数注入
 *  @author Felixerio
 *  **/
@Component
@EnableAspectJAutoProxy
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
@Aspect
public class ParametersInItializationAOPConfiguration{

	/** @illustrate 获取Log(日志)接口 **/
	@Autowired
	@Qualifier("logRecord")
	private LogMethod baseLog;

	/** @construction 初始化构造 **/
	private ParametersInItializationAOPConfiguration() {}
	
	/** @illustrate initialize方法的AOP切点**/
	@Pointcut ("execution(* com.stargazerproject.interfaces.characteristic.shell.StanderCharacteristicShell.initialize(..))")
	public void characteristicMethod(){}
	
	/** @illustrate  characteristicMethod切点的具体方法**/
	@Around("characteristicMethod()")
	public Object setMethodAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
		try {
			Object object = proceedingJoinPoint.getTarget();
			Annotation[] annotationArray = object.getClass().getAnnotations();

			if(checkAnnotationExist(NeedInitialization.class, annotationArray)){
				String content = ((NeedInitialization)getAnnotation(NeedInitialization.class, annotationArray)).content();
				Object arg = ((Optional<Object>)proceedingJoinPoint.getArgs()[0]).get();

				if(arg instanceof MultimapCache){
					initMultimapParameters(parametersMap(content), (MultimapCache)arg);
				}
				else if(arg instanceof Cache){
					initMapParameters(parametersMap(content), (Cache)arg);
				}
				else{
					throw new IllegalArgumentException("NeedInitialization Parameters Type Error : " + arg.toString());
				}
			}
			return proceedingJoinPoint.proceed();
		} catch (Throwable throwable) {
			System.out.println(proceedingJoinPoint.getThis() + throwable.getMessage());
			throw throwable;
		}
	}

	private Map<String, String> parametersMap(String content){
		Map<String, String> map = new Gson().fromJson(content, Map.class);
		return map;
	}

	private void initMultimapParameters(Map<String, String> map, MultimapCache multimapCache){
		map.entrySet().stream().forEach(parameter -> multimapCache.put(Optional.of(parameter.getKey()), Optional.of(parameter.getValue())));
	}

	private void initMapParameters(Map<String, String> map, Cache cache){
		map.entrySet().stream().forEach(parameter -> cache.put(Optional.of(parameter.getKey()), Optional.of(parameter.getValue())));
	}

	private boolean checkAnnotationExist(Class<? extends Annotation> annotationClass, Annotation[] annotationArray){
		for (Annotation annotationUnit : annotationArray){
			if(annotationUnit.annotationType().equals(annotationClass)){
				return Boolean.TRUE;
			}
		}
		return Boolean.FALSE;
	}

	private Annotation getAnnotation(Class<? extends Annotation> annotationClass, Annotation[] annotationArray){
		for (Annotation annotationUnit : annotationArray){
			if(annotationUnit.annotationType().equals(annotationClass)){
				return annotationUnit;
			}
		}
		throw new NullPointerException("Annotation inexistence");
	}

}
	