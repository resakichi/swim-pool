package com.swimpool.swim.pool.Service;

import org.springframework.stereotype.Service;

import com.swimpool.swim.pool.Entity.Client;
import com.swimpool.swim.pool.Entity.User;
import com.swimpool.swim.pool.Repository.ClientRepository;
import com.swimpool.swim.pool.Repository.UserRepository;

@Service
public class ClientService {
    private final ClientRepository clientRepository;
    private final UserRepository userRepository;

    public ClientService(ClientRepository clientRepository, UserRepository userRepository) {
        this.clientRepository = clientRepository;
        this.userRepository = userRepository;
    }

    public Client getById(Long id){
        return clientRepository.findById(id).get();
    }

    public boolean saveClient(Client client, User user){
        return false;
    }
}
