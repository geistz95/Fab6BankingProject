package com.fab5.bankingapp.repository;

import com.fab5.bankingapp.model.AccountActivity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AccountActivityRepository extends JpaRepository<AccountActivity, Long> {

    Optional<AccountActivity> findByAccountId(Long accountId);
}
