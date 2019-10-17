package com.stargazerproject.spring.context.initialization.test;

import com.google.common.base.Optional;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import com.stargazerproject.service.ServiceControl;
import com.stargazerproject.service.ServiceInitialization;
import com.stargazerproject.spring.container.impl.BeanContainer;
import org.junit.BeforeClass;

public class BaseJunitTest {

    @BeforeClass
    public static void startAllService(){
        GlobalAnnotationApplicationContextInitialization.ApplicationContextInitialize(new String [] {});
        serverListInitialization();
        BaseCharacteristic<ServiceControl> serviceControl = BeanContainer.instance().getBean(Optional.of("serviceControlCharacteristic"), BaseCharacteristic.class);
        serviceControl.characteristic().get().startAllservice();
    }


//    @AfterClass
//    public static void stopAllService(){
//        try {
//            TimeUnit.SECONDS.sleep(10);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        BaseCharacteristic<ServiceControl> serviceControl = BeanContainer.instance().getBean(Optional.of("serviceControlCharacteristic"), BaseCharacteristic.class);
//        serviceControl.characteristic().get().stopAllService();
//    }

    private static void serverListInitialization(){
        BaseCharacteristic<ServiceInitialization> serverInitialization = BeanContainer.instance().getBean(Optional.of("serviceInitializationCharacteristic"), BaseCharacteristic.class);
        serverInitialization.characteristic().get().initializationFromAnnotationsScan();
    }

    protected <T> T initializationElement(String name, Class<T> t){
        return BeanContainer.instance().getBean(Optional.of(name), t);
    }

}
