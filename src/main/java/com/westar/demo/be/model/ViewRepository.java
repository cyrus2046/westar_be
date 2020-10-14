package com.westar.demo.be.model;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ViewRepository extends JpaRepository<View, Long> {
    View findByName(String name);
}