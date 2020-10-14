package com.westar.demo.be.controller;

import com.westar.demo.be.model.Hotel;
import com.westar.demo.be.model.HotelRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/api")
class HotelController {

    private final Logger log = LoggerFactory.getLogger(HotelController.class);
    private HotelRepository hotelRepository;

    public HotelController(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    @GetMapping("/hotels")
    Collection<Hotel> hotels() {
        return hotelRepository.findAll();
    }

    @GetMapping("/hotel/{id}")
    ResponseEntity<?> getHotel(@PathVariable Long id) {
        Optional<Hotel> hotel = hotelRepository.findById(id);
        return hotel.map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/hotel")
    ResponseEntity<Hotel> createHotel(@Valid @RequestBody Hotel hotel) throws URISyntaxException {
        log.info("Request to create hotel: {}", hotel);
        Hotel result = hotelRepository.save(hotel);
        return ResponseEntity.created(new URI("/api/hotel/" + result.getId()))
                .body(result);
    }

    @PutMapping("/hotel/{id}")
    ResponseEntity<Hotel> updateHotel(@Valid @RequestBody Hotel hotel) {
        log.info("Request to update hotel: {}", hotel);
        Hotel result = hotelRepository.save(hotel);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/hotel/{id}")
    public ResponseEntity<?> deleteHotel(@PathVariable Long id) {
        log.info("Request to delete hotel: {}", id);
        hotelRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}