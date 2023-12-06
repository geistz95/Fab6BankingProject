package com.fab5.bankingapp.service;

import com.fab5.bankingapp.exceptions.NotFoundExceptions.ModelNotFoundExceptions.AccountNotFoundException;
import com.fab5.bankingapp.exceptions.NotFoundExceptions.ModelNotFoundExceptions.CustomerNotFoundException;
import com.fab5.bankingapp.model.Account;
import com.fab5.bankingapp.model.Customer;
import com.fab5.bankingapp.repository.AccountRepository;
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


    @Autowired
    private AccountRepository accountRepository;



    @Override
    public void verifyID1(String message, Long id) throws CustomerNotFoundException {
        Optional<Customer> checkCustomer = customerRepository.findById(id);
        if(checkCustomer.isEmpty()){
            throw new CustomerNotFoundException(message, id);
        }
    }

    @Override
    public void verifyID2(String message, Long id) throws AccountNotFoundException {
        Optional<Account> checkAccount = accountRepository.findById(id);
        if(checkAccount.isEmpty()){
            throw new AccountNotFoundException(message, id);
        }

    }

    public List<Customer> getAllCustomers() {
        return (List<Customer>) customerRepository.findAll();
    }

    // Get customer by ID
    public Optional<Customer> getCustomerById(Long customerId) {
        verifyID1("error fetching customer",customerId);
        return customerRepository.findById(customerId);
    }

    // Create a new customer
    public Customer createCustomer(Customer customer) {
        // You might want to validate data before saving
        return customerRepository.save(customer);
    }
    public Customer updateCustomer(Long id, Customer updatedCustomer) {
        verifyID1("Error", id);
        return customerRepository.findById(id)
                .map(existingCustomer -> {
                    existingCustomer.setFirstName(updatedCustomer.getFirstName());
                    existingCustomer.setLastName(updatedCustomer.getLastName());
                    existingCustomer.setAddresses(updatedCustomer.getAddresses());
                    // Update other fields as needed
                    return customerRepository.save(existingCustomer);
                })
                .orElse(null); // Handle the case where the customer with the given id is not found
    }
    public void deleteCustomer(Long id) {
        verifyID1("Customer does not exist", id);
        customerRepository.deleteById(id);
    }
    public Iterable<Customer> getCustomerByAccountId(Long id){
        verifyID2("error fetching customers accounts" ,id);
        return customerRepository.findCustomerByAccountId(id);

    }

}


