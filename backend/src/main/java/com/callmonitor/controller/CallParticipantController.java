package com.callmonitor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.callmonitor.model.CallParticipant;
import com.callmonitor.service.CallParticipantService;

@RestController
@RequestMapping("/api/participants")
public class CallParticipantController {

    @Autowired
    private CallParticipantService service;

    @GetMapping
    public ResponseEntity<List<CallParticipant>> getAll() {
        return ResponseEntity.ok(service.getAllParticipants());
    }

    @PostMapping
    public ResponseEntity<CallParticipant> create(@RequestBody CallParticipant participant) {
        return ResponseEntity.ok(service.addParticipant(participant));
    }
}
