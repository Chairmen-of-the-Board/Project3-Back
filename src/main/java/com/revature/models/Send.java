package com.revature.models;

public class Send extends Transfer{
    private int senderAcc;

    public Send(int id, int fromAcctId, int toAcctId, double amount, int senderName) {
        super(id, fromAcctId, toAcctId, amount);
        this.senderAcc = senderAcc;
    }

    public Send(int senderAcc) {
        this.senderAcc = senderAcc;
    }

    public Send() {

    }

    public int getSenderAcc() {
        return senderAcc;
    }

    public void setSenderAcc(int senderAcc) {
        this.senderAcc = senderAcc;
    }
}
