package br.com.canaldapeca.router.repositories;


import br.com.canaldapeca.router.RouterApplication;
import br.com.canaldapeca.router.models.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hibernate.validator.internal.util.Contracts.assertNotNull;


@DataJpaTest
// @ActiveProfiles("test")
@RunWith(SpringRunner.class)
public class CustomerRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void testSavePeopleWithAccount(){

        Long id = 1L;

        Customer customer = new Customer();
        customer.setCwsId(id);
        this.customerRepository.save(customer);

        entityManager.flush();
        entityManager.clear();

        Customer found = this.customerRepository.findByIntegrationId(Customer.getIntegrationColumn(RouterApplication.IntegrationType.CWS),id);
        assertThat(found.getCwsId(), is( equalTo( customer.getCwsId() )) );
        assertNotNull(found.getId());
    }
}
