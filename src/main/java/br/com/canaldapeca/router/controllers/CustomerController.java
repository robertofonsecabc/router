package br.com.canaldapeca.router.controllers;

import br.com.canaldapeca.router.RouterApplication;
import br.com.canaldapeca.router.config.IntegrationSystemConverter;
import br.com.canaldapeca.router.models.RequestModel;
import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

/**
 * Controler de teste real
 */
@RestController
public class CustomerController {

    @Autowired
    private ProducerTemplate producerTemplate;

    @InitBinder
    public void initBinder(final WebDataBinder webdataBinder) {
        webdataBinder.registerCustomEditor(RouterApplication.IntegrationSystem.class, new IntegrationSystemConverter());
    }

    @RequestMapping(value = "/customer/",method = RequestMethod.POST)
    public void addCustomer(@RequestBody RequestModel requestModel) {
        producerTemplate.sendBody("direct:addCustomer", requestModel.toJson());
    }

    @RequestMapping(value = "/customer/",method = RequestMethod.PUT)
    public void editCustomer(@RequestBody  RequestModel requestModel) {
        producerTemplate.sendBody("direct:addCustomer", requestModel.toJson());
    }


}
