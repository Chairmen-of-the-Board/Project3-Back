package com.revature.controllers;

import com.revature.annotations.Authorized;
import com.revature.models.Account;
import com.revature.models.Send;
import com.revature.models.Transaction;
import com.revature.models.Transfer;
import com.revature.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/account")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:3000", "http://angular-app-frontend-sdfgsfdgsfgs.s3-website-us-west-1.amazonaws.com"}, allowCredentials = "true")
public class AccountController {

    @Autowired
    private AccountService accountService;

    // gets one certain account, of a given account id
    @Authorized
    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccount(@PathVariable("id") int accountId) {
        Optional<Account> optional = accountService.findById(accountId);

        if(!optional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(optional.get());
    }




    @Authorized
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Account> createAccount(@RequestBody Account account, @RequestHeader("Current-User") String userId) {
        return ResponseEntity.ok(accountService.upsertAccount(account, userId));
    }

    @Authorized
    @GetMapping("/{id}/transaction")
    public ResponseEntity<List<Transaction>> getTransactions(@PathVariable("id") int accountId) {
        return ResponseEntity.ok(accountService.getAllTransactions(accountId));
    }

    // this gets all accounts of a given user id
    @Authorized
    @GetMapping("/{id}/all")
    public ResponseEntity<List<Account>> getAccounts(@PathVariable("id") int userId) {
        return ResponseEntity.ok(accountService.getAllAccounts(userId));
    }


    @Authorized
    @PostMapping(value = "/{id}/transaction", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Transaction> addTransaction(@PathVariable("id") int accountId, @RequestBody Transaction transaction) {
        return new ResponseEntity<>(accountService.upsertTransaction(accountId, transaction), HttpStatus.CREATED);
    }

    //adding new transfer
    @Authorized
    @PostMapping(value = "/transfer", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Transfer> addTransfer(@RequestBody Transfer transfer) {
        return new ResponseEntity<>(accountService.createTransfer(transfer), HttpStatus.CREATED);
    }
    // get all transfers
    @Authorized
    @GetMapping("/{id}/transfer")
    public ResponseEntity<List<Transfer>> getTransfers(@PathVariable("id") int accountId) {
        return ResponseEntity.ok(accountService.getAllTransfers(accountId));
    }


    //@Authorized
    @GetMapping("/{id}/received")
    public ResponseEntity<List<Send>> getReceived(@PathVariable ("id") int transferId) {
        //System.out.println(ResponseEntity.ok(accountService.getAllTransfers(transferId)));
        return ResponseEntity.ok(accountService.getAllSends(transferId));
    }

}
