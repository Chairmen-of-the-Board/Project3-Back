package com.revature.services;

import com.revature.dtos.UpdateUser;
import com.revature.exceptions.DuplicateEmailFoundException;
import com.revature.models.User;
import com.revature.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> findById(int id) {
        return userRepository.findById(id);
    }

    public Optional<User> findByCredentials(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password);
    }

    public Optional<User> findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public User save(User user) {
        return userRepository.save(user);
    }



    //Method for updating user info
    public User updateUser(UpdateUser updateUser)
    {
//        User user = userRepository.findByEmailAndPassword(updateUser.getOldemail(), updateUser.getPassword()).get();
        Optional<User> user = userRepository.findById(updateUser.getId());
        if(userRepository.findByEmail(updateUser.getEmail()) == null || user.get().getEmail().equals(updateUser.getEmail()))
        {
            if(updateUser.getEmail() != user.get().getEmail())
            user.get().setEmail(updateUser.getEmail());
        }else {
            throw new DuplicateEmailFoundException("Email already exists");

        }
        user.get().setPassword(updateUser.getPassword());
        user.get().setAddress(updateUser.getAddress());
        user.get().setPhone(updateUser.getPhone());
        return userRepository.save(user.get());
    }


}
