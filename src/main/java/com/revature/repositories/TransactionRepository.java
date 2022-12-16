package com.revature.repositories;

import com.revature.models.Account;
import com.revature.models.Send;
import com.revature.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

    List<Transaction> findByAccount(Account account);
    List<Send> findAllById(int accountId);

}
