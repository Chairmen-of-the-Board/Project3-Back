package com.revature;

import com.revature.models.*;
import com.revature.repositories.RequestRepository;
import com.revature.repositories.TransactionRepository;
import com.revature.repositories.UserRepository;
import com.revature.services.AccountService;
import com.revature.services.RequestService;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
@Transactional
public class ProjectTests {

    private UserRepository userRepositoryMock;
    AccountService mockAccountService;
    Date testDate;
    @Autowired
    TransactionRepository transactionRepository;
    RequestRepository mockRequestRepository;
    @Autowired
    RequestService requestService;

    @Autowired
    AccountService accountService;

    /////////// TEST SETUP ///////////////
    @Before
    public void setup () {

        mockAccountService = mock(AccountService.class);
        testDate = Date.from(Instant.now());
        mockRequestRepository = mock(RequestRepository.class);
        userRepositoryMock = mock(UserRepository.class);
    }


    /////////// TRANSACTION TESTS ///////////////
    @Test
    @Transactional
    public void find_by_account_test () {
        Account account = new Account( "accountname", "description", null, null);
        account.setId(3);
        List<Transaction> list = transactionRepository.findByAccount(account);
        System.out.println(list);
        Assertions.assertNotNull(list);
    }

    /////////// REQUESTS TESTS ///////////////
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


    /////////// TRANSFER TESTS ///////////////
    @Test
    @Transactional
    public void get_all_transfers_test(){

        List<Transfer> transferList = accountService.getAllTransfers(1);
        for (Transfer t : transferList)
            System.out.println(t.toString());
        Assertions.assertTrue(transferList.size() > 0);
    }

    @Test
    @Transactional
    public void create_transfer_test() {

        Transfer newTransfer = new Transfer(0, 1, 2, 100);
        Transfer createdTransfer = accountService.createTransfer((newTransfer));
        Assertions.assertEquals(newTransfer.getAmount(), createdTransfer.getAmount());
    }


    /////////// SEND TESTS ///////////////
    @Test
    @Transactional
    public void get_received_test(){

        Transfer transfer1 = new Transfer(1,1,2,200.00);
        Transfer transfer2 = new Transfer(2,13,2,400.00);

        mockAccountService = mock(AccountService.class);
        mockAccountService.createTransfer(transfer1);
        mockAccountService.createTransfer(transfer2);
        mockAccountService.getAllSends(2);

        //Assertions.ass




    }


    /////////// USER PROFILE TESTS ///////////////
    @org.junit.Test
    public void testFindByEmail(){

        User user = new User();
        user.setId(1);
        user.setEmail("A");
        user.setAddress("Washington");
        user.setFirstname("hika");
        user.setLastname("lamu");
        user.setPhone("123");
        user.setPassword("b");


        when(userRepositoryMock.findByEmail("A")).thenReturn(Optional.of(user));

        Assertions.assertNotNull(userRepositoryMock.findByEmail("A"));
    }

    @org.junit.Test
    public void testFindByEmailFalse(){
        String email = "A";
        when(userRepositoryMock.findByEmail(email)).thenReturn(null);

        Assertions.assertEquals(Optional.empty(),userRepositoryMock.findByEmail("B"));
    }

    @org.junit.Test
    public void testFindById(){

        User user = new User();
        user.setId(1);
        user.setEmail("A");
        user.setAddress("Washington");
        user.setFirstname("hika");
        user.setLastname("lamu");
        user.setPhone("123");
        user.setPassword("b");

        when(userRepositoryMock.findById(1)).thenReturn(Optional.of(user));

        Assertions.assertNotNull(userRepositoryMock.findById(1));

    }

    @org.junit.Test
    public void testFindByCredentials(){

        User user = new User();
        user.setId(1);
        user.setEmail("A");
        user.setAddress("Washington");
        user.setFirstname("hika");
        user.setLastname("lamu");
        user.setPhone("123");
        user.setPassword("b");

        when(userRepositoryMock.findByEmailAndPassword("A", "b")).thenReturn(Optional.of(user));

        Assertions.assertNotNull(userRepositoryMock.findByEmailAndPassword("A", "b"));

    }

    @org.junit.Test
    public void testSaveUser(){

        User user = new User();
        user.setEmail("A");
        user.setAddress("Washington");
        user.setFirstname("hika");
        user.setLastname("lamu");
        user.setPhone("123");
        user.setPassword("b");

        when(userRepositoryMock.save(user)).thenReturn(user);

        Assertions.assertNotNull(userRepositoryMock.save(user));

    }

    @org.junit.Test
    public void testUpdateUser(){

        User user = new User();
        user.setEmail("C");
        user.setAddress("Maryland");
        user.setPhone("456");
        user.setPassword("C");

        when(userRepositoryMock.save(user)).thenReturn(user);

        Assertions.assertNotNull(userRepositoryMock.save(user));

    }


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