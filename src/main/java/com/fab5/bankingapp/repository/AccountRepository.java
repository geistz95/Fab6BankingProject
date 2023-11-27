package com.fab5.bankingapp.repository;

import com.fab5.bankingapp.enums.AccountType;
import com.fab5.bankingapp.model.Account;
import com.fab5.bankingapp.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


import com.fab5.bankingapp.enums.AccountType;
import com.fab5.bankingapp.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

      // @Query(value = "SELECT a.* FROM account a JOIN customer c ON a.customer_id = c.id WHERE c.id = ?1", nativeQuery = true)
    //List<Account> findByCustomerId(Long customerId);

       @Query(value = "SELECT a.* FROM account a JOIN customer ON customer.customer_id = a.customer_id WHERE a.customer_id = ?1", nativeQuery = true)
               //SELECT c.* FROM customer c JOIN account ON c.id = account.customer_id WHERE account.id = ?1
       //List<Customer> findByCustomerId(Long accountId);
       List<Account> findByCustomerId(Long customerId);




}


