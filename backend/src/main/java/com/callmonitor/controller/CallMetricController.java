package com.callmonitor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.callmonitor.model.CallMetric;
import com.callmonitor.service.CallMetricService;

@RestController
@RequestMapping("/api/metrics")
public class CallMetricController {

    @Autowired
    private CallMetricService service;

    @GetMapping
    public ResponseEntity<List<CallMetric>> getAll() {
        return ResponseEntity.ok(service.getAllMetrics());
    }

    @PostMapping
    public ResponseEntity<CallMetric> create(@RequestBody CallMetric metric) {
        return ResponseEntity.ok(service.addMetric(metric));
    }
}
