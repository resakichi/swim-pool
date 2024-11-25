package com.swimpool.swim.pool.Entity;

import java.util.Set;
import java.util.regex.Pattern;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "client_data")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String phone;

    @Column(unique = true, nullable = false)
    private String email;

    @OneToMany(mappedBy = "client")
    private Set<Order> orders;

    @Transient
    private final Pattern VALID_EMAIL_ADDRESS_REGEX = 
    Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    @Transient
    private final Pattern VALID_PHONE_NUMBER_REGEX = 
    Pattern.compile("\\d{10}|(?:\\d{3}-){2}\\d{4}|\\(\\d{3}\\)\\d{3}-?\\d{4}", Pattern.CASE_INSENSITIVE);
    
    public Client() {}

    public Client(Long id, String name, String phone, String email) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }
    public boolean setPhone(String phone) {
        var matcher = VALID_PHONE_NUMBER_REGEX.matcher(phone);
        if (matcher.matches()) {
            this.phone = phone;
            return true;
        }
        return false;
    }

    public String getEmail() {
        return email;
    }
    public boolean setEmail(String email) {
        var matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
        if (matcher.matches()){
            this.email = email;
            return true;
        }
        return false;
    }
}
