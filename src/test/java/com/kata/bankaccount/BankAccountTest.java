package com.kata.bankaccount;

import org.junit.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by h.akakpo on 15/11/2017.
 */
public class BankAccountTest {

    private BankAccount userAccount;
    private BankAccountManager bankAccountManager;
    private BankAccountRecord firstRecord;
    private BankAccountRecord secondRecord;
    private BankAccountRecord thirdRecord;

    private String allTransactionExpected;
    private String allDebitTransactionExpected;
    private String allCreditTransactionExpected;

    @Before
    public void setUp() throws Exception {

        bankAccountManager = new BankAccountManager();
        userAccount = new BankAccount();
        userAccount.setBankAccountId("XXX01");
        userAccount.setCustomerId("AAAA01");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        firstRecord= new BankAccountRecord("01",IconstantBankAccount.DEBIT,BigDecimal.valueOf(50.0),Date.from(LocalDate.parse("14/11/2017", formatter).atStartOfDay(ZoneId.systemDefault()).toInstant()));
        secondRecord= new BankAccountRecord("02",IconstantBankAccount.CREDIT,BigDecimal.valueOf(5000.0),Date.from(LocalDate.parse("15/11/2017", formatter).atStartOfDay(ZoneId.systemDefault()).toInstant()));
        thirdRecord = new BankAccountRecord("03",IconstantBankAccount.DEBIT,BigDecimal.valueOf(900.0),Date.from(LocalDate.parse("16/11/2017", formatter).atStartOfDay(ZoneId.systemDefault()).toInstant()));

        allTransactionExpected= "Date :Tue Nov 14 00:00:00 CET 2017 Amount : 50.0 Type :DEBIT\n" +
                                "Date :Wed Nov 15 00:00:00 CET 2017 Amount : 5000.0 Type :CREDIT\n" +
                                "Date :Thu Nov 16 00:00:00 CET 2017 Amount : 900.0 Type :DEBIT\n";

        allDebitTransactionExpected= "Date :Tue Nov 14 00:00:00 CET 2017 Amount : 50.0 Type :DEBIT\n" +
                                     "Date :Thu Nov 16 00:00:00 CET 2017 Amount : 900.0 Type :DEBIT\n";

        allCreditTransactionExpected= "Date :Wed Nov 15 00:00:00 CET 2017 Amount : 5000.0 Type :CREDIT\n";


    }


    @Test
    public void should_be_Positive_for_actual_value_when_first_deposit() {
        BigDecimal actual = bankAccountManager.deposit(userAccount, BigDecimal.valueOf(50));
        assertThat(actual).isPositive();
        assertThat(actual).isEqualTo(BigDecimal.valueOf(50.0));
    }

    @Test
    public void should_be_negative_for_actual_value_when_first_withdraw() {
        BigDecimal balance = bankAccountManager.withdraw(userAccount, BigDecimal.valueOf(50));
        assertThat(balance).isNegative();
        assertThat(balance).isEqualTo(BigDecimal.valueOf(-50.0));
    }

    @Test
    public void should_be_accumulate_transaction_for_actual_value_when_several_deposits() {
        bankAccountManager.deposit(userAccount, BigDecimal.valueOf(50));
        BigDecimal actual = bankAccountManager.deposit(userAccount, BigDecimal.valueOf(100));
        assertThat(actual).isPositive();
        assertThat(actual).isEqualTo(BigDecimal.valueOf(150.0));
    }

    @Test
    public void should_be_return_difference_between_credits_and_debit_transaction() {
        bankAccountManager.deposit(userAccount, BigDecimal.valueOf(50));
        BigDecimal actual = bankAccountManager.withdraw(userAccount, BigDecimal.valueOf(100));
        assertThat(actual).isNegative();
        assertThat(actual).isEqualTo(BigDecimal.valueOf(-50.0));
    }

    @Test
    public void should_print_all_transactions() {
        List<BankAccountRecord> bankAccountRecords = new ArrayList<BankAccountRecord>();
        bankAccountRecords.add(firstRecord);
        bankAccountRecords.add(secondRecord);
        bankAccountRecords.add(thirdRecord);


        userAccount.setBankAccountRecords(bankAccountRecords);
        String actual=bankAccountManager.statment(userAccount,IconstantBankAccount.EMPTY_STRING);
        assertThat(actual).isEqualTo(allTransactionExpected);
    }

    @Test
    public void should_print_all_debit_transactions() {
        List<BankAccountRecord> bankAccountRecords = new ArrayList<BankAccountRecord>();
        bankAccountRecords.add(firstRecord);
        bankAccountRecords.add(secondRecord);
        bankAccountRecords.add(thirdRecord);


        userAccount.setBankAccountRecords(bankAccountRecords);
        String actual=bankAccountManager.statment(userAccount,IconstantBankAccount.DEBIT);
        assertThat(actual).isEqualTo(allDebitTransactionExpected);
    }
    @Test
    public void should_print_all_credit_transactions() {
        List<BankAccountRecord> bankAccountRecords = new ArrayList<BankAccountRecord>();
        bankAccountRecords.add(firstRecord);
        bankAccountRecords.add(secondRecord);
        bankAccountRecords.add(thirdRecord);


        userAccount.setBankAccountRecords(bankAccountRecords);
        String actual=bankAccountManager.statment(userAccount,IconstantBankAccount.CREDIT);
        assertThat(actual).isEqualTo(allCreditTransactionExpected);
    }

}