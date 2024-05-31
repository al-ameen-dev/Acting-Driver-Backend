package com.alameendev.ActingDriver.actor.service;

import com.alameendev.ActingDriver.actor.dto.ActorProfileResponseDTO;
import com.alameendev.ActingDriver.actor.dto.ActorProfileUpdateDTO;
import com.alameendev.ActingDriver.actor.entity.Actor;
import com.alameendev.ActingDriver.actor.entity.AvailabilityStatus;
import com.alameendev.ActingDriver.user.entity.User;

import java.util.List;

public interface ActorService {

    List<Actor> retrieveAllActors();

    Actor retrieveActorById(Long id);

    Actor retrieveActorByUser(User user);

    Actor saveActor(Actor actor);

    ActorProfileResponseDTO updateProfileWithId(Long id, ActorProfileUpdateDTO body);

    AvailabilityStatus updateAvailabilityStatusWithId(Long id, AvailabilityStatus status);
}
