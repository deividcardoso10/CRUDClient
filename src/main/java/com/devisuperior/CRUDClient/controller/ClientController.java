package com.devisuperior.CRUDClient.controller;

import com.devisuperior.CRUDClient.dto.ClientDTO;
import com.devisuperior.CRUDClient.repositories.ClientRepository;
import com.devisuperior.CRUDClient.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/clients")
public class ClientController {

    @Autowired
    private ClientRepository repository;

    @Autowired
    private ClientService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<ClientDTO> findById(@PathVariable Long id) {
        ClientDTO dto = service.findById(id).getBody();
        return ResponseEntity.ok().body(dto);

    }

}
