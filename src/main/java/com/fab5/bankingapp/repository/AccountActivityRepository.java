package com.fab5.bankingapp.repository;

import com.fab5.bankingapp.model.AccountActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AccountActivityRepository extends JpaRepository<AccountActivity, Long> {

//    @Query(value = "SELECT x.* FROM accountactivity x JOIN account a ON x.account_id = a.account_id WHERE a.account_id = ?1")
    @Query(value = "SELECT x.* FROM account_activity x JOIN Account a ON x.account_id = a.account_id WHERE x.activity_id = ?1", nativeQuery = true)
    //@Query(value = "SELECT x FROM AccountActivity x JOIN Account a ON x.account_id = a.id WHERE a.id = ?1")


    List<AccountActivity> findByAccountId(Long accountId);
//    @Query(value = "SELECT x.* FROM accountactivity x JOIN account a ON x.account_id = a.account_id WHERE a.account_id = ?1")



}
