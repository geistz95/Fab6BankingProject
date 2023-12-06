package com.fab5.bankingapp.service;

import com.fab5.bankingapp.exceptions.NotFoundExceptions.ModelNotFoundExceptions.AccountNotFoundException;
import com.fab5.bankingapp.exceptions.NotFoundExceptions.ModelNotFoundExceptions.DepositNotFoundException;
import com.fab5.bankingapp.exceptions.NotFoundExceptions.ModelNotFoundExceptions.P2PTransferNotFoundException;
import com.fab5.bankingapp.exceptions.NotFoundExceptions.ModelNotFoundExceptions.TransferNotFoundException;
import com.fab5.bankingapp.model.Account;
import com.fab5.bankingapp.model.P2PTransfer;
import com.fab5.bankingapp.repository.AccountRepository;
import com.fab5.bankingapp.repository.P2PTransferRepository;
import com.fab5.bankingapp.validation.IDValidation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class P2PTransferService implements IDValidation<DepositNotFoundException,AccountNotFoundException> {

    @Autowired
    private P2PTransferRepository p2pTransferRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionService transactionService;

    private static final Logger logger = LoggerFactory.getLogger(P2PTransfer.class);

    @Override
    public void verifyID1(String message, Long id) throws P2PTransferNotFoundException {
        Optional<P2PTransfer> checkP2PTransfer = p2pTransferRepository.findById(id);
        if (checkP2PTransfer.isEmpty()) {
            throw new P2PTransferNotFoundException(message, id);
        }
    }

    @Override
    public void verifyID2(String message, Long id) throws AccountNotFoundException {
        Optional<Account> checkAccount = accountRepository.findById(id);
        if(checkAccount.isEmpty()){
            logger.info("Error, Deposit ID : " +id+" is not valid");
            throw new AccountNotFoundException(message, id);
        }
    }

    public void createTransfer(Long payer_id, Long payee_id, P2PTransfer transfer){
        verifyID2("error fetching payer_id", payer_id);
        transfer.setGiver(accountRepository.findById(payer_id).get());
        verifyID2("error fetching payee_id", payee_id);
        transfer.setReceiver(accountRepository.findById(payee_id).get());

        logger.info("Creating P2P Transfer");
        transactionService.processTransfer(transfer);
        p2pTransferRepository.save(transfer);
    }

    public void deleteTransfer(Long transfer_id){
        verifyID1("transfer id not found", transfer_id);
        Optional<P2PTransfer> transfer =  p2pTransferRepository.findById(transfer_id);
        if(transfer.isEmpty()){
            throw new TransferNotFoundException("There is no transfer of ID  : "+transfer_id);
        }
        logger.info("Deleting/Undoing P2P Transfer ID : "+transfer_id);
        transactionService.undoTransfer(transfer.get());
        p2pTransferRepository.delete(transfer.get());

    }

    public P2PTransfer getTransfer(Long transfer_id){
        Optional<P2PTransfer> transfer = p2pTransferRepository.findById(transfer_id);
        logger.info("Getting transfer id " + transfer_id);
        if(transfer.isEmpty()){
            logger.error("Error, the ID of " + transfer_id + " is not valid");
            throw new TransferNotFoundException("There is no transfer of ID  : "+transfer_id);
        }
        return transfer.get();
    }

}
