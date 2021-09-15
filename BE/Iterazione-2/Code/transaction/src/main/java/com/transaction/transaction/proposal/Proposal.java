package com.transaction.transaction.proposal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;




@Entity
@Table(name="proposal")
@Setter
@Getter
public class Proposal {
    @Id
    Integer id;
    
    String name;
    String description;
    String email;
    String recipient;

    @Column(columnDefinition = "ENUM('CONFIRMED', 'DECLINED', PENDING)")
    @Enumerated(EnumType.STRING)
    StatusProposal status;


    // constructors
    public Proposal() {
    }


    public Proposal(Integer id, String name, String description, String email, String recipient, StatusProposal status) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.email = email;
        this.recipient = recipient;
        this.status = status;
    }

   
   
}

