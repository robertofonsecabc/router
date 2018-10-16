package br.com.canaldapeca.router;


import org.apache.camel.CamelContext;
import org.apache.activemq.camel.component.ActiveMQComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

import javax.annotation.PostConstruct;


@EnableJms
@SpringBootApplication
public class RouterApplication {

    @Autowired
    private CamelContext context;

    @Value("${spring.activemq.broker-url}")
    private String broker_url;


    @PostConstruct
    public void init() throws Exception {
        context.addComponent("jms", ActiveMQComponent.activeMQComponent(broker_url));
    }

    public static void main(String[] args) {
        SpringApplication.run(RouterApplication.class, args);
    }


    public enum IntegrationType{

        CWS("cws"), DATABASE("database"), SALESFORCE("salesforce");

        private final String description;

        IntegrationType(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }

    }

}
