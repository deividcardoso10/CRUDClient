package com.devisuperior.CRUDClient.repositories;

import com.devisuperior.CRUDClient.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {

}
