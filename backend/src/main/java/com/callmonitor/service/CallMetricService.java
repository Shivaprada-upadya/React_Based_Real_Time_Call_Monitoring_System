package com.callmonitor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.callmonitor.model.CallMetric;
import com.callmonitor.repository.CallMetricRepository;

@Service
public class CallMetricService {

    @Autowired
    private CallMetricRepository repository;

    public List<CallMetric> getAllMetrics() {
        return repository.findAll();
    }

    public CallMetric addMetric(CallMetric metric) {
        return repository.save(metric);
    }
}
