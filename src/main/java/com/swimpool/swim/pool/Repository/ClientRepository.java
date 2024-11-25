package com.swimpool.swim.pool.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.swimpool.swim.pool.Entity.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long>{
    Client findByEmail(String email);
    Client findByPhone(String phone);
}
