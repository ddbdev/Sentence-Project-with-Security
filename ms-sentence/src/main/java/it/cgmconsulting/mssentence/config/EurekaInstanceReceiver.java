package it.cgmconsulting.mssentence.config;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;

@Configuration
public class EurekaInstanceReceiver {

    private final EurekaClient eurekaClient;
    @Autowired
    public EurekaInstanceReceiver(EurekaClient eurekaClient) {
        this.eurekaClient = eurekaClient;
    }
    @PostConstruct
    public HashMap<String, String> getEurekaInfoByServiceName(){
        HashMap<String, String> map = new HashMap<>();
        List<Application> registeredApplications = eurekaClient.getApplications().getRegisteredApplications();
        registeredApplications.forEach( application -> {
            List<InstanceInfo> instances = application.getInstances();
            instances.forEach( instanceInfo -> {
                map.put(
                        instanceInfo.getAppName().toLowerCase(),
                        instanceInfo.getIPAddr() + ":" + instanceInfo.getPort() + "/" + instanceInfo.getAppName().toLowerCase());
            });
        });
        return map;
    }
}
