package com.transaction.transaction.transaction;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="transaction")
@Setter
@Getter
public class Transaction {

    @Id
    Integer id;

    String user1;
    String user2;

    String description;

    String info;

    public Transaction() {
    }

    public Transaction(Integer id, String user1, String user2, String description, String info) {
        this.id = id;
        this.user1 = user1;
        this.user2 = user2;
        this.description = description;
        this.info = info;
    }




    
}
