package com.fab5.bankingapp.controller;
import com.fab5.bankingapp.response.CustomerResponse;
import com.fab5.bankingapp.model.Customer;
import com.fab5.bankingapp.service.CustomerService;;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/customers")
    public ResponseEntity<Object> getAllCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        return CustomerResponse.getAllCustomersBuilder(HttpStatus.OK, customers);
    }

    // Get customer by ID
    @GetMapping("/customers/{id}")
    public ResponseEntity<Object> getCustomerById(@PathVariable Long id) {
        Optional<Customer> customer = customerService.getCustomerById(id);
        return customer.map(value -> CustomerResponse.getCustomerBuilder(HttpStatus.OK, value))
                .orElseGet(() -> CustomerResponse.getCustomerBuilder(HttpStatus.NOT_FOUND, null));
    }

    // Create a new customer
    @PostMapping("/customers")
    public ResponseEntity<Object> createCustomer(@RequestBody Customer customer) {
        Customer createdCustomer = customerService.createCustomer(customer);
        return CustomerResponse.createdCustomerBuilder(HttpStatus.CREATED, createdCustomer);
    }

    // Update an existing customer
    @PutMapping("/customers/{id}")
    public ResponseEntity<Object> updateCustomer(@PathVariable Long id, @RequestBody Customer updatedCustomer) {
        Customer updated = customerService.updateCustomer(id, updatedCustomer);
        if (updated != null) {
            return CustomerResponse.updateCustomerBuilder(HttpStatus.ACCEPTED);
        } else {
            return CustomerResponse.updateCustomerBuilder(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/customers/{id}")
    public ResponseEntity<Object> deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return CustomerResponse.deleteCustomerBuilder(HttpStatus.NO_CONTENT);
    }
    @GetMapping("/accounts/{accountId}/customer")
    public ResponseEntity<Object> getCustomerByAccounts(@PathVariable Long accountId) {
        Iterable<Customer> customers = customerService.getCustomerByAccountId(accountId);
        return CustomerResponse.getAllCustomersBuilder(HttpStatus.OK, (List<Customer>) customers);
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

