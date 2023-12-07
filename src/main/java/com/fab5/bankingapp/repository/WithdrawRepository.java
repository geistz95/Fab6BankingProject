package com.fab5.bankingapp.repository;

import com.fab5.bankingapp.model.Withdraw;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface WithdrawRepository extends JpaRepository<Withdraw, Long> {
//    @Query(value = "select w.* where withdrawal w join account a on w.payer_id = a.account_id where a.account_id = ?1", nativeQuery = true)
    @Query(value = "select w.* from withdraw w join account a on w.account_id = a.account_id where a.account_id = ?1", nativeQuery = true)
    Iterable<Withdraw> findByAccount(Long accountId);
}
