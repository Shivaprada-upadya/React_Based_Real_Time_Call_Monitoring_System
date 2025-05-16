package com.callmonitor.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.callmonitor.model.Call;
import com.callmonitor.model.CallMetric;
import com.callmonitor.model.CallParticipant;
import com.callmonitor.repository.CallParticipantRepository;

@Service
public class CallSimulatorService {

    @Autowired
    private CallUpdateService callUpdateService;

    @Autowired
    private CallParticipantRepository participantRepository;

    private final Random random = new Random();

    @Scheduled(fixedRate = 5000) // every 5 seconds
    public void simulateLiveCalls() {
        List<CallParticipant> participants = participantRepository.findAll();

        if (participants.size() < 2) {
            System.out.println("Not enough participants in DB to simulate calls");
            return;
        }

        List<Call> activeCalls = new ArrayList<>();

        int callCount = random.nextInt(3) + 1; // 1 to 3 active calls
        for (int i = 0; i < callCount; i++) {
            Call call = new Call();
            call.setStartTime(LocalDateTime.now().minusSeconds(random.nextInt(60)));
            call.setStatus("ONGOING");

            // Randomly pick 2 distinct participants
            CallParticipant caller = participants.get(random.nextInt(participants.size()));
            CallParticipant receiver;
            do {
                receiver = participants.get(random.nextInt(participants.size()));
            } while (receiver.equals(caller));

            // Create new CallParticipant instances linked to this call (important for persistence)
            CallParticipant callCaller = new CallParticipant();
            callCaller.setName(caller.getName());
            callCaller.setRole("CALLER");
            callCaller.setCall(call);

            CallParticipant callReceiver = new CallParticipant();
            callReceiver.setName(receiver.getName());
            callReceiver.setRole("RECEIVER");
            callReceiver.setCall(call);

            call.setParticipants(List.of(callCaller, callReceiver));

            // Simulated metrics
            CallMetric metric = new CallMetric();
            metric.setCall(call);
            metric.setTimestamp(LocalDateTime.now());
            metric.setLatency(random.nextFloat() * 500);
            metric.setJitter(random.nextFloat() * 100);
            metric.setPacketLoss(random.nextFloat() * 5);

            call.setMetrics(Collections.singletonList(metric));

            activeCalls.add(call);

            // Broadcast metric update
            callUpdateService.broadcastMetricUpdate(metric);
        }

        // Broadcast call updates
        callUpdateService.broadcastCallUpdates(activeCalls);
    }
}



/*package com.callmonitor.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CallSimulatorService {

    @Autowired
    private CallUpdateService callUpdateService;

    private static final String[] PARTICIPANTS = {
        "Alice", "Bob", "Charlie", "Dave", "Eva"
    };

    private final Random random = new Random();
     
    @Scheduled(fixedRate = 5000) // Run every 5 seconds
    public void simulateLiveCalls() {
        List<Call> activeCalls = new ArrayList<>();

        int callCount = random.nextInt(3) + 1; // 1 to 3 active calls
        for (int i = 0; i < callCount; i++) {
            Call call = new Call();
            call.setStartTime(LocalDateTime.now().minusSeconds(random.nextInt(60)));
            call.setStatus("ONGOING");

            // Random participants
            CallParticipant caller = new CallParticipant();
            caller.setName(PARTICIPANTS[random.nextInt(PARTICIPANTS.length)]);
            caller.setRole("CALLER");
            caller.setCall(call); // set bidirectional relationship

            CallParticipant receiver = new CallParticipant();
            receiver.setName(PARTICIPANTS[random.nextInt(PARTICIPANTS.length)]);
            receiver.setRole("RECEIVER");
            receiver.setCall(call);

            call.setParticipants(Arrays.asList(caller, receiver));

            // Simulated metrics
            CallMetric metric = new CallMetric();
            metric.setCall(call);
            metric.setTimestamp(LocalDateTime.now());
            metric.setLatency(random.nextFloat() * 500); // ms
            metric.setJitter(random.nextFloat() * 100);  // ms
            metric.setPacketLoss(random.nextFloat() * 5); // %

            call.setMetrics(Collections.singletonList(metric));

            activeCalls.add(call);

            // Broadcast the metric
            callUpdateService.broadcastMetricUpdate(metric);
        }

        // Broadcast call updates
        callUpdateService.broadcastCallUpdates(activeCalls);
    }
        
}
*/