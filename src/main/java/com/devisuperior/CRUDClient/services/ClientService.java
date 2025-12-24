package com.devisuperior.CRUDClient.services;

import com.devisuperior.CRUDClient.dto.ClientDTO;
import com.devisuperior.CRUDClient.entities.Client;
import com.devisuperior.CRUDClient.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;

    @Transactional(readOnly = true)
    public ResponseEntity<ClientDTO> findById(Long id) {
        Optional<Client> result = repository.findById(id);
        Client client = result.get();
        return new ResponseEntity<>(new ClientDTO(client), HttpStatus.OK);

    }

    @Transactional(readOnly = true)
    public Page<ClientDTO> findAll(Pageable pageable) {
     Page<Client> client = repository.findAll(pageable);
     return client.map(c -> new ClientDTO(c));
    }


}
