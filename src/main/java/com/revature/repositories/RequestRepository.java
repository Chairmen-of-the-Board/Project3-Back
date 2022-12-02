package com.revature.repositories;

import com.revature.models.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface RequestRepository extends JpaRepository<Request, Integer> {

    List<Request> findAllByRequestAccId(Integer requestAccId);

    List<Request> findAllByTargetId(Integer targetId);


}
