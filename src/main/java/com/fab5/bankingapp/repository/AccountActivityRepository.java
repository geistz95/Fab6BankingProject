package com.fab5.bankingapp.repository;

import com.fab5.bankingapp.model.AccountActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AccountActivityRepository extends JpaRepository<AccountActivity, Long> {

   // Optional<AccountActivity> findByAccountId(Long accountId);

    @Query(value = "SELECT x.* FROM accountactivity x JOIN account a ON x.account_id = a.account_id WHERE a.account_id = ?1")
    List<AccountActivity> findByAccountId(Long accountId);


}
