package com.fab5.bankingapp.repository;

import com.fab5.bankingapp.enums.AccountType;
import com.fab5.bankingapp.model.Account;
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

       @Query(value = "select a.* where account a join customer c on a.customer_id = c.id where c.id = ?1", nativeQuery = true)

    List<Account> findByCustomerId(Long customerId);


}


