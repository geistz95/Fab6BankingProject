package com.fab5.bankingapp.repository;

import com.fab5.bankingapp.model.Withdraw;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WithdrawRepository extends CrudRepository<Withdraw, Long> {

    Iterable<Withdraw> findByAccount(Long accountId);
}
