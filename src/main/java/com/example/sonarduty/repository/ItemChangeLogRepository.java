package com.example.sonarduty.repository;

import com.example.sonarduty.model.ItemChangeLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemChangeLogRepository extends JpaRepository<ItemChangeLog, Long> {

}
