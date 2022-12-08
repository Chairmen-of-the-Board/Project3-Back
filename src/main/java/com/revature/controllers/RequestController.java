package com.revature.controllers;

import com.revature.annotations.Authorized;
import com.revature.models.Request;
import com.revature.services.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/request")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:3000"}, allowCredentials = "true")
public class RequestController {

    @Autowired
    private RequestService requestService;

    @Authorized
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Request> createRequest(@RequestBody Request request){
        return ResponseEntity.ok(requestService.upsertRequest(request));
    }

    @Authorized
    @PostMapping("/{id}/incoming")
    public ResponseEntity<List<Request>> getUserIncoming(@PathVariable("id") int id){
        return ResponseEntity.ok(requestService.getIncoming(id));
    }

    @Authorized
    @PostMapping("/{id}/outgoing")
    public ResponseEntity<List<Request>> getUserOutgoing(@PathVariable("id") int id){
        return ResponseEntity.ok(requestService.getOutgoing(id));
    }

}
