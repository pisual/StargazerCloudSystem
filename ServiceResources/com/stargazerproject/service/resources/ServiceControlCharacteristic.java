package com.stargazerproject.service.resources;

import com.google.common.base.Optional;
import com.google.common.collect.Table;
import com.google.common.util.concurrent.AbstractIdleService;
import com.google.common.util.concurrent.ServiceManager;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import com.stargazerproject.service.ServiceControl;
import com.stargazerproject.service.ServiceInitialization;
import com.stargazerproject.spring.container.impl.BeanContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component(value = "serviceControlCharacteristic")
@Qualifier("serviceControlCharacteristic")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class ServiceControlCharacteristic implements ServiceControl, BaseCharacteristic<ServiceControl> {
	
	@Autowired
	@Qualifier("serviceInitializationCharacteristic")
	private BaseCharacteristic<ServiceInitialization> ServiceInitialization;
	
	private Table<Integer, String, Boolean> serviceMenu;
	
	private ServiceManager serviceManager;
	
	public ServiceControlCharacteristic() {}
	
	@Override
	public Optional<ServiceControl> characteristic() {
		return Optional.of(this);
	}

	@Override
	public void startAllservice() {
		serviceMenu = ServiceInitialization.characteristic().get().serviceMenu().get();

		List<String> serviceList = serviceMenu.cellSet().stream().map(x -> x.getColumnKey()).collect(Collectors.toList());
		serviceManager = new ServiceManager(serviceListConvertToAbstractIdleServiceList(Optional.of(serviceList)).get());

		Stream<Integer> serverLayerMenu = serviceMenu.cellSet().stream().map(x -> x.getRowKey()).distinct();
		serverLayerMenu.forEach(serviceLayer -> {
			serviceGroupStateBlockCheck(serviceLayer);
			startGroupService(serviceLayer);
		});
		serviceManager.awaitHealthy();
	}

	private void serviceGroupStateBlockCheck(int serviceLayer){
		if(serviceLayer != firstGroupService() || serviceLayer != loadingInAdvanceService()){
			while(checkGroupServer(beforeServiceLayer(serviceLayer)) == Boolean.FALSE){
				try {
					TimeUnit.MILLISECONDS.sleep(10);;
				} catch (InterruptedException interruptedException) {
					interruptedException.printStackTrace();
				}
			}
		}
	}

	private void startGroupService(int serviceLayer){
		serviceMenu.rowMap().get(serviceLayer).keySet().forEach(service->{
			new Thread(() -> {
				AbstractIdleService serviceUnit = BeanContainer.instance().getBean(Optional.of(service), AbstractIdleService.class);
				serviceUnit.startAsync().awaitRunning();
				serviceMenu.put(serviceLayer, service, Boolean.TRUE);
			}).start();
		});
	}

	@Override
	public void stopAllService() {

		serviceManager.stopAsync().awaitStopped();
	}

	private Optional<List<AbstractIdleService>> serviceListConvertToAbstractIdleServiceList(Optional<List<String>> serviceListArg) {
		return Optional.of(serviceListArg.get().stream().map(x -> BeanContainer.instance().getBean(Optional.of(x), AbstractIdleService.class)).collect(Collectors.toList()));
	}
	
	public Boolean checkGroupServer(int sercverIndex){
		Collection<Boolean> serverStateIterator = serviceMenu.row(sercverIndex).values();
		Boolean groupServerState = Boolean.TRUE;
		Iterator<Boolean> iterator = serverStateIterator.iterator();
		while(iterator.hasNext()){
			groupServerState = iterator.next()&&groupServerState;
		}
		
		return groupServerState;
	}
	
	private int firstGroupService(){
		return 1;
	}
	
	private int loadingInAdvanceService(){
		return 0;
	}
	
	private int beforeServiceLayer(int layer){
		return layer - 1;
	}

}