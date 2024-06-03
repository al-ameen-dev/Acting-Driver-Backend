package com.alameendev.ActingDriver.actor.repository;

import com.alameendev.ActingDriver.actor.entity.Actor;
import com.alameendev.ActingDriver.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ActorRepository extends JpaRepository<Actor,Long> {
    Optional<Actor> findByUserUserId(Long id);

    Optional<Actor> findByUser(User user);
}
