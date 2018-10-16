package br.com.canaldapeca.router.services;

import br.com.canaldapeca.router.RouterApplication;
import br.com.canaldapeca.router.models.Customer;

public interface CustomerService {

    public abstract Customer getCustomerByType(RouterApplication.IntegrationSystem type , String customerId);

    public abstract void integrate(String customerId, String origin);

}
