package com.example.pharmacy_wayfinding_project.direction.controller;

import com.example.pharmacy_wayfinding_project.direction.entity.Direction;
import com.example.pharmacy_wayfinding_project.direction.service.DirectionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.util.UriComponentsBuilder;

@Controller
@Slf4j
@RequiredArgsConstructor
public class DirectionController {

    private final DirectionService directionService;
    private static final String GET_DIRECTIONS_BASE_URL="https://map.kakao.com/link/to/";

    @GetMapping("/dir/{encodedId}")
    public String searchDirection(@PathVariable("encodedId") String encodedId) {
        String result = directionService.findDirectionById(encodedId);

        log.info("[DirectionController searchDirection] direction url : {}", result);

        return "redirect:"+result;
    }

}
