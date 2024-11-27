package com.swimpool.swim.pool.Entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "auth_data")
public class User implements UserDetails{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, name = "login")
    private String login;
    
    @Column(nullable = false, name = "password")
    @JsonIgnore
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "role")
    private UserRole role;

    @Column(nullable = false, name = "name")
    private String name;

    @Column(unique = true, nullable = false, name = "phone")
    private String phone;

    @Column(unique = true, nullable = false, name = "email")
    private String email;

    @OneToMany(mappedBy = "user",
                fetch = FetchType.LAZY,
                cascade = CascadeType.ALL)
    List<Order> orders = new ArrayList<>();

    public User(String login, String password, UserRole role, String name, String phone, String email) {
        this.login = login;
        this.password = password;
        this.role = role;
        this.name = name;
        this.phone = phone;
        this.email = email;
    }


    public User(){}


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }


    @Override
    public String getUsername() {
        return login;
    }

    public void addOrder(Order order){
        orders.add(order);
        order.setUser(this);
    }

    public void removeOrder(Order order){
        orders.remove(order);
        order.setUser(null);
    }

    @Override
    public String toString() {
        return "{\"id\" : \"" + id + "\", \"name\" : \"" + name + "\", \"phone\" : \"" + phone + "\", \"email\" : \"" + email + "\"}";
    }

    
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name){
        this.name = name;
    }


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone){
        this.phone = phone;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public UserRole getRole(){
        return role;
    }
}
