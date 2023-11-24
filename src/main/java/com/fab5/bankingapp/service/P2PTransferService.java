package com.fab5.bankingapp.service;

import com.fab5.bankingapp.exceptions.AccountNotFoundException;
import com.fab5.bankingapp.exceptions.DepositNotFoundException;
import com.fab5.bankingapp.exceptions.NoSuchElementFoundException;
import com.fab5.bankingapp.exceptions.TransferNotFoundException;
import com.fab5.bankingapp.model.Account;
import com.fab5.bankingapp.model.Deposit;
import com.fab5.bankingapp.model.P2PTransfer;
import com.fab5.bankingapp.repository.AccountRepository;
import com.fab5.bankingapp.repository.P2PTransferRepository;
import com.fab5.bankingapp.validation.IDValidation;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class P2PTransferService implements IDValidation<DepositNotFoundException,AccountNotFoundException> {

    @Autowired
    private P2PTransferRepository p2pTransferRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionService transactionService;
    public void verifyID1(Long id) throws DepositNotFoundException {
    }
    public void verifyID2(Long id) throws AccountNotFoundException {
        Optional<Account> checkAccount = accountRepository.findById(id);
        if(checkAccount.isEmpty()){
            throw new AccountNotFoundException(id);
        }
    }

    public void createTransfer(Long account_id, P2PTransfer transfer){
        verifyID2(account_id);
        transfer.setGiver(accountRepository.findById(account_id).get());
        verifyID2(transfer.getReceiver().getId());

        transactionService.processTransfer(transfer);
        p2pTransferRepository.save(transfer);

    }

    public void deleteTransfer(Long transfer_id){
        Optional<P2PTransfer> transfer =  p2pTransferRepository.findById(transfer_id);
        if(transfer.isEmpty()){
            throw new TransferNotFoundException("There is no transfer of ID  : "+transfer_id);
        }
        transactionService.undoTransfer(transfer.get());
        p2pTransferRepository.delete(transfer.get());

    }


}
