package com.example.games.controller;

import com.example.games.repository.StudioRepository;
import com.example.games.exception.ResourceNotFoundException;
import com.example.games.model.Studio;
import com.example.games.dto.StudioDTO;
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

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.List;

import javax.validation.Valid;

@RestController
public class RestStudioController {

    @Autowired
    private StudioRepository studioRepository;

    @Autowired
    private StudioMapper mapper;

    @GetMapping("/studios/{id}")
    public ResponseEntity<StudioDTO> getStudio(@PathVariable String id) {
        Optional<Studio> studio = studioRepository.findById(id);
        if (!studio.isPresent()) {
            throw new ResourceNotFoundException(String.format("Studio with %s does not exist", id.toString()));
        }
        StudioDTO retrievedStudio = StudioMapper.fromStudiotoStudioDTO(studio.get());
        return new ResponseEntity<>(retrievedStudio, HttpStatus.OK);
    }

    @GetMapping("/studios")
    public ResponseEntity<List<StudioDTO>> getAllStudios() {
        List<StudioDTO> studios = studioRepository.findAll().stream()
                .map((studio) -> StudioMapper.fromStudiotoStudioDTO(studio))
                .collect(Collectors.toList());
        return new ResponseEntity<>(studios, HttpStatus.OK);
    }

    @PostMapping("/studios")
    public ResponseEntity<StudioDTO> saveStudio(@Valid @RequestBody CreateStudioDTO studioDTO) {
        Studio studio = StudioMapper.fromCreateStudioDTOtoStudio(studioDTO);
        Studio savedStudio = studioRepository.save(studio);
        StudioDTO createdStudio = StudioMapper.fromStudiotoStudioDTO(savedStudio);

        return new ResponseEntity<>(createdStudio, HttpStatus.CREATED);
    }
}
