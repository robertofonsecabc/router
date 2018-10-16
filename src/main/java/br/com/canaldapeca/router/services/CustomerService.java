package br.com.canaldapeca.router.services;

import br.com.canaldapeca.router.RouterApplication;
import br.com.canaldapeca.router.models.Customer;
import br.com.canaldapeca.router.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("customerService")
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;


    public void integrate(Long customerId, String origin) {

        RouterApplication.IntegrationType type = RouterApplication.IntegrationType.valueOf(origin);

        Customer customer = customerRepository.findByIntegrationId( Customer.getIntegrationColumn(type) , customerId );



    }
}
