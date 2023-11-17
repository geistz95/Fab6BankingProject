package com.fab5.bankingapp.repository;

<<<<<<< HEAD
<<<<<<< HEAD
import com.fab5.bankingapp.enums.AccountType;
=======
>>>>>>> development
import com.fab5.bankingapp.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
=======

import com.fab5.bankingapp.enums.AccountType;
import com.fab5.bankingapp.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
>>>>>>> development
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
<<<<<<< HEAD
<<<<<<< HEAD
public interface AccountRepository extends JpaRepository<Account, Long> {

    List<Account> findByCustomerId(Long customerId);




=======
public interface AccountRepository extends CrudRepository<Account, Long> {
>>>>>>> development
=======
public interface AccountRepository extends JpaRepository<Account, Long> {
    List<Account> findByCustomerId(Long customerId);
>>>>>>> development
}


