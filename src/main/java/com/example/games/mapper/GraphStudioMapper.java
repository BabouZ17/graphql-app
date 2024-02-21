package com.example.games.mapper;

import org.springframework.stereotype.Component;

import com.example.games.dto.GraphStudioDTO;
import com.example.games.dto.GraphGameDTO;
import com.example.games.model.Studio;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GraphStudioMapper {

    private GraphGameMapper gameMapper;

    public GraphStudioMapper(GraphGameMapper gameMapper) {
        this.gameMapper = gameMapper;
    }

    public GraphStudioDTO map(Studio studio) {
        GraphStudioDTO studioDTO = new GraphStudioDTO();
        studioDTO.setId(studio.getId());
        studioDTO.setName(studio.getName());
        List<GraphGameDTO> games = studio
                .getGames()
                .stream()
                .map((g) -> this.gameMapper.map(g))
                .collect(Collectors.toList());

        studioDTO.setGames(games);
        return studioDTO;
    }
}
