package com.kata.bankaccount;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by h.akakpo on 15/11/2017.
 */
public class BankAccountRecord {

   private String id;
   private String typeOfRecord;
   private BigDecimal amount;
   private Date operationDate;

    public BankAccountRecord(String id,String typeOfRecord,BigDecimal amount,Date operationDate){
        this.id=id;
        this.typeOfRecord=typeOfRecord;
        this.amount = amount;
        this.operationDate = operationDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTypeOfRecord() {
        return typeOfRecord;
    }

    public void setTypeOfRecord(String typeOfRecord) {
        this.typeOfRecord = typeOfRecord;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Date getOperationDate() {
        return operationDate;
    }

    public void setOperationDate(Date operationDate) {
        this.operationDate = operationDate;
    }

    public String toString(){
        return new StringBuilder().append("Date :").append(operationDate).append(" Amount : ")
                .append(amount).append(" Type :").append(typeOfRecord).append("\n").toString();
    }
}
