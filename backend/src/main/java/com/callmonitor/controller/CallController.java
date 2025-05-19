package com.callmonitor.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.callmonitor.model.Call; // import this
import com.callmonitor.service.CallService;

@RestController
@RequestMapping("/api/calls")
public class CallController {
    @Autowired
    private CallService callService;

    // Pagination-enabled call list endpoint
    @GetMapping
    public ResponseEntity<Page<Call>> getAllCalls(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<Call> callsPage = callService.getCallsPage(page, size);
        return ResponseEntity.ok(callsPage);
    }

    @GetMapping("/history")
 public List<Call> getEndedCalls() {
   return callRepository.findByStatus("ENDED");
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
