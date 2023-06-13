package com.example.pharmacy_wayfinding_project.pharmacy.service

import com.example.pharmacy_wayfinding_project.pharmacy.cache.PharmacyRedisTemplateService
import com.example.pharmacy_wayfinding_project.pharmacy.entity.Pharmacy
import com.google.common.collect.Lists
import spock.lang.Specification

class PharmacySearchServiceTest extends Specification {

    private PharmacySearchService pharmacySearchService;

    private final PharmacyRepositoryService pharmacyRepositoryService = Mock()
    private final PharmacyRedisTemplateService pharmacyRedisTemplateService = Mock()

    private List<Pharmacy> pharmacyList

    def setup() {
        pharmacySearchService = new PharmacySearchService(pharmacyRepositoryService, pharmacyRedisTemplateService)

        pharmacyList = Lists.newArrayList(
                Pharmacy.builder()
                        .id(1L)
                        .pharmacyName("세븐일레븐 서귀포색달점")
                        .latitude(33.25778666)
                        .longitude(126.4134715)
                        .build(),
                Pharmacy.builder()
                        .id(2L)
                        .pharmacyName("세븐일레븐 서귀포동문점")
                        .latitude(33.24882037)
                        .longitude(126.5672914)
                        .build())

    }

    def "레디스 장애시 DB를 이용하여 약국 데이터 조회"() {
        when:
        pharmacyRedisTemplateService.findAll() >> []
        pharmacyRepositoryService.findAll() >> pharmacyList

        def result = pharmacySearchService.searchPharmacyDtoList()

        then:
        result.size() == 2
    }
}
