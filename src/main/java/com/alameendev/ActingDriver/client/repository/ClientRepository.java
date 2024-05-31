package com.alameendev.ActingDriver.client.repository;

import com.alameendev.ActingDriver.client.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client,Long> {
}
