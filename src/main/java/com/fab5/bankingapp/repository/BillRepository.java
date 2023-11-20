package com.fab5.bankingapp.repository;

import com.fab5.bankingapp.model.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BillRepository  extends JpaRepository<Bill, Long> {
    @Query(value = "SELECT bills.*\n" +
            "FROM bills\n" +
            "JOIN accounts ON bills.account_id = accounts.account_id\n" +
            "WHERE accounts.account_id = ?1", nativeQuery = true )
    Iterable<Bill> findByAccountId(Long accountId);
    @Query(value = "SELECT bills.*\n" +
            "FROM bills\n" +
            "JOIN customers ON bills.customer_id = customers.customer_id\n" +
            "WHERE bills.customer_id = ?1", nativeQuery = true)
    Iterable<Bill> findByCustomerId(Long customerId);
}
