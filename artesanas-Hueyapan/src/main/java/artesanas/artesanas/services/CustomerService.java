package artesanas.artesanas.services;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import artesanas.artesanas.model.Customer;
import artesanas.artesanas.repository.CustomerRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> getAll(){
        return customerRepository.findAll();
    }

    public List<Customer> getAll(int page,  int pageSize){
        PageRequest pageRequest = PageRequest.of(page, pageSize);
        Page<Customer> customer = customerRepository.findAll(pageRequest);
        return customer.getContent();
    }
    
    public void save(Customer customer){
        customerRepository.save(customer);
    }

    public Customer getIdCustomer(Long idCustomer){
        return customerRepository.findById(idCustomer).get();
    }

    public void delete(Long idCustomer){
        customerRepository.deleteById(idCustomer);
    }

    public List<Customer> getCustomersByName(String name) {
        return customerRepository.getCustomersByNameJPQL(name); // Usar JPQL
    }
    
    public Optional<Customer> getCustomerByEmail(String email) {
        return customerRepository.getCustomerByEmailJPQL(email); // Usar JPQL
    }
    
    public List<Customer> getCustomerByUserName( String userName) {
        return customerRepository.getCustomerByUserNameJPQL(userName); // Usar JPQL
    }
    
}