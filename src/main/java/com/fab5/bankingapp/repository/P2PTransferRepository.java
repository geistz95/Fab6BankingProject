package com.fab5.bankingapp.repository;

import com.fab5.bankingapp.model.P2PTransfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface P2PTransferRepository extends JpaRepository<P2PTransfer,Long> {

}
