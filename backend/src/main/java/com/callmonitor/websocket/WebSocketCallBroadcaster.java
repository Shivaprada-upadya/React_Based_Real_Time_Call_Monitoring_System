package com.callmonitor.websocket;

import com.callmonitor.model.Call;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class WebSocketCallBroadcaster {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    public void broadcastCallUpdate(Call call) {
        messagingTemplate.convertAndSend("/topic/call-updates", call);
    }
}
