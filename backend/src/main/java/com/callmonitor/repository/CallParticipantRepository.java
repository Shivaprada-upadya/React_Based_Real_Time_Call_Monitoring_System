package com.callmonitor.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.callmonitor.model.CallParticipant;

public interface CallParticipantRepository extends JpaRepository<CallParticipant, Long> {
     List<CallParticipant> findByCallId(Long callId);
}
