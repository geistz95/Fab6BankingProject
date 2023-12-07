package com.fab5.bankingapp.repository;

import com.fab5.bankingapp.model.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface BillRepository  extends JpaRepository<Bill, Long> {
    @Query(value = "SELECT b.*\n" +
            "FROM bill b\n" +
            "JOIN account a ON b.account_id = a.account_id\n" +
            "WHERE a.account_id = ?1", nativeQuery = true )
    Iterable<Bill> findByAccountId(Long accountId);
    @Query(value = "SELECT b.*\n" +
            "FROM bill b\n" +
            "JOIN account a ON b.account_id = a.account_id\n" +
            "JOIN customer c ON a.customer_id = c.customer_id\n" +
            "WHERE c.customer_id = ?1", nativeQuery = true)

    Iterable<Bill> findByCustomerId(Long customer_Id);

//    Iterable<Bill> findBillsByCustomerId(Long customer_id);
//    Iterable<Bill> findBillsByAccountId(Long accountId);
}
