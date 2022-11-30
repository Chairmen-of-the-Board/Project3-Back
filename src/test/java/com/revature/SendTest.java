package com.revature;

import com.revature.models.*;
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
public class SendTest {

    AccountService mockAccountService;
    @Autowired
    TransactionRepository transactionRepository;

    // set up tests
    @Before
    public void setup () {

        mockAccountService = mock(AccountService.class);

    }

    //tests createsend in service
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


}
