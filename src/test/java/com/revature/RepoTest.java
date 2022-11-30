package com.revature;

import com.revature.models.Account;
import com.revature.models.Transaction;
import com.revature.models.TransactionType;
import com.revature.models.Transfer;
import com.revature.repositories.AccountRepository;
import com.revature.repositories.TransactionRepository;
import com.revature.repositories.TransferRepository;
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
import static org.mockito.Mockito.when;

@SpringBootTest
@Transactional
public class RepoTest {

   // @Autowired
   // TransactionRepository transactionRepository;

    private AccountRepository mockAccountRepo = mock(AccountRepository.class);

    // set up tests
    @Before
    public void setup () {
       // mockAccountService = mock(AccountService.class);
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

        when(mockAccountRepo.findById(1)).thenReturn(account1);


        Transfer transfer = new Transfer();
        transfer.setId(22);
        transfer.setFromAcctId(1);
        transfer.setToAcctId(2);
        transfer.setId(1);
        transfer.setAmount(500);

        System.out.println(transfer.toString());
     //   mockAccountService = mock(AccountService.class);
    //    when(mockAccountService.createTransfer(transfer)).thenReturn(transfer);

     //   mockAccountService.createTransfer(transfer);

        Assertions.assertEquals(500.0,transfer.getAmount());

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
