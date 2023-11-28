package com.fab5.bankingapp.repository;

import com.fab5.bankingapp.model.Deposit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepositRepository extends JpaRepository<Deposit,Long> {
    //Query may be wrong, will check again when Customer is done
    @Query(value = "select d.* from deposit d join account a on d.account_id = a.account_id where a.account_id = ?1", nativeQuery = true)
    List<Deposit> findAllDepositsByAccountID(Long account_id);

    //List<Deposit> findAllDepositsByAccountID(Long accountID);

}
