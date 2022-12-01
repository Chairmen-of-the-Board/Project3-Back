package com.revature;

import com.revature.models.*;
import com.revature.repositories.TransactionRepository;
import com.revature.services.AccountService;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.mock;

@SpringBootTest
@Transactional
public class RepoTest {

    AccountService mockAccountService;
    Date testDate;
    @Autowired
    TransactionRepository transactionRepository;

    // set up tests
    @Before
    public void setup () {

        mockAccountService = mock(AccountService.class);
        testDate = Date.from(Instant.now());

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

    /*
    * Request needs six things:
    * [ID
    * Account (not user) Requesting ID
    * Amount
    * Description
    * Status
    * Date
    * Recipient USER ID]
    *
    * How do we make sure a request was made?
    * If there was no error, return the request object, otherwise return null
    * Assert not null
    *
    * Test getters
    * [account ID
    * amount
    * description
    * status
    * date
    * recipient user ID]
    *
    * Test status setter
    *
    * How do we make sure a request was deleted?
    * If there was no error, return -1
    * Assert equals -1
    *
    * */

    @Test
    @Order(1)
    @Transactional
    public void request_create_test() {
        Request gotRequest = new Request(1,3,100.00,"For my dog","Pending",testDate);
        Assertions.assertNotNull(gotRequest);
    }

    @Test
    @Order(2)
    @Transactional
    public void request_get_requesterID_test(){
        Request gotRequest = new Request();
        Assertions.assertEquals(1,gotRequest.getRequestAccId());
    }

    @Test
    @Order(3)
    @Transactional
    public void request_get_targetID_test(){
        Request gotRequest = new Request();
        Assertions.assertEquals(3,gotRequest.getTargetID());
    }

    @Test
    @Order(4)
    @Transactional
    public void request_get_amount_test(){
        Request gotRequest = new Request();
        Assertions.assertEquals(100.00,gotRequest.getAmount());
    }

    @Test
    @Order(5)
    @Transactional
    public void request_get_description_test(){
        Request gotRequest = new Request();
        Assertions.assertEquals("For my dog",gotRequest.getDescription());
    }

    @Test
    @Order(6)
    @Transactional
    public void request_get_status_test(){
        Request gotRequest = new Request();
        Assertions.assertEquals("Pending",gotRequest.getStatus());
    }

    @Test
    @Order(7)
    @Transactional
    public void request_get_date_test(){
        Request gotRequest = new Request();
        Assertions.assertEquals(testDate,gotRequest.getCreationDate());
    }

    @Test
    @Order(8)
    @Transactional
    public void request_set_status_test(){
        Request gotRequest = new Request();
        Assertions.assertEquals("Accepted",gotRequest.getStatus());
    }

    @Test
    @Order(9)
    @Transactional
    public void request_delete_test(){

    }
}
