package com.kata.bankaccount;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by h.akakpo on 15/11/2017.
 */
public class BankAccount {

   private String bankAccountId;
   private String customerId;
   private List<BankAccountRecord> bankAccountRecords;

   public BankAccount(){
       bankAccountRecords = new ArrayList<BankAccountRecord>();
   }

   public BankAccount(String bankAccountId,String customerId,List<BankAccountRecord> bankAccountRecords){
       this.bankAccountId=bankAccountId;
       this.customerId=customerId;
       this.bankAccountRecords=bankAccountRecords;

   }


    public String getBankAccountId() {
        return bankAccountId;
    }

    public void setBankAccountId(String bankAccountId) {
        this.bankAccountId = bankAccountId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public List<BankAccountRecord> getBankAccountRecords() {
        return bankAccountRecords;
    }

    public void setBankAccountRecords(List<BankAccountRecord> bankAccountRecords) {
        this.bankAccountRecords = bankAccountRecords;
    }

    public String print(String typeOfRecord) {
        StringBuilder sb = new StringBuilder();
        if(StringUtils.isBlank(typeOfRecord)){
            for(BankAccountRecord bankAccountRecord : bankAccountRecords){
                sb.append(bankAccountRecord.toString());
            }
         }
         else
            getStatments(sb,typeOfRecord);

        return sb.toString();
    }

    private void getStatments(StringBuilder sb,String typeOfRecord) {
        for(BankAccountRecord bankAccountRecord: bankAccountRecords){
            if(typeOfRecord.equalsIgnoreCase(bankAccountRecord.getTypeOfRecord()))
                sb.append(bankAccountRecord.toString());
        }
    }
}
