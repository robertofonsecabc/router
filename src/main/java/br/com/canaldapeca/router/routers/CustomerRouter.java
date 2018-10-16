package br.com.canaldapeca.router.routers;


import org.apache.camel.builder.RouteBuilder;
import com.google.gson.Gson;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;
import java.util.Map;

@Component("customerRouter")
public class CustomerRouter extends RouteBuilder {

    private final String ROUTE_NAME = "CUSTOMER";

    /**
     * Adicionar um cliente na fila usando um
     * @throws Exception
     */
    @Override
    public void configure() throws Exception {
        from("direct:addCustomer")
                .routeId(ROUTE_NAME)
                .log("Adicionar cliente na fila: ${body}")
                .to("jms:customer.incoming");
    }



    /**
     * Listener de JSM com adição de resultado em fila
     *
     * @param jsonMessage
     * @return
     * @throws JMSException
     */
    @JmsListener(destination = "customer.incoming")
    // @SendTo("outbound.topic")
    public String receiveMessageFromTopic(final Message jsonMessage) throws JMSException {
        String messageData = null;
        System.out.println("Received message " + jsonMessage);
        String response = null;

        if(jsonMessage instanceof TextMessage) {
            TextMessage textMessage = (TextMessage)jsonMessage;
            messageData = textMessage.getText();
            Map map = new Gson().fromJson(messageData, Map.class);
            response  = "Hello " + map.get("name");
        }

        return response;
    }


}
