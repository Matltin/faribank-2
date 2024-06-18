package ir.ac.kntu.transaction;

import ir.ac.kntu.util.Calendar;

import java.io.Serializable;
import java.util.Date;

public class Transaction implements Serializable {

    private String fNameDestination;
    private String lNameDestination;
    private Date date;
    private String formatDate;
    private String destAccNO;
    private String sourceAccountNO;
    private TransactionType transactionType;
    private String followupNumber;    // شناره پیگیری

    public Transaction(String fNameDestination, String lNameDestination,
                       String destAccNO, String sourceAccountNO, TransactionType transactionType) {
        this.fNameDestination = fNameDestination;
        this.lNameDestination = lNameDestination;
        this.date = Calendar.getDate();
        this.formatDate = Calendar.getDateFormat(date);
        this.destAccNO = destAccNO;
        this.sourceAccountNO = sourceAccountNO;
        this.followupNumber = getRandom();
        this.transactionType = transactionType;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public Date getDate() {
        return date;
    }

    private String getRandom() {
        int max = 9999, min = 1000;
        int number = min + (int) (Math.random() * ((max - min) + 1));
        return String.valueOf(number);
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "followupNumber='" + followupNumber + '\'' +
                ", transactionType=" + transactionType +
                ", sourceAccountNO='" + sourceAccountNO + '\'' +
                ", destinationAccountNO='" + destAccNO + '\'' +
                ", formatDate='" + formatDate + '\'' +
                ", lastNameDestination='" + lNameDestination + '\'' +
                ", firstNameDestination='" + fNameDestination + '\'' +
                '}';
    }
}
