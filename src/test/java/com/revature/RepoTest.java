package com.revature;

import com.revature.models.*;
import com.revature.repositories.RequestRepository;
import com.revature.repositories.TransactionRepository;
import com.revature.services.AccountService;
import com.revature.services.RequestService;
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
    RequestRepository mockRequestRepository;
    @Autowired
    RequestService requestService;

    // set up tests
    @Before
    public void setup () {

        mockAccountService = mock(AccountService.class);
        testDate = Date.from(Instant.now());
        mockRequestRepository = mock(RequestRepository.class);

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
    @Transactional
    public void request_create_test() {
        Request testRequest = new Request(0,1,3,100.00,"For my dog","Pending",testDate);
        Request gotRequest = requestService.upsertRequest(testRequest);
        Assertions.assertEquals(testRequest.getCreationDate(), gotRequest.getCreationDate());
    }

    @Test
    @Transactional
    public void request_set_status_test(){
        Request testRequest = new Request(0,1,3,100.00,"For my dog","Pending",testDate);
        Request gotRequest = requestService.upsertRequest(testRequest);
        testRequest = gotRequest;
        testRequest.setStatus("Accepted");
        gotRequest = requestService.upsertRequest(testRequest);
        Assertions.assertEquals("Accepted",gotRequest.getStatus());
    }

    @Test
    @Transactional
    public void request_get_outgoing_test(){
        Request testRequest = new Request(0,1,3,100.00,"For my dog","Pending",testDate);
        Request gotRequest = requestService.upsertRequest(testRequest);
        testRequest = new Request(0,1,5,200.00,"For my dog","Pending",testDate);
        gotRequest = requestService.upsertRequest(testRequest);
        List<Request> testList = requestService.getOutgoing(1);
        Assertions.assertEquals(2, testList.size());
    }

    @Test
    @Transactional
    public void request_get_incoming_test(){
        Request testRequest = new Request(0,1,3,100.00,"For my dog","Pending",testDate);
        Request gotRequest = requestService.upsertRequest(testRequest);
        testRequest = new Request(0,5,3,200.00,"For my dog","Pending",testDate);
        gotRequest = requestService.upsertRequest(testRequest);
        List<Request> testList = requestService.getIncoming(3);
        Assertions.assertEquals(2,testList.size());
    }
}
