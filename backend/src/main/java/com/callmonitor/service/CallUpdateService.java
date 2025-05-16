package com.callmonitor.service;

import java.util.List;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.callmonitor.model.Call;
import com.callmonitor.model.CallMetric;

@Service
public class CallUpdateService {

    private final SimpMessagingTemplate messagingTemplate;

    public CallUpdateService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void broadcastCallUpdates(List<Call> calls) {
        messagingTemplate.convertAndSend("/topic/calls", calls);
    }

    public void broadcastMetricUpdate(CallMetric metric) {
        messagingTemplate.convertAndSend("/topic/metrics", metric);
    }
}
