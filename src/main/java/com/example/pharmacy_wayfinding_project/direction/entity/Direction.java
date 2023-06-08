package com.example.pharmacy_wayfinding_project.direction.entity;

import com.example.pharmacy_wayfinding_project.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "direction")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Direction extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 고객
    private String inputAddress;
    private double inputLatitude;
    private double inputLongitude;

    // 안전상비의약품판매업소
    private String targetPharmacyName;
    private String targetAddress;
    private double targetLatitude;
    private double targetLongitude;
    
    // 고객 주소와 안전상비의약품판매업소 사이의 거리
    private double distance;
}
