package com.example.sonarduty.repository;

import com.example.sonarduty.model.ItemType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ItemTypeRepository extends JpaRepository<ItemType, Long> {

    public Optional<ItemType> findByName(String name);
}
