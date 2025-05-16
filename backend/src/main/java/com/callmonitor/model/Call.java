package com.callmonitor.model;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "calls")  // avoid conflict with SQL keyword 'call'
public class Call {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String status;

    @Column(name = "start_time", nullable = false)
    private LocalDateTime startTime;

    @OneToMany(mappedBy = "call", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<CallParticipant> participants;

    @OneToMany(mappedBy = "call", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<CallMetric> metrics;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public List<CallParticipant> getParticipants() {
        return participants;
    }

    public void setParticipants(List<CallParticipant> participants) {
        this.participants = participants;
    }

    public List<CallMetric> getMetrics() {
        return metrics;
    }

    public void setMetrics(List<CallMetric> metrics) {
        this.metrics = metrics;
    }
}
