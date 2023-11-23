package com.fab5.bankingapp.repository;

import com.fab5.bankingapp.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
   // Optional<Customer> findById(Long accountId);;
   @Query(value = "SELECT c.* FROM customer c JOIN account a ON c.id = a.customer_id WHERE a.id = :accountId", nativeQuery = true)


   Iterable<Customer> findCustomerByAccountId(Long accountId);
}
