package com.transaction.transaction.transaction;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "transaction")
@Setter
@Getter
public class Transaction implements Comparable<Transaction> {

    @Id
    Integer id;

    Integer proposalID;
    String user1;
    String user2;
    String description;
    String info;
    Integer metric;

    public Transaction() {
    }

  

    public Transaction(Integer id, Integer proposalID, String user1, String user2, String description, String info,
            Integer metric) {
        this.id = id;
        this.proposalID = proposalID;
        this.user1 = user1;
        this.user2 = user2;
        this.description = description;
        this.info = info;
        this.metric = metric;
    }



    @Override
    public int compareTo(Transaction o) {

        if (this.getMetric() > o.getMetric())
            return 1;
        else if (this.getMetric() < o.getMetric())
            return -1;
        else
            return 0;
    }

}
