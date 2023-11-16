package com.fab5.bankingapp.service;

import com.fab5.bankingapp.model.Customer;
import com.fab5.bankingapp.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    public Iterable<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Optional<Customer> getCustomerById(Long id) {
        return customerRepository.findById(id);
    }

    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer updateCustomer(Long id, Customer updatedCustomer) {
        if (customerRepository.existsById(id)) {
            updatedCustomer.setId(id);
            return customerRepository.save(updatedCustomer);
        } else {
            // Handle the case where the customer with the given id is not found
            return null;
        }
    }

    public Optional<Customer> getCustomerByAccountId(Long accountId) {
        // Add logic to retrieve the customer that owns the specified account
        // This might involve querying the database based on the account information
        // For demonstration purposes, let's assume the account information is stored in the Customer entity
        return customerRepository.findByAccountId(accountId);
    }
}


