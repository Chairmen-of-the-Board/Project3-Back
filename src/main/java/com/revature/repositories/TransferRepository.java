package com.revature.repositories;

import com.revature.models.Account;
import com.revature.models.Send;
import com.revature.models.Transaction;
import com.revature.models.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface TransferRepository extends JpaRepository<Transfer, Integer> {


    List<Transfer> findAllByFromAcctId(int accountId);
    List<Transfer> findAllByToAcctId(int accountId);


}
