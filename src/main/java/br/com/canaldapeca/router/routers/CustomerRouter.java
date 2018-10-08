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
     * Rota usando Camel
     * @throws Exception
     */
    @Override
    public void configure() throws Exception {
        from("direct:firstRoute")
                .routeId(ROUTE_NAME)
                .log("Camel body: ${body}")
                .to("jms:executed");
    }

    /**
     * Listener de fila JMS
     * @param text
     */
    @JmsListener(destination = "sample.queue")
    public void receiveQueue(String text) {
        System.out.println(text);
    }

    /**
     * Listener de JSM com adição de resultado em fila
     *
     * @param jsonMessage
     * @return
     * @throws JMSException
     */
    @JmsListener(destination = "inbound.topic")
    @SendTo("outbound.topic")
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
