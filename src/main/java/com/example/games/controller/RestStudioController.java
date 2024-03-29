package com.example.games.controller;

import com.example.games.repository.StudioRepository;
import com.example.games.model.Studio;
import com.example.games.dto.StudioDTO;
import com.example.games.exceptions.ResourceNotFoundException;
import com.example.games.dto.CreateStudioDTO;
import com.example.games.mapper.StudioMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

import java.util.stream.Collectors;
import java.util.List;

@RestController
@RequestMapping("studios")
public class RestStudioController {

    @Autowired
    private StudioRepository studioRepository;

    @Autowired
    private StudioMapper mapper;

    @GetMapping("/{id}")
    public ResponseEntity<StudioDTO> getStudio(@PathVariable String id) {
        Studio studio = studioRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Studio: " + id + " does not exist"));

        StudioDTO retrievedStudio = this.mapper.fromStudiotoStudioDTO(studio);
        return new ResponseEntity<>(retrievedStudio, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<StudioDTO>> getAllStudios() {
        List<StudioDTO> studios = studioRepository.findAll().stream()
                .map((studio) -> this.mapper.fromStudiotoStudioDTO(studio))
                .collect(Collectors.toList());
        return new ResponseEntity<>(studios, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<StudioDTO> saveStudio(@RequestBody @Valid CreateStudioDTO studioDTO) {
        Studio studio = this.mapper.fromCreateStudioDTOtoStudio(studioDTO);
        Studio savedStudio = studioRepository.save(studio);
        StudioDTO createdStudio = this.mapper.fromStudiotoStudioDTO(savedStudio);
        return new ResponseEntity<>(createdStudio, HttpStatus.CREATED);
    }
}
