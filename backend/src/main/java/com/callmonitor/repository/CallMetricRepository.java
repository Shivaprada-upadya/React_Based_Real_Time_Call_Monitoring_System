package com.callmonitor.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.callmonitor.model.CallMetric;

public interface CallMetricRepository extends JpaRepository<CallMetric, Long> {
}
