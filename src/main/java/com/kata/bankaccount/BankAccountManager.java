package com.kata.bankaccount;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by h.akakpo on 15/11/2017.
 */
public class BankAccountManager {

    public BigDecimal computeBankAccountBalance(BankAccount bankAccount) {

        double debits = bankAccount.getBankAccountRecords()
                .stream()
                .filter(record -> IconstantBankAccount.DEBIT.equals(record.getTypeOfRecord()))
                .mapToDouble(record -> record.getAmount().doubleValue())
                .sum();

        double credits = bankAccount.getBankAccountRecords()
                .stream()
                .filter(record -> IconstantBankAccount.CREDIT.equals(record.getTypeOfRecord()))
                .mapToDouble(rec -> rec.getAmount().doubleValue())
                .sum();

        return BigDecimal.valueOf(credits - debits);
    }

    public BigDecimal deposit(BankAccount bankAccount, BigDecimal amount) {
        return recordTransaction(bankAccount, amount, IconstantBankAccount.CREDIT);
    }

    public BigDecimal withdraw(BankAccount bankAccount, BigDecimal amount) {
        return recordTransaction(bankAccount, amount, IconstantBankAccount.DEBIT);
    }

    public String statment(BankAccount bankAccount,String typeOfRecord) {
        return bankAccount.print(typeOfRecord);
    }
    private BigDecimal recordTransaction(BankAccount bankAccount,
                                         BigDecimal amount, String typeOfRecord) {

        int newId = bankAccount.getBankAccountRecords().size() + 1;
        BankAccountRecord accountRecord = new BankAccountRecord(String.valueOf(newId),typeOfRecord,amount, new Date());


        bankAccount.getBankAccountRecords().add(accountRecord);

        return computeBankAccountBalance(bankAccount);
    }
}
