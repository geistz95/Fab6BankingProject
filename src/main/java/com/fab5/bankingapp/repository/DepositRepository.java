package com.fab5.bankingapp.repository;

import com.fab5.bankingapp.model.Deposit;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepositRepository extends CrudRepository<Deposit,Long> {
}
