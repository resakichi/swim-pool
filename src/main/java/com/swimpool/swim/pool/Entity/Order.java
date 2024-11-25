package com.swimpool.swim.pool.Entity;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders")
public class Order {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Timestamp date;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    public Order(){}

    public Order(Long id, Timestamp date, Client client) {
        this.id = id;
        this.date = date;
        this.client = client;
    }

    public Long getId(){
        return id;
    }

    public Timestamp getDate(){
        return date;
    }

    public Client getClient(){
        return client;
    }
}
