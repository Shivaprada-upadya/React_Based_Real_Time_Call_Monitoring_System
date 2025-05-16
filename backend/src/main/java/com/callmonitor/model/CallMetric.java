package com.callmonitor.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "call_metric")
public class CallMetric {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private float latency;

    private float jitter;

    @Column(name = "packet_loss")
    private float packetLoss;

    private LocalDateTime timestamp;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "call_id", nullable = false)
    @JsonBackReference
    private Call call;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public float getLatency() {
        return latency;
    }

    public void setLatency(float latency) {
        this.latency = latency;
    }

    public float getJitter() {
        return jitter;
    }

    public void setJitter(float jitter) {
        this.jitter = jitter;
    }

    public float getPacketLoss() {
        return packetLoss;
    }

    public void setPacketLoss(float packetLoss) {
        this.packetLoss = packetLoss;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Call getCall() {
        return call;
    }

    public void setCall(Call call) {
        this.call = call;
    }
}
