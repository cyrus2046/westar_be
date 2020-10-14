package com.westar.demo.be.model;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface HotelRepository extends JpaRepository<Hotel, Long> {
    Hotel findByName(String name);
}