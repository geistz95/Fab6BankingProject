package com.fab5.bankingapp.repository;

import com.fab5.bankingapp.model.Bill;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillRepository  extends CrudRepository<Bill, Long> {
    Iterable<Bill> findByAccountId(Long accountId);
    Iterable<Bill> findByCustomerId(Long customerId);
}
