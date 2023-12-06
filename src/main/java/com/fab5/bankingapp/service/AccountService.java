package com.fab5.bankingapp.service;

import com.fab5.bankingapp.exceptions.NotFoundExceptions.ModelNotFoundExceptions.AccountNotFoundException;
import com.fab5.bankingapp.exceptions.NotFoundExceptions.ModelNotFoundExceptions.CustomerNotFoundException;
import com.fab5.bankingapp.model.Account;
import com.fab5.bankingapp.model.Customer;
import com.fab5.bankingapp.repository.AccountRepository;
import com.fab5.bankingapp.repository.CustomerRepository;
import com.fab5.bankingapp.validation.IDValidation;
import org.springframework.beans.factory.annotation.Autowired;
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
    public void verifyID1(String message, Long id) throws AccountNotFoundException {
        Optional<Account> checkAccount = accountRepository.findById(id);
        if(checkAccount.isEmpty()){
            throw new AccountNotFoundException(message, id);
        }
    }

    @Override
    public void verifyID2(String message, Long id) throws CustomerNotFoundException {
        Optional<Customer> checkCustomer = customerRepository.findById(id);
        if(checkCustomer.isEmpty()){
            throw new CustomerNotFoundException(message, id);
        }
    }

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public Optional<Account> getAccountById(Long accountId) {
        verifyID1("error fetching account",accountId);
        return accountRepository.findById(accountId);
    }

    public List<Account> getAccountsByCustomerId(Long customerId) {
        verifyID2("error fetching account",customerId);
        return accountRepository.findByCustomerId(customerId);
    }

    public Account createAccount(Account account, Long customerId) {
        verifyID2("error fetching creating customers account", customerId);
        Customer editCustomer = customerRepository.findById(customerId).get();
        account.setCustomer(editCustomer);
        return accountRepository.save(account);
    }

//    public Optional<Account> updateAccount(Long accountId, Account newAccount)  {
//       newAccount = accountRepository.findById(accountId).get();
//        accountRepository.save(newAccount);
//       return
//    }


    public Optional<Account> updateAccount(Long accountId, Account newAccount) {
        verifyID1("Error", accountId);
        Optional<Account> existingAccount = accountRepository.findById(accountId);

        if (existingAccount.isPresent()) {
            Account accountToUpdate = existingAccount.get();

            accountToUpdate.setBalance(newAccount.getBalance());
            accountToUpdate.setType(newAccount.getType());
            accountToUpdate.setRewards(newAccount.getRewards());
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
        verifyID1("Account does not exist", accountId);
        Optional<Account> account = accountRepository.findById(accountId);
        account.ifPresent(accountRepository::delete);
        return account;
    }

}
