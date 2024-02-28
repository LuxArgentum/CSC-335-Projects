//////////////////////////////////////////////////////////////

//  This private inner class models a very minimal BankAccount
//  Do not change this class!

//////////////////////////////////////////////////////////////

import java.text.NumberFormat;

public class BankAccount {

    private String ID;

    private double balance;


    public BankAccount(String initID, double initBalance) {

        ID = initID;

        balance = initBalance;

    }


    public void deposit(double depositAmount) {

        if (depositAmount > 0.0)

            balance = balance + depositAmount;

    }


    public void withdraw(double withdrawalAmount) {

        if ((withdrawalAmount <= balance) && (withdrawalAmount > 0.0))

            balance = balance - withdrawalAmount;

    }


    public String toString() {

        NumberFormat nf = NumberFormat.getCurrencyInstance();

        return nf.format(balance);

    }


    public String getID() {
        return ID;
    }

} // end class BankAccount