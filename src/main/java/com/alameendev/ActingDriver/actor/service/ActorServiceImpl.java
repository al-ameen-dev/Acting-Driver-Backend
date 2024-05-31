package com.alameendev.ActingDriver.actor.service;

import com.alameendev.ActingDriver.actor.dto.ActorProfileResponseDTO;
import com.alameendev.ActingDriver.actor.dto.ActorProfileUpdateDTO;
import com.alameendev.ActingDriver.actor.entity.Actor;
import com.alameendev.ActingDriver.actor.entity.AvailabilityStatus;
import com.alameendev.ActingDriver.actor.repository.ActorRepository;
import com.alameendev.ActingDriver.exceptions.actor.ActorNotFoundException;
import com.alameendev.ActingDriver.exceptions.user.UserNotFoundException;
import com.alameendev.ActingDriver.user.entity.User;
import com.alameendev.ActingDriver.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ActorServiceImpl implements ActorService{

    private final ActorRepository actorRepository;
    private final UserService userService;
    private final ModelMapper modelMapper;

    @Override
    public List<Actor> retrieveAllActors() {
        return actorRepository.findAll();
    }

    @Override
    public Actor retrieveActorById(Long id) {
        return actorRepository.findById(id).orElseThrow(()->new ActorNotFoundException(id));
    }

    @Override
    public Actor retrieveActorByUser(User user) {
        return actorRepository.findById(user.getUserId()).orElseThrow(()->new ActorNotFoundException(user.getUserId()));
    }

    @Override
    public Actor saveActor(Actor actor) {
        return actorRepository.save(actor);
    }

    @Override
    public ActorProfileResponseDTO updateProfileWithId(Long id, ActorProfileUpdateDTO body) {
        Actor actor = actorRepository.findByUserUserId(id).orElseThrow(()->new UserNotFoundException(id));
        actor.setAvailabilityStatus(body.getAvailabilityStatus());
        actor.setCredentials(body.getCredentials());
        actor.setDriverExperience(body.getDriverExperience());
        actor.setName(body.getName());
        actorRepository.save(actor);
        return modelMapper.map(actor,ActorProfileResponseDTO.class);
    }

    @Override
    public AvailabilityStatus updateAvailabilityStatusWithId(Long id, AvailabilityStatus status) {
        Actor actor = actorRepository.findByUserUserId(id).orElseThrow(()->new UserNotFoundException(id));
        actor.setAvailabilityStatus(status);
        return status;
    }
}
