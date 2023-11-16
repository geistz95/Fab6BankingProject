package com.fab5.bankingapp.repository;


import com.fab5.bankingapp.enums.AccountType;
import com.fab5.bankingapp.model.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface AccountRepository extends CrudRepository<Account, Long> {

    List<Account> findByCustomerId(Long customerId);

}