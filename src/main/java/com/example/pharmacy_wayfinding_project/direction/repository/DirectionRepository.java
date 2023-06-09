package com.example.pharmacy_wayfinding_project.direction.repository;

import com.example.pharmacy_wayfinding_project.direction.entity.Direction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DirectionRepository extends JpaRepository<Direction, Long> {
}
