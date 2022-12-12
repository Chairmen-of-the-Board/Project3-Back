package com.revature.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique=true)
    private String email;
    private String password;
    private String firstname;   //new field added
    private String lastname;    //new field added
    private String address;     //new field added
    private String phone;       //new field added

    //new constructor created
    public User(String email, String password, String firstname, String lastname, String address, String phone) {
    }

    public User(String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }
}
