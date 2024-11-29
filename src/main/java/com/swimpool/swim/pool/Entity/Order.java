package com.swimpool.swim.pool.Entity;

//import java.sql.LocalDateTime;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
    private LocalDateTime date;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private User user;

    public Order(){}

    public Order(Long id, LocalDateTime date, User user) {
        this.id = id;
        this.date = date;
        this.user = user;
    }

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public void setDate(LocalDateTime date){
        this.date = date;
    }

    public LocalDateTime getDate(){
        return date;
    }

    public void setUser(User user){
        this.user = user;
    }

    public User getUser(){
        return user;
    }

    @Override
    public String toString() {
        return "\"id\" = \"" + id + "\", \"date\" = \"" + date + "\", \"user\" = " + user.toString() ;
    }
}
