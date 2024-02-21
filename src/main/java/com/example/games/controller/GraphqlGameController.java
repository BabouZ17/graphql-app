package com.example.games.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import com.example.games.model.Game;
import com.example.games.model.Studio;
import com.example.games.dto.GraphGameDTO;
import com.example.games.dto.GraphStudioDTO;
import com.example.games.dto.GraphPlayerDTO;
import com.example.games.mapper.GraphGameMapper;
import com.example.games.mapper.GraphStudioMapper;
import com.example.games.repository.GameRepository;
import com.example.games.repository.StudioRepository;

import java.util.Optional;
import java.util.Random;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class GraphqlGameController {
    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private StudioRepository studioRepository;

    @Autowired
    private GraphStudioMapper studioMapper;

    @Autowired
    private GraphGameMapper gameMapper;

    @QueryMapping
    public GraphGameDTO gameById(@Argument String id) {
        Optional<Game> game = gameRepository.findById(id);
        if (!game.isPresent()) {
            throw new IllegalStateException(String.format("Game with %s does not exist", id.toString()));
        }
        return this.gameMapper.map(game.get());
    }

    @QueryMapping
    public Iterable<GraphGameDTO> games() {
        List<Game> games = gameRepository.findAll();
        return games.stream().map((game) -> this.gameMapper.map(game)).collect(Collectors.toList());
    }

    @QueryMapping
    public Iterable<GraphStudioDTO> studios() {
        List<Studio> studios = studioRepository.findAll();
        return studios.stream().map((studio) -> this.studioMapper.map(studio))
                .collect(Collectors.toList());
    }

    @SchemaMapping
    public Iterable<GraphPlayerDTO> players(GraphGameDTO gameDTO) {
        // Not persisted into DB
        return GraphPlayerDTO.players()
                .stream()
                .limit(new Random().nextInt(GraphPlayerDTO.players().size() + 1))
                .toList();
    }
}
