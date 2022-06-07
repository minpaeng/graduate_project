package com.minpaeng.graduate_project.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findAppointmentsByGroupIdAndTimestamp(Long groupId, LocalDateTime timeStamp);
}
