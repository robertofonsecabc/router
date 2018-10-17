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

    @Autowired
    private CWSService cwsService;

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

        // Buscar cliente para ver se tem ID cadastrado nos outros sistemas
        Customer customer = this.getCustomerByType( type , customerId );

        // Se não tem o cliente
        if( customer == null ){
            customer = new Customer();
        }

        switch (type){
            case CWS:
                customer.setCwsId( Long.parseLong(customerId) );
                // integrar com os outros sistemas
                break;
            case SALESFORCE:
                customer.setSalesForceId( customerId );
                // integrar com os outros sistemas
                Long cwsId = this.cwsService.integrateCustomer()

                break;
            case DATABASE:
                customer.setOriginId( Long.parseLong(customerId) );
                // integrar com os outros sistemas

                break;
        }

        this.customerRepository.save(customer);
    }
}
