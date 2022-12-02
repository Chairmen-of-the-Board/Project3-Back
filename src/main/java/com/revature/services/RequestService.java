package com.revature.services;

import com.revature.models.Request;
import com.revature.repositories.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@Service
public class RequestService {

    @Autowired
    private RequestRepository requestRepository;

    public Request upsertRequest(Request inputRequest) {
        if(requestRepository.existsById(inputRequest.getId())) {
            Request request = requestRepository.getById(inputRequest.getId());
            request.setStatus(inputRequest.getStatus());
            return requestRepository.saveAndFlush(request);
        } else {
            inputRequest.setCreationDate(Date.from(Instant.now()));
            return requestRepository.save(inputRequest);
        }
    }

    public List<Request> getIncoming(int id){
        return requestRepository.findAllByTargetId(id);
    }

    public List<Request> getOutgoing(int id){
        return requestRepository.findAllByRequestAccId(id);
    }

}
