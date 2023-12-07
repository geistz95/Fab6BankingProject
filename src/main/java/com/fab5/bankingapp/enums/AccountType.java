package com.fab5.bankingapp.enums;


import com.fab5.bankingapp.model.Deposit;
import com.fab5.bankingapp.model.P2PTransfer;
import com.fab5.bankingapp.model.Withdraw;

public enum AccountType {
    // This will specify what type of account it is, Checking, Savings, etc.


         Savings("SAVINGS", Deposit.class),
         Credit("CREDIT", Withdraw.class),
        Cheque("CHEQUE", P2PTransfer.class),
        JointAccount("JOINT_ACCOUNT", Deposit.class),
        Rewards("REWARD", P2PTransfer.class);

         private final String accountType;

         private final Class<TransactionType>[] activities;

    AccountType(String accountType, Class<?> activities) {
        this.accountType = accountType;
        this.activities = new Class[]{activities};
    }

    public String getAccountType() {
        return accountType;
    }

    public Class<?>[] getActivities(){
        return activities;
    }
}
