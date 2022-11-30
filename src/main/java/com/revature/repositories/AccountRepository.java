package com.revature.repositories;

import com.revature.models.Account;
import com.revature.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface AccountRepository extends JpaRepository<Account, Integer> {

    Optional<Account> findByUser(User user);

    // gets all accounts of a user
    List<Account> findAllByUser(User user);
}
