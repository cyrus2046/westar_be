package com.westar.demo.be.controller;

import com.westar.demo.be.model.View;
import com.westar.demo.be.model.ViewRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("/api")
class ViewController {

    private final Logger log = LoggerFactory.getLogger(ViewController.class);
    private ViewRepository viewRepository;

    public ViewController(ViewRepository viewRepository) {
        this.viewRepository = viewRepository;
    }

    @GetMapping("/views")
    Collection<View> views() {
        return viewRepository.findAll();
    }

    @GetMapping("/view/{id}")
    ResponseEntity<?> getView(@PathVariable Long id) {
        Optional<View> view = viewRepository.findById(id);
        return view.map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/view")
    ResponseEntity<View> createView(@Valid @RequestBody View view) throws URISyntaxException {
        log.info("Request to create view: {}", view);
        View result = viewRepository.save(view);
        return ResponseEntity.created(new URI("/api/view/" + result.getId()))
                .body(result);
    }

    @PutMapping("/view")
    ResponseEntity<View> updateView(@Valid @RequestBody View view) {
        log.info("Request to update view: {}", view);

        View result = viewRepository.save(view);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/view/{id}")
    public ResponseEntity<?> deleteView(@PathVariable Long id) {
        log.info("Request to delete view: {}", id);
        viewRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}