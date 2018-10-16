package br.com.canaldapeca.router.config;

import org.apache.activemq.camel.component.ActiveMQComponent;
import org.apache.camel.CamelContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class CamelContextConfig {

    @Autowired
    protected CamelContext context;

    @Value("${spring.activemq.broker-url}")
    private String broker_url;


    @PostConstruct
    public void init() throws Exception {
        context.addComponent("jms", ActiveMQComponent.activeMQComponent(broker_url));
    }

}
