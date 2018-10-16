package br.com.canaldapeca.router.queue;

import br.com.canaldapeca.router.services.CustomerService;
import br.com.canaldapeca.router.services.CustomerServiceImp;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;
import java.util.Map;


@Service("customerQueueService")
public class CustomerQueueService {

    private final Logger logger = LoggerFactory.getLogger(CustomerQueueService.class);

    @Autowired
    private CustomerService customerService;

    /**
     * Escutar fila de Customer e fazer verificação de sistema
     * @param jsonMessage
     */
    @JmsListener(destination = "customer")
    public void receiveMessageFromQueue(final Message jsonMessage) throws JMSException {

        logger.info("Received customer: " + jsonMessage);

        if(jsonMessage instanceof TextMessage) {
            TextMessage textMessage = (TextMessage)jsonMessage;
            String messageData = textMessage.getText();
            Map map = new Gson().fromJson(messageData, Map.class);

            customerService.integrate( (String) map.get("id") , (String) map.get("origin") );
            return;
        }

        throw new RuntimeException("Erro ao converter");
    }




}
