package com.revature.repositories;

import com.revature.models.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface TransferRepository extends JpaRepository<Transfer, Integer> {


}
