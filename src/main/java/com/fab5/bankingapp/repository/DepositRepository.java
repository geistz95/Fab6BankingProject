package com.fab5.bankingapp.repository;

import com.fab5.bankingapp.model.Deposit;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepositRepository extends CrudRepository<Deposit,Long> {

    @Query(value = "select d.* where deposit d join customer c on d.payee_id = c.customerid where c.customerid = ?1", nativeQuery = true)
    Iterable<Deposit> findAllDepositsByCustomerID(Long customerid);
}
