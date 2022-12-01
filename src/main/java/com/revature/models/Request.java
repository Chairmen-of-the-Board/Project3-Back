package com.revature.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "requests")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer requestAccId;
    private Integer targetID;
    private Double amount;
    private String description;
    private String status;
    private Date creationDate;

    public Request(Integer requestAccId, Integer targetID, Double amount, String description, String status, Date creationDate) {
        this.requestAccId = requestAccId;
        this.targetID = targetID;
        this.amount = amount;
        this.description = description;
        this.status = status;
        this.creationDate = creationDate;
    }
}
