package com.fab5.bankingapp.repository;

import com.fab5.bankingapp.model.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {
   // Optional<Customer> findById(Long accountId);;
   Iterable<Customer> findCustomerByAccountId(Long accountId);
}
