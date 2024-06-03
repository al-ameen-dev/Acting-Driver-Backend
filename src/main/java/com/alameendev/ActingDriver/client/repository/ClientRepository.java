package com.alameendev.ActingDriver.client.repository;

import com.alameendev.ActingDriver.client.entity.Client;
import com.alameendev.ActingDriver.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client,Long> {

    Optional<Client> findByUserUserId(Long id);


    Optional<Client> findByUser(User user);
}
