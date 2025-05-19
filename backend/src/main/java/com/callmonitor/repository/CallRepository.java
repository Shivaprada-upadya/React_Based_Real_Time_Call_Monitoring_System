package com.callmonitor.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.callmonitor.model.Call;

public interface CallRepository extends JpaRepository<Call, Long> {
    List<Call> findByStatus(String status);

}
