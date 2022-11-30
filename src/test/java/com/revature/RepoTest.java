package com.revature;

import com.revature.models.Account;
import com.revature.models.Transaction;
import com.revature.models.TransactionType;
import com.revature.models.Transfer;
import com.revature.repositories.TransactionRepository;
import com.revature.services.AccountService;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.List;

import static org.mockito.Mockito.mock;

@SpringBootTest
@Transactional
public class RepoTest {

    AccountService mockAccountService;
    @Autowired
    TransactionRepository transactionRepository;

    // set up tests
    @Before
    public void setup () {

        mockAccountService = mock(AccountService.class);

    }

    //tests createtransfer in service
    @Test
    @Transactional
    public void create_transfer_test(){
        Account account1 = new Account( "accountname", "description", null, null);
        account1.setId(1);
        account1.setBalance(1000d);
        Account account2 = new Account( "accountname", "description", null, null);
        account1.setId(2);
        account2.setBalance(1000d);

        Transfer transfer = new Transfer();
        transfer.setFromAcctId(1);
        transfer.setToAcctId(2);
        transfer.setId(1);
        transfer.setAmount(500);

        mockAccountService = mock(AccountService.class);
        mockAccountService.createTransfer(transfer);

        Assertions.assertEquals(500.0, account1.getBalance());

    }



    // finds all transactions of an account
    @Test
    @Transactional
    public void find_by_account_test () {
        Account account = new Account( "accountname", "description", null, null);
        account.setId(3);
        List<Transaction> list = transactionRepository.findByAccount(account);
        System.out.println(list);
        Assertions.assertNotNull(list);
    }

}
