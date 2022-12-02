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
