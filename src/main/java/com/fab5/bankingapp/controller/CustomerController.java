package com.fab5.bankingapp.controller;

import com.fab5.bankingapp.model.Customer;
import com.fab5.bankingapp.service.CustomerService;;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequestMapping("/customers")
@RestController
public class CustomerController {
    @Autowired
    private CustomerService customerService;
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