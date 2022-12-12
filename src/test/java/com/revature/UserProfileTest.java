package com.revature;

import com.revature.models.User;
import com.revature.repositories.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import java.util.Optional;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserProfileTest {
    private UserRepository userRepositoryMock;


    @Before

    public void setup(){
        userRepositoryMock = mock(UserRepository.class);

    }

    @Test
    public void testFindByEmail(){

        User user = new User();
        user.setId(1);
        user.setEmail("A");
        user.setAddress("Washington");
        user.setFirstname("hika");
        user.setLastname("lamu");
        user.setPhone("123");
        user.setPassword("b");


        when(userRepositoryMock.findByEmail("A")).thenReturn(Optional.of(user));

        Assertions.assertNotNull(userRepositoryMock.findByEmail("A"));
    }

    @Test
    public void testFindByEmailFalse(){
        String email = "A";
        when(userRepositoryMock.findByEmail(email)).thenReturn(null);

        Assertions.assertEquals(Optional.empty(),userRepositoryMock.findByEmail("B"));
    }

    @Test
    public void testFindById(){

        User user = new User();
        user.setId(1);
        user.setEmail("A");
        user.setAddress("Washington");
        user.setFirstname("hika");
        user.setLastname("lamu");
        user.setPhone("123");
        user.setPassword("b");

        when(userRepositoryMock.findById(1)).thenReturn(Optional.of(user));

        Assertions.assertNotNull(userRepositoryMock.findById(1));

    }

    @Test
    public void testFindByCredentials(){

        User user = new User();
        user.setId(1);
        user.setEmail("A");
        user.setAddress("Washington");
        user.setFirstname("hika");
        user.setLastname("lamu");
        user.setPhone("123");
        user.setPassword("b");

        when(userRepositoryMock.findByEmailAndPassword("A", "b")).thenReturn(Optional.of(user));

        Assertions.assertNotNull(userRepositoryMock.findByEmailAndPassword("A", "b"));

    }

    @Test
     public void testSaveUser(){

         User user = new User();
         user.setEmail("A");
         user.setAddress("Washington");
         user.setFirstname("hika");
         user.setLastname("lamu");
         user.setPhone("123");
         user.setPassword("b");

         when(userRepositoryMock.save(user)).thenReturn(user);

         Assertions.assertNotNull(userRepositoryMock.save(user));

     }

     @Test
    public void testUpdateUser(){

         User user = new User();
         user.setEmail("C");
         user.setAddress("Maryland");
         user.setPhone("456");
         user.setPassword("C");

         when(userRepositoryMock.save(user)).thenReturn(user);

         Assertions.assertNotNull(userRepositoryMock.save(user));

     }


}
