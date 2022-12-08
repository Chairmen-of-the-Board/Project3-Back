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
    private Integer targetId;
    private Double amount;
    private String description;
    private String status;
    private Date creationDate;

    public Request(Integer requestAccId, Integer targetId, Double amount, String description, String status, Date creationDate) {
        this.requestAccId = requestAccId;
        this.targetId = targetId;
        this.amount = amount;
        this.description = description;
        this.status = status;
        this.creationDate = creationDate;
    }
}
