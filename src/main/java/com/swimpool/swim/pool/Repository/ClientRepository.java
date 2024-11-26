package com.swimpool.swim.pool.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.swimpool.swim.pool.Entity.Client;


public interface ClientRepository extends JpaRepository<Client, Long>{
    Client findByEmail(String email);
    Client findByPhone(String phone);
}
