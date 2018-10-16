package br.com.canaldapeca.router.services;

import br.com.canaldapeca.router.RouterApplication;
import br.com.canaldapeca.router.models.Customer;
import br.com.canaldapeca.router.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("customerService")
public class CustomerServiceImp implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Customer getCustomerByType(RouterApplication.IntegrationSystem type , String customerId){
        switch (type){
            case CWS: return this.customerRepository.findByCwsId( Long.parseLong(customerId) );
            case SALESFORCE: return this.customerRepository.findBySalesForceId( customerId );
            case DATABASE: return this.customerRepository.findByOrOriginId( Long.parseLong(customerId) );
        }
        return null;
    }

    @Override
    public void integrate(String customerId, String origin) {

        RouterApplication.IntegrationSystem type = RouterApplication.IntegrationSystem.valueOf(origin);

        Customer customer = this.getCustomerByType( type , customerId );



    }
}
