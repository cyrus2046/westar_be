package com.westar.demo.be.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.westar.demo.be.model.Car;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class RouteController {
    private final Logger log = LoggerFactory.getLogger(RouteController.class);

    @GetMapping("/route/{srcLatitude}/{srcLongitude}/{destLatitude}/{destLongitude}")
    ResponseEntity<?> checkRoute(@PathVariable Number srcLatitude, @PathVariable Number srcLongitude,
                                 @PathVariable Number destLatitude, @PathVariable Number destLongitude) throws URISyntaxException, JsonProcessingException {

        String toDuration = getTravelInSeconds(srcLatitude,srcLongitude,
                destLatitude, destLongitude);
        long toTravelTime = Math.round(Double.parseDouble(toDuration)/60);

        String backDuration = getTravelInSeconds(destLatitude, destLongitude,
                srcLatitude,srcLongitude);
        long backTravelTime = Math.round(Double.parseDouble(backDuration)/60);

        String ret = "{\"toDuration\":"+toTravelTime+",\"backDuration\":"+backTravelTime+"}";
        System.out.println("ret = "+ret);
        return ResponseEntity.ok().body(ret);
    }

    private String getTravelInSeconds(Number srcLatitude,Number srcLongitude,
                                      Number destLatitude, Number destLongitude) throws URISyntaxException, JsonProcessingException {

        //call map api to get duration
        final String baseUrl = "https://api.tomtom.com/routing/1/calculateRoute/";
        final String apiKey = "8oVCGZf7dPLQH50ojBoAbNLhB9uZiXsy";
        String finalURL = baseUrl + srcLatitude+","+srcLongitude+":"+
                destLatitude+","+destLongitude+
                "/json?travelMode=car&key="+apiKey;

        RestTemplate restTemplate = new RestTemplate();

        URI uri = new URI(finalURL);
        ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);

        //create ObjectMapper instance
        ObjectMapper objectMapper = new ObjectMapper();

        //convert json tree node
        JsonNode rootNode = objectMapper.readTree(result.getBody());

        //read travelTimeInSeconds nodes
        String duration =  rootNode.path("routes").get(0).path("summary").path("travelTimeInSeconds").asText();

        return duration;
    }
}
