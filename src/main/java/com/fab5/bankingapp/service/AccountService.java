package com.fab5.bankingapp.service;

import com.fab5.bankingapp.model.Account;
import com.fab5.bankingapp.repository.AccountRepository;
import org.apache.velocity.exception.ResourceNotFoundException;
import com.fab5.bankingapp.validation.IDValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;



    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Iterable<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public Optional<Account> getAccountById(Long accountId) {
        return accountRepository.findById(accountId);
    }

    public List<Account> getAccountsByCustomerId(Long customerId) {
        return accountRepository.findByCustomerId(customerId);
    }

    public Account createAccount(Account account, Long customerId) {
        return accountRepository.save(account);
    }

//    public Optional<Account> updateAccount(Long accountId, Account newAccount)  {
//       newAccount = accountRepository.findById(accountId).get();
//        accountRepository.save(newAccount);
//       return
//    }


    public Optional<Account> updateAccount(Long accountId, Account newAccount) {
        Optional<Account> existingAccount = accountRepository.findById(accountId);

        if (existingAccount.isPresent()) {
            Account accountToUpdate = existingAccount.get();

            accountToUpdate.setBalance(newAccount.getBalance());
            accountToUpdate.setId(newAccount.getId());
            accountToUpdate.setCustomer(newAccount.getCustomer());
            accountToUpdate.setType(newAccount.getType());
            accountToUpdate.setRewards(newAccount.getRewards());

            // Save the updated account
            accountRepository.save(accountToUpdate);

            return Optional.of(accountToUpdate);
        } else {
            // Return empty optional if the account with the given ID doesn't exist
            return Optional.empty();
        }
    }



    public Optional<Account> deleteAccount(Long accountId) {
        Optional<Account> account = accountRepository.findById(accountId);
        account.ifPresent(accountRepository::delete);
        return account;
    }
}
