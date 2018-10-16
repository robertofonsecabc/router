package br.com.canaldapeca.router.repositories;

import br.com.canaldapeca.router.models.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<Customer,Long> {

    public abstract Customer findByCwsId(Long id);

    public abstract Customer findBySalesForceId(String id);

    public abstract Customer findByOrOriginId(Long id);
}
