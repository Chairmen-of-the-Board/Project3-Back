package com.revature.controllers;

import com.revature.annotations.Authorized;
import com.revature.models.Account;
import com.revature.models.Request;
import com.revature.models.User;
import com.revature.services.RequestService;
import com.revature.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;



@RestController
@RequestMapping("/request")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:3000"}, allowCredentials = "true")
public class RequestController {

    @Autowired
    private RequestService requestService;

    @Autowired
    private UserService userService;

    @Authorized
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Request> createRequest(@RequestBody Request request){
        Optional<User> user = userService.findByEmail(request.getTargetEmail());
        Request newRequest = new Request(0, request.getRequestAccId(), user.get().getId(), request.getTargetEmail(), request.getAmount(),
                            request.getDescription(), request.getStatus(), request.getCreationDate());
        return ResponseEntity.ok(requestService.upsertRequest(newRequest));
    }


    // get user by email
    @Authorized
    @GetMapping("/user/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable("email") String userEmail) {
        Optional<User> optional = userService.findByEmail(userEmail);

        if(!optional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(optional.get());
    }

    @Authorized
    @GetMapping("/{id}/incoming")
    public ResponseEntity<List<Request>> getUserIncoming(@PathVariable("id") int id){
        return ResponseEntity.ok(requestService.getIncoming(id));
    }

    @Authorized
    @GetMapping("/{id}/outgoing")
    public ResponseEntity<List<Request>> getUserOutgoing(@PathVariable("id") int id){
        return ResponseEntity.ok(requestService.getOutgoing(id));
    }

}
