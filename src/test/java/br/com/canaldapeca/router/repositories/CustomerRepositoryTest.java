package br.com.canaldapeca.router.repositories;


import br.com.canaldapeca.router.RouterApplication;
import br.com.canaldapeca.router.models.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.hibernate.validator.internal.util.Contracts.assertNotNull;


@DataJpaTest
// @ActiveProfiles("test")
@RunWith(SpringRunner.class)
//@ContextConfiguration
//@RunWith(SpringJUnit4ClassRunner.class)
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

        List<Customer> customerList = (List<Customer>) this.customerRepository.findAll();
        assertThat(customerList.size(),is(greaterThan(0)));

        Customer found = this.customerRepository.findByCwsId(id);
        assertThat(found.getCwsId(), is( equalTo( customer.getCwsId() )) );
        assertNotNull(found.getId());
    }
}
