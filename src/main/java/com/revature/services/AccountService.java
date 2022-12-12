package com.revature.services;

import com.revature.models.*;
import com.revature.repositories.AccountRepository;
import com.revature.repositories.TransactionRepository;
import com.revature.repositories.TransferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private TransferRepository transferRepository;
    @Autowired
    private UserService userService;


    public Optional<Account> findByUserId(int id) {
        User user = userService.findById(id);
        return accountRepository.findByUser(user);
    }

    public Optional<Account> findById(int id) {
        return accountRepository.findById(id);
    }

    public Account upsertAccount(Account accountToUpsert, String userId) {

        int id = Integer.parseInt(userId);
        User user = userService.findById(id);

        if(accountRepository.existsById(accountToUpsert.getId())) {
            Account account = accountRepository.getById(accountToUpsert.getId());
            account.setBalance(accountToUpsert.getBalance());
            account.setDescription(accountToUpsert.getDescription());
            account.setName(accountToUpsert.getName());
            return accountRepository.saveAndFlush(account);
        } else {
            accountToUpsert.setUser(user);
            accountToUpsert.setCreationDate(Date.from(Instant.now()));
            return accountRepository.save(accountToUpsert);
        }
    }

    public List<Transaction> getAllTransactions(int accountId) {
        Account account = accountRepository.getById(accountId);
        return transactionRepository.findByAccount(account);
    }

    // gets all accounts of a given user id
    public List<Account> getAllAccounts(int userId) {
        User user = userService.findById(userId);
        return accountRepository.findAllByUser(user);
    }


    public Transaction upsertTransaction(int accountId, Transaction transactionToUpsert) {

            Account account = accountRepository.getById(accountId);

            if(transactionToUpsert.getType() == TransactionType.Expense) {
                account.setBalance(account.getBalance() - transactionToUpsert.getAmount());
            } else if (transactionToUpsert.getType() == TransactionType.Income) {
                account.setBalance(account.getBalance() + transactionToUpsert.getAmount());
            }
            accountRepository.saveAndFlush(account);
            transactionToUpsert.setAccount(account);
            return transactionRepository.save(transactionToUpsert);
    }


    // create a new transfer

    public Transfer createTransfer(Transfer transfer) {

        // get account objects
        Account fromAccount = accountRepository.getById(transfer.getFromAcctId());
        Account toAccount = accountRepository.getById(transfer.getToAcctId());

        //alec - just added a catch to prevent people from adding negative amounts?
        if(transfer.getAmount() >0){
            // do math
            double fromBalance = fromAccount.getBalance() - transfer.getAmount();
            double toBalance = toAccount.getBalance() + transfer.getAmount();

            // set new associated account balances
            fromAccount.setBalance(fromBalance);
            toAccount.setBalance(toBalance);

            //save both accounts
            accountRepository.saveAndFlush(fromAccount);
            accountRepository.saveAndFlush(toAccount);

            // save transfer object
            transferRepository.saveAndFlush(transfer);

            // return the originating account
            return transfer;

        }
        else {
            return null;
        }



    }

    public List<Send> getAllSends(int accountId) {
        Account account = accountRepository.getById(accountId);
        //System.out.println(transactionRepository.findAllById(account));

        // filter if receviver !senderId
        return transactionRepository.findAllById(account);
    }

}
