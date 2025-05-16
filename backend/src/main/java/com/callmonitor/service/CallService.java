package com.callmonitor.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.callmonitor.model.Call;
import com.callmonitor.repository.CallRepository;

@Service
public class CallService {

    @Autowired
    private CallRepository callRepository;

    public List<Call> getAllCalls() {
        return callRepository.findAll();
    }

    public Optional<Call> getCallById(Long id) {
        return callRepository.findById(id);
    }

    public Call saveCall(Call call) {
        return callRepository.save(call);
    }

    public void deleteCall(Long id) {
        callRepository.deleteById(id);
    }
}
