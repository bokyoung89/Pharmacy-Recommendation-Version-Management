package com.example.pharmacy_wayfinding_project.pharmacy.service;

import com.example.pharmacy_wayfinding_project.api.dto.DocumentDto;
import com.example.pharmacy_wayfinding_project.api.dto.KakaoApiResponseDto;
import com.example.pharmacy_wayfinding_project.api.service.KakaoAddressSearchService;
import com.example.pharmacy_wayfinding_project.direction.dto.OutputDto;
import com.example.pharmacy_wayfinding_project.direction.entity.Direction;
import com.example.pharmacy_wayfinding_project.direction.service.DirectionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class PharmacyRecommendationService {

    private final KakaoAddressSearchService kakaoAddressSearchService;
    private final DirectionService directionService;

    public List<OutputDto> recommendPharmacyList(String address) { //문자열 기반 주소로 고객이 요청
        //위치기반 데이터로 변환
        KakaoApiResponseDto kakaoApiResponseDto = kakaoAddressSearchService.requestAddressSearch(address);
        //응답값에 대해 validation check
        if(Objects.isNull(kakaoApiResponseDto) || CollectionUtils.isEmpty(kakaoApiResponseDto.getDocumentList())) {
            log.error("[PharmacyRecommendationService recommendPharmacyList fail] Input address: {}", address);
            return Collections.emptyList();
        }
        //변환된 위도, 경도 기준으로 가까운 약국을 찾는다.
        DocumentDto documentDto = kakaoApiResponseDto.getDocumentList().get(0);
        //거리 계산 알고리즘을 통해 가까운 약국 리스트를 리턴한다.
        List<Direction> directionList = directionService.buildDirectionList(documentDto);
        //고객에서 길안내를 한다.
        return directionService.saveAll(directionList)
                .stream()
                .map(this::convertToOutputDto)
                .collect(Collectors.toList());
    }

    private OutputDto convertToOutputDto(Direction direction) {
        return OutputDto.builder()
                .pharmacyName(direction.getTargetPharmacyName())
                .pharmacyAddress(direction.getInputAddress())
                .directionUrl("todo")   // todo
                .roadViewUrl("todo")
                .distance(String.format("%.2f km", direction.getDistance()))
                .build();
    }
}
