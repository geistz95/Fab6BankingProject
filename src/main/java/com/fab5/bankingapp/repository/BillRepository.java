package com.fab5.bankingapp.repository;

import com.fab5.bankingapp.model.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillRepository  extends JpaRepository<Bill, Long> {
    Iterable<Bill> findByAccountId(Long accountId);
    Iterable<Bill> findByCustomerId(Long customerId);
}
