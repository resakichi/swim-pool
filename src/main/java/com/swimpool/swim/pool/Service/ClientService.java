package com.swimpool.swim.pool.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swimpool.swim.pool.Entity.Client;
import com.swimpool.swim.pool.Entity.User;
import com.swimpool.swim.pool.Repository.ClientRepository;
import com.swimpool.swim.pool.Repository.UserRepository;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;
    
    @Autowired
    private UserRepository userRepository;

    public Client getById(Long id){
        return clientRepository.findById(id).get();
    }

    public boolean saveClient(Client client, User user){
        return false;
    }
}
