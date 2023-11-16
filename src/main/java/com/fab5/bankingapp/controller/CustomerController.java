package com.fab5.bankingapp.controller;

import com.fab5.bankingapp.exceptions.CustomerNotFoundException;
import com.fab5.bankingapp.model.Customer;
import com.fab5.bankingapp.service.CustomerService;;
import com.fab5.bankingapp.validation.IDValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequestMapping("/customers")
@RestController
public class CustomerController implements IDValidation<CustomerNotFoundException> {

    @Autowired
    private CustomerService customerService;

    @Override
    public void verifyID(Long id) throws CustomerNotFoundException {
        Optional<Customer> checkCustomer = customerService.getCustomerByAccountId(id);
        if(checkCustomer.isEmpty()) {
            throw new CustomerNotFoundException(id);
        }
    }

    @GetMapping
    public Iterable<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/{id}")
    public Optional<Customer> getCustomerById(@PathVariable Long id) {
        return customerService.getCustomerById(id);
    }

   /* @GetMapping("/accounts/{accountId}/customer")
    public Optional<Customer> getCustomerByAccountId(@PathVariable Long accountId) {
        return customerService.getCustomerByAccountId(accountId);
    }*/

    @PostMapping
    public Customer createCustomer(@RequestBody Customer customer) {
        return customerService.createCustomer(customer);
    }

    @PutMapping("/{id}")
    public Customer updateCustomer(@PathVariable Long id, @RequestBody Customer updatedCustomer) {
        return customerService.updateCustomer(id, updatedCustomer);
    }


}