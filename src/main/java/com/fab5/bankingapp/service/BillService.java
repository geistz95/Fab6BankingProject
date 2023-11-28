package com.fab5.bankingapp.service;

import com.fab5.bankingapp.enums.TransactionStatus;
import com.fab5.bankingapp.exceptions.NotFoundExceptions.ModelNotFoundExceptions.AccountNotFoundException;
import com.fab5.bankingapp.exceptions.NotFoundExceptions.ModelNotFoundExceptions.BillNotFoundException;
import com.fab5.bankingapp.exceptions.NotFoundExceptions.ModelNotFoundExceptions.CustomerNotFoundException;
import com.fab5.bankingapp.model.Account;
import com.fab5.bankingapp.model.Bill;
import com.fab5.bankingapp.model.Customer;
import com.fab5.bankingapp.repository.AccountRepository;
import com.fab5.bankingapp.repository.BillRepository;

import com.fab5.bankingapp.repository.CustomerRepository;

import com.fab5.bankingapp.validation.IDValidation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BillService implements IDValidation<BillNotFoundException, AccountNotFoundException> {

    @Autowired
    private BillRepository billRepository;
   @Autowired
    private AccountRepository accountRepository;
   @Autowired
    private CustomerRepository customerRepository;

    @Override
    public void verifyID1(Long id) throws BillNotFoundException {
        Optional<Bill> checkBill = billRepository.findById(id);
        if(checkBill.isEmpty()){
            throw new BillNotFoundException(id);
        }
    }

    @Override
    public void verifyID2(Long id) throws AccountNotFoundException {
        Optional<Account> checkAccount = accountRepository.findById(id);
        if(checkAccount.isEmpty()){
            throw new AccountNotFoundException(id);
        }
    }

    public void verifyIDCustomer(Long id) throws CustomerNotFoundException {
        Optional<Customer> checkCustomer = customerRepository.findById(id);
        if(checkCustomer.isEmpty()){
            throw new CustomerNotFoundException(id);
        }
    }

    public Optional<Bill> getBillsById (Long id){
        return billRepository.findById(id);
    }
    public Iterable<Bill> getAllBillsFromAccountId(Long id){
        verifyID2(id);
        return billRepository.findBillsByAccountId(id);
    }
    public Iterable<Bill> getAllBillsFromCustomerId(Long customer_id){
        verifyIDCustomer(customer_id);
        return billRepository.findBillsByCustomerId(customer_id);
    }
    public void createBill(Long account_id, Bill bill){
        verifyID2(account_id);
        Optional<Account> account = accountRepository.findById(account_id);
        Customer customer = account.get().getCustomer();
        bill.setCustomer(customer);
        bill.setAccount(account.get());
        bill.setStatus(TransactionStatus.PENDING);
        billRepository.save(bill);
    }
    public void updateBill(Bill bill, Long id){
        verifyID1(id);
        Bill originalBill = billRepository.findById(id).get();
        originalBill.setStatus(bill.getStatus());
        originalBill.setPayee(bill.getPayee());
        originalBill.setNickName(bill.getNickName());
        originalBill.setCreation_date(bill.getCreation_date());
        originalBill.setPayment_date(bill.getPayment_date());
        originalBill.setRecurring_date(bill.getRecurring_date());
        originalBill.setUpcoming_payment_date(bill.getUpcoming_payment_date());
        originalBill.setPayment_amount(bill.getPayment_amount());
        billRepository.save(originalBill);

    }
    public Iterable<Bill> getAllBills(){
        return billRepository.findAll();
    }
    public void deleteBillById(Long id){
        verifyID1(id);
        billRepository.deleteById(id);
    }

}
