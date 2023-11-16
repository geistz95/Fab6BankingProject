package com.fab5.bankingapp.service;

import com.fab5.bankingapp.model.Bill;
import com.fab5.bankingapp.model.Deposit;
import com.fab5.bankingapp.repository.AccountRepository;
import com.fab5.bankingapp.repository.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BillService {
    @Autowired
    private BillRepository billRepository;
    public Optional<Bill> getBillsById (Long id){
        return billRepository.findById(id);
    }
    public Iterable<Bill> getAllBillsFromAccountId(Long id){
        return billRepository.findByAccountId(id);
    }
    public Iterable<Bill> getAllBillsFromCustomerId(Long id){
        return billRepository.findByCustomerId(id);
    }
    public void createBill(Bill bill){
        billRepository.save(bill);
    }
    public void updateBill(Bill bill, Long id){
        Bill originalBill = billRepository.findById(id).get();
        originalBill.setStatus(bill.getStatus());
        originalBill.setPayee(bill.getPayee());
        originalBill.setNickName(bill.getNickName());
        originalBill.setCreation_date(bill.getCreation_date());
        originalBill.setPayment_date(bill.getPayment_date());
        originalBill.setReccuring_date(bill.getReccuring_date());
        originalBill.setUpcoming_payment_date(bill.getUpcoming_payment_date());
        originalBill.setPayment_amount(bill.getPayment_amount());
        originalBill.setAccount_id(bill.getAccount_id());
        billRepository.save(originalBill);

    }
    public void deleteBillById(Long id){
        billRepository.deleteById(id);
    }
}
