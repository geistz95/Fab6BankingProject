package com.fab5.bankingapp.service;

import com.fab5.bankingapp.exceptions.AccountNotFoundException;
import com.fab5.bankingapp.exceptions.CustomerNotFoundException;
import com.fab5.bankingapp.exceptions.DepositNotFoundException;
import com.fab5.bankingapp.exceptions.NoSuchElementFoundException;
import com.fab5.bankingapp.model.Account;
import com.fab5.bankingapp.model.Customer;
import com.fab5.bankingapp.model.Deposit;
import com.fab5.bankingapp.repository.AccountRepository;
import com.fab5.bankingapp.repository.CustomerRepository;
import org.apache.velocity.exception.ResourceNotFoundException;
import com.fab5.bankingapp.validation.IDValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService implements IDValidation<AccountNotFoundException, CustomerNotFoundException> {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public void verifyID1(Long id) throws AccountNotFoundException {
        Optional<Account> checkAccount = accountRepository.findById(id);
        if(checkAccount.isEmpty()){
            throw new AccountNotFoundException(id);
        }
    }

    @Override
    public void verifyID2(Long id) throws CustomerNotFoundException {
        Optional<Customer> checkCustomer = customerRepository.findById(id);
        if(checkCustomer.isEmpty()){
            throw new CustomerNotFoundException(id);
        }
    }

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
            accountToUpdate.setName(newAccount.getName());
            accountToUpdate.setNickname(newAccount.getNickname());

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
