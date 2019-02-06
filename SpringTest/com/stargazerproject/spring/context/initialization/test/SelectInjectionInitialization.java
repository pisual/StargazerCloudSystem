package com.stargazerproject.spring.context.initialization.test;

import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;
import com.stargazerproject.cache.aop.configuration.ParametersInjectAOPConfiguration;
import com.stargazerproject.cache.datastructure.impl.*;
import com.stargazerproject.cache.impl.SystemParameterCahce;
import com.stargazerproject.cache.impl.resources.SystemParameterCahceCharacteristic;
import com.stargazerproject.cache.impl.resources.shell.SystemParameterCahceShell;
import com.stargazerproject.cache.server.impl.SystemParameterCacheServer;
import com.stargazerproject.cache.server.listener.impl.SystemParameterCacheServerListener;
import com.stargazerproject.cache.server.manage.SystemParameterCacheServerManage;
import com.stargazerproject.resources.parameter.InformationParameter;
import com.stargazerproject.resources.parameter.NegotiateParameters;
import com.stargazerproject.resources.parameter.SequenceParameters;
import com.stargazerproject.resources.parameter.SystemParameters;

import java.util.Arrays;
import java.util.List;

public class SelectInjectionInitialization {

    private Multimap<String, Class<?>> loadingList = LinkedHashMultimap.create();



    public static void SelectInjection(String modelName){

    }

    private void loadingListInitialization(){

        /**Depend SystemParameter **/
        injectionParameters("SystemParameter",
                SystemParameterCahce.class,
                SystemParameterCahceCharacteristic.class,
                SystemParameterCahceShell.class,
                SystemParameterCacheServer.class,
                SystemParameterCacheServerListener.class,
                SystemParameterCacheServerManage.class,
                NegotiateParameters.class,
                SystemParameters.class,
                SequenceParameters.class,
                InformationParameter.class,
                ParametersInjectAOPConfiguration.class);

        /**Depend ComponentCache **/
        injectionParameters("ComponentCache",
                ObjectParameterCache.class,
                SocketChannelCache.class,
                InterProcessSemaphoreMutexCache.class,
                LeaderLatchParameterCache.class,
                TreeCacheCache.class,
                ServerCache.class,
                ServerListCache.class);

        /**Depend OrderQueueMessage **/



    }

    private void injectionParameters(String className, Class<?> ... clazz){
        List<Class<?>> list = Arrays.asList(clazz);
        loadingList.putAll(className,list);
    }

}
