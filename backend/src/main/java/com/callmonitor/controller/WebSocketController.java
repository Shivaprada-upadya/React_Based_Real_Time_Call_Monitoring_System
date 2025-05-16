package com.callmonitor.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {

    @MessageMapping("/ping")
    public void handlePing() {
        // Can respond to client messages here
    }
}
