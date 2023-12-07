package com.fab5.bankingapp.repository;

import com.fab5.bankingapp.model.AccountActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AccountActivityRepository extends JpaRepository<AccountActivity, Long> {

    @Query(value = "SELECT x.* FROM account_activity x JOIN Account a ON x.account_id = a.account_id WHERE x.activity_id = ?1", nativeQuery = true)
    List<AccountActivity> findByAccountId(Long accountId);


}
