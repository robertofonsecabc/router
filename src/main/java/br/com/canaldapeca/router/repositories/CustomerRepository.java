package br.com.canaldapeca.router.repositories;

import br.com.canaldapeca.router.models.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository("customerRepository")
public interface CustomerRepository extends CrudRepository<Customer,Long> {

    @Query("select c from Customer where :column = :value")
    Customer findByIntegrationId(@Param("column") String integrationColumn, @Param("value") Long customerId);
}
