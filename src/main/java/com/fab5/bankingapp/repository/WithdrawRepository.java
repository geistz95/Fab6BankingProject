package com.fab5.bankingapp.repository;

import com.fab5.bankingapp.model.Withdraw;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WithdrawRepository extends JpaRepository<Withdraw, Long> {

    Iterable<Withdraw> findByAccount(Long accountId);
}
