package com.fab5.bankingapp.repository;

import com.fab5.bankingapp.model.Deposit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DepositRepository extends JpaRepository<Deposit,Long> {
    //Query may be wrong, will check again when Customer is done
    @Query(value = "select d.* where deposit d join customer c on d.payee_id = c.customerid where c.customerid = ?1", nativeQuery = true)
    Iterable<Deposit> findAllDepositsByCustomerID(Long customerid);

}
