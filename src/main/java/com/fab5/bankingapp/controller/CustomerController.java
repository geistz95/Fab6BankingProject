package com.fab5.bankingapp.controller;

import com.fab5.bankingapp.exceptions.AccountNotFoundException;
import com.fab5.bankingapp.exceptions.CustomerNotFoundException;
import com.fab5.bankingapp.model.Customer;
import com.fab5.bankingapp.service.CustomerService;;
import com.fab5.bankingapp.validation.IDValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CustomerController implements IDValidation<CustomerNotFoundException, AccountNotFoundException> {

    @Autowired
    private CustomerService customerService;

    @Override
    public void verifyID(Long id) throws CustomerNotFoundException {
        Optional<Customer> checkCustomer = customerService.getCustomerById(id);
        if(checkCustomer.isEmpty()) {
            throw new CustomerNotFoundException(id);
        }
    }

    @GetMapping("/customers")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    // Get customer by ID
    @GetMapping("/customers/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
        Optional<Customer> customer = customerService.getCustomerById(id);
        return customer.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Create a new customer
    @PostMapping("/customers")
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        Customer createdCustomer = customerService.createCustomer(customer);
        return new ResponseEntity<>(createdCustomer, HttpStatus.CREATED);
    }

    // Update an existing customer
    @PutMapping("/customers/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody Customer updatedCustomer) {
        Customer updated = customerService.updateCustomer(id, updatedCustomer);
        if (updated != null) {
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/customers/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping ("/accounts/{accountId}/customer")
    public Iterable<Customer> getCustomerByAccounts(@PathVariable Long accountId) {
        return customerService.getCustomerByAccountId(accountId);

    }
}

/*uncomment only with below mentioned changes to account service and account repository
    @GetMapping("/accounts/{accountId}")
    public ResponseEntity<Customer> getAllCustomersByAccountId(@PathVariable Long accountId) {
        Customer customer = accountService.getCustomerByAccountId(accountId);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }*/

//this has to be added in account service
/*public Customer getCustomerByAccountId(long Id) {
    // Find the account by ID
    Account account = accountRepository.findById(Id);

    // Check if the account exists
    if (account != null) {
        // Retrieve the associated customer
        Customer customer = account.getCustomer();
        return customer;
    } else {
        // Handle the case when the account with the given ID is not found
        return null;
    } }*/

    /* this has to be added in account repository
     Account findById(long accountId);
     */

