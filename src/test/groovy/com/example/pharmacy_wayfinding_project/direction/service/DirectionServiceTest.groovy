package com.example.pharmacy_wayfinding_project.direction.service

import com.example.pharmacy_wayfinding_project.api.dto.DocumentDto
import com.example.pharmacy_wayfinding_project.direction.repository.DirectionRepository
import com.example.pharmacy_wayfinding_project.pharmacy.dto.PharmacyDto
import com.example.pharmacy_wayfinding_project.pharmacy.service.PharmacySearchService
import spock.lang.Specification

class DirectionServiceTest extends Specification {

    private PharmacySearchService pharmacySearchService = Mock()
    private DirectionRepository directionRepository = Mock()
    private Base62Service base62Service = Mock()

    private DirectionService directionService = new DirectionService(
            pharmacySearchService, directionRepository, base62Service)

    private List<PharmacyDto> pharmacyList

    def setup() {
        pharmacyList = new ArrayList<>()
        pharmacyList.addAll(
                PharmacyDto.builder()
                        .id(1L)
                        .pharmacyName("씨유 서귀포주공점")
                        .pharmacyAddress("주소1")
                        .latitude(33.26245019)
                        .longitude(126.5597307)
                        .build(),
                PharmacyDto.builder()
                        .id(2L)
                        .pharmacyName("세븐일레븐 서귀포동문점")
                        .pharmacyAddress("주소2")
                        .latitude(33.24882037)
                        .longitude(126.5672914)
                        .build()
        )

    }

    def "buildDirectionList - 결과 값이 거리 순으로 정렬이 되는지 확인"() {
        given:
        def addressName = "제주특별자치도 서귀포시 중앙로 85";
        double inputLatitude = 33.25217239;
        double inputLongitude = 126.5609179;

        def documentDto = DocumentDto.builder()
                .addressName(addressName)
                .latitude(inputLatitude)
                .longitude(inputLongitude)
                .build()

        when:
        pharmacySearchService.searchPharmacyDtoList() >> pharmacyList

        def results = directionService.buildDirectionList(documentDto)

        then:
        results.size() == 2
        results.get(0).targetPharmacyName == "세븐일레븐 서귀포동문점"
        results.get(1).targetPharmacyName == "씨유 서귀포주공점"
    }

    def "buildDirectionList - 정해진 반경 10 km 내에 검색이 되는지 확인"() {
        given:
        pharmacyList.add(
                PharmacyDto.builder()
                        .id(3L)
                        .pharmacyName("세븐일레븐 서귀포색달점")
                        .pharmacyAddress("주소3")
                        .latitude(33.25778666)
                        .longitude(126.4134715)
                        .build())

        def addressName = "제주특별자치도 서귀포시 중앙로 85";
        double inputLatitude = 33.25217239;
        double inputLongitude = 126.5609179;

        def documentDto = DocumentDto.builder()
                .addressName(addressName)
                .latitude(inputLatitude)
                .longitude(inputLongitude)
                .build()

        when:
        pharmacySearchService.searchPharmacyDtoList() >> pharmacyList

        def results = directionService.buildDirectionList(documentDto)

        then:
        results.size() == 2
        results.get(0).targetPharmacyName == "세븐일레븐 서귀포동문점"
        results.get(1).targetPharmacyName == "씨유 서귀포주공점"
    }
}
