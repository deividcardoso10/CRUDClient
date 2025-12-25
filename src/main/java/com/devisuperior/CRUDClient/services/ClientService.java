package com.devisuperior.CRUDClient.services;

import com.devisuperior.CRUDClient.dto.ClientDTO;
import com.devisuperior.CRUDClient.entities.Client;
import com.devisuperior.CRUDClient.repositories.ClientRepository;
import com.devisuperior.CRUDClient.services.exceptions.DatabaseException;
import com.devisuperior.CRUDClient.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;

    @Transactional(readOnly = true)
    public ClientDTO findById(Long id) {
        Client client = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Client not found with id " + id));
        return new ClientDTO(client);

    }

    @Transactional(readOnly = true)
    public Page<ClientDTO> findAll(Pageable pageable) {
        Page<Client> client = repository.findAll(pageable);
        return client.map(c -> new ClientDTO(c));
    }

    @Transactional
    public ClientDTO insert(ClientDTO dto) {
        Client client = new Client();
        dtoToEntity(dto, client);
        client = repository.save(client);
        return new ClientDTO(client);

    }

    @Transactional
    public ClientDTO update(Long id, ClientDTO dto) {
        try{
            Client client = repository.getReferenceById(id);
            dtoToEntity(dto, client);
            client = repository.save(client);
            return new ClientDTO(client);
        }
        catch(EntityNotFoundException e){
            throw new ResourceNotFoundException("Client not found with id " + id);
        }


    }

    @Transactional
    public void delete(Long id) {
        if (!repository.existsById(id)){
            throw new ResourceNotFoundException("Client not found with id " + id);
        }
        try {
            repository.deleteById(id);
        }
        catch (DataIntegrityViolationException e){
            throw new  DatabaseException("falha de integridade referencial");
        }
    }

    private void dtoToEntity(ClientDTO dto, Client client) {
        client.setName(dto.getName());
        client.setCpf(dto.getCpf());
        client.setIncome(dto.getIncome());
        client.setBirthDate(dto.getBirthDate());
        client.setChildren(dto.getChildren());
    }


}