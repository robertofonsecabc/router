package br.com.canaldapeca.router;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

import java.util.Arrays;


@EnableJms
@SpringBootApplication
public class RouterApplication {


    public static void main(String[] args) {
        SpringApplication.run(RouterApplication.class, args);
    }

    /**
     * Sistemas que serão integrados
     *
     * Atinge diretamente os seguintes arquivos:
     *
     * @see br.com.canaldapeca.router.services.CustomerServiceImp#getCustomerByType(IntegrationSystem, String)
     */
    public enum IntegrationSystem {

        CWS("cws"), DATABASE("database"), SALESFORCE("salesforce");

        private final String description;

        IntegrationSystem(String description) {
            this.description = description;
        }

        public static IntegrationSystem fromValue(String value) {
            for (IntegrationSystem category : values()) {
                if (category.description.equalsIgnoreCase(value)) {
                    return category;
                }
            }
            throw new IllegalArgumentException("Unknown enum type " + value + ", Allowed values are " + Arrays.toString(values()));
        }

        public String getDescription() {
            return description;
        }

    }

    /**
     * Tipos de dados que são aceitos para integração
     */
    public enum  IntegrationType {

        CUSTOMER("customer"), PRODUCT("product"), STOCK("stock");

        private final String description;

        IntegrationType(String description) {
            this.description = description;
        }

        public static IntegrationType fromValue(String value) {
            for (IntegrationType category : values()) {
                if (category.description.equalsIgnoreCase(value)) {
                    return category;
                }
            }
            throw new IllegalArgumentException("Unknown enum type " + value + ", Allowed values are " + Arrays.toString(values()));
        }

        public String getDescription() {
            return description;
        }
    }
}
