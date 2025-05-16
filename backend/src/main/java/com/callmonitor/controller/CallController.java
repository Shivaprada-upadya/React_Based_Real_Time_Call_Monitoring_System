package com.callmonitor.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.callmonitor.model.Call;
import com.callmonitor.service.CallService;

@RestController
@RequestMapping("/api/calls")
public class CallController {
    @Autowired
    private CallService callService;

    @GetMapping
    public ResponseEntity<List<Call>> getAllCalls() {
        return ResponseEntity.ok(callService.getAllCalls());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Call> getCallById(@PathVariable Long id) {
        Optional<Call> call = callService.getCallById(id);
        return call.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Call> createCall(@RequestBody Call call) {
        return ResponseEntity.ok(callService.saveCall(call));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCall(@PathVariable Long id) {
        callService.deleteCall(id);
        return ResponseEntity.noContent().build();
    }
}