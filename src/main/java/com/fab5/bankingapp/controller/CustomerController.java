package com.fab5.bankingapp.controller;
import com.fab5.bankingapp.exceptions.NotFoundExceptions.DataNotFoundExceptions.NoCustomersException;
import com.fab5.bankingapp.response.CustomerResponse;
import com.fab5.bankingapp.model.Customer;
import com.fab5.bankingapp.service.CustomerService;;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);
    public void checkIfCustomersExist(String message) throws NoCustomersException {
        List<Customer> checkCustomers = customerService.getAllCustomers();
        if (checkCustomers.isEmpty()) {
            throw new NoCustomersException("error fetching customers");
        }
    }
    @GetMapping("/customers")
    public ResponseEntity<Object> getAllCustomers() {
        checkIfCustomersExist("error fetching customers");
        logger.info("Request received: Getting All Customers");
        List<Customer> customers = customerService.getAllCustomers();
        logger.info("All Customers Gotten Successfully");
        return CustomerResponse.getAllCustomersBuilder(HttpStatus.OK, customers);
    }

    @GetMapping("/customers/{id}")
    public ResponseEntity<Object> getCustomerById(@PathVariable Long id) {
        logger.info("Request received: Getting Customer By Id");
        Optional<Customer> customer = customerService.getCustomerById(id);
        if (customer.isPresent()) {
            logger.info("Customer Gotten Successfully");
            return CustomerResponse.getCustomerBuilder(HttpStatus.OK, customer.get());
        } else {
            logger.warn("Customer Not Found");
            return CustomerResponse.getCustomerBuilder(HttpStatus.NOT_FOUND, null);
        }
    }

    @PostMapping("/customers")
    public ResponseEntity<Object> createCustomer(@Valid @RequestBody Customer customer) {
        logger.info("Request received: Creating Customer");
        Customer createdCustomer = customerService.createCustomer(customer);
        logger.info("Customer Created Successfully");
        return CustomerResponse.createdCustomerBuilder(HttpStatus.CREATED, createdCustomer);
    }

    @PutMapping("/customers/{id}")
    public ResponseEntity<Object> updateCustomer(@PathVariable Long id, @RequestBody Customer updatedCustomer) {
        logger.info("Request received: Updating Customer");
        Customer updated = customerService.updateCustomer(id, updatedCustomer);
        if (updated != null) {
            logger.info("Updated Customer Successfully");
            return CustomerResponse.updateCustomerBuilder(HttpStatus.ACCEPTED);
        } else {
            logger.warn("Customer Not Found");
            return CustomerResponse.updateCustomerBuilder(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/customers/{id}")
    public ResponseEntity<Object> deleteCustomer(@PathVariable Long id) {
        logger.info("Request received: Deleting Customer");
        customerService.deleteCustomer(id);
        logger.info("Customer Deleted Successfully");
        return CustomerResponse.deleteCustomerBuilder(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/accounts/{accountId}/customer")
    public ResponseEntity<Object> getCustomerByAccounts(@PathVariable Long accountId) {
        logger.info("Request received: Getting Customer By Account Id");
        Iterable<Customer> customers = customerService.getCustomerByAccountId(accountId);
        logger.info("Customers Gotten Successfully");
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

