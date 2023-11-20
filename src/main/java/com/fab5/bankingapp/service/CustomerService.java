package com.fab5.bankingapp.service;

import com.fab5.bankingapp.exceptions.AccountNotFoundException;
import com.fab5.bankingapp.exceptions.CustomerNotFoundException;
import com.fab5.bankingapp.exceptions.NoSuchElementFoundException;
import com.fab5.bankingapp.model.Account;
import com.fab5.bankingapp.model.Customer;
import com.fab5.bankingapp.repository.CustomerRepository;
import com.fab5.bankingapp.validation.IDValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService implements IDValidation<CustomerNotFoundException, AccountNotFoundException> {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public void verifyID1(Long id) throws CustomerNotFoundException {
        Optional<Customer> checkCustomer = customerRepository.findById(id);
        if(checkCustomer.isEmpty()){
            throw new CustomerNotFoundException(id);
        }
    }

    @Override
    public void verifyID2(Long id) throws AccountNotFoundException {
        Optional<Customer> checkAccount = accountRepository.findById(id);
        if(checkAccount.isEmpty()){
            throw new AccountNotFoundException(id);
        }

    }

    public List<Customer> getAllCustomers() {
        return (List<Customer>) customerRepository.findAll();
    }

    // Get customer by ID
    public Optional<Customer> getCustomerById(Long customerId) {
        return customerRepository.findById(customerId);
    }

    // Create a new customer
    public Customer createCustomer(Customer customer) {
        // You might want to validate data before saving
        return customerRepository.save(customer);
    }
    public Customer updateCustomer(Long id, Customer updatedCustomer) {
        return customerRepository.findById(id)
                .map(existingCustomer -> {
                    existingCustomer.setFirstName(updatedCustomer.getFirstName());
                    existingCustomer.setLastName(updatedCustomer.getLastName());
                    // Update other fields as needed
                    return customerRepository.save(existingCustomer);
                })
                .orElse(null); // Handle the case where the customer with the given id is not found
    }
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }
    public Iterable<Customer> getCustomerByAccountId(Long id){
        return customerRepository.findCustomerByAccountId(id);

    }

}


