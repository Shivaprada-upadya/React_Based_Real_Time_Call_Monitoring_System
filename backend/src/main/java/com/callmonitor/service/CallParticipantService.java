package com.callmonitor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.callmonitor.model.CallParticipant;
import com.callmonitor.repository.CallParticipantRepository;

@Service
public class CallParticipantService {

    @Autowired
    private CallParticipantRepository repository;

    public List<CallParticipant> getAllParticipants() {
        return repository.findAll();
    }

    public CallParticipant addParticipant(CallParticipant participant) {
        return repository.save(participant);
    }

    public void deleteParticipant(Long id) {
        repository.deleteById(id);
    }
}
