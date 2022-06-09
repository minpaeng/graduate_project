package com.minpaeng.graduate_project.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface PersonalRepository extends JpaRepository<Personal, Long> {
    List<Personal> findPersonalsByGroupIdAndTimestamp(Long groupId, LocalDateTime timeStamp);
}
