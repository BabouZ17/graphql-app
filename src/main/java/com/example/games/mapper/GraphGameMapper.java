package com.example.games.mapper;

import com.example.games.model.Game;
import org.springframework.stereotype.Component;

import com.example.games.dto.GraphReviewDTO;
import com.example.games.dto.GraphGameDTO;
import com.example.games.dto.GraphStudioDTO;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GraphGameMapper {

    public GraphGameDTO map(Game game) {
        GraphGameDTO gameDTO = new GraphGameDTO();
        gameDTO.setId(game.getId());
        gameDTO.setTitle(game.getTitle());
        gameDTO.setPlatform(game.getPlatform());
        gameDTO.setType(game.getType().toString());

        GraphStudioDTO studio = new GraphStudioDTO();
        studio.setId(game.getStudio().getId());
        studio.setName(game.getStudio().getName());
        gameDTO.setStudio(studio);

        List<GraphReviewDTO> reviews = game
                .getReviews()
                .stream()
                .map((r) -> new GraphReviewDTO(
                        r.getId(),
                        r.getNumberOfStars(),
                        r.getComment()))
                .collect(Collectors.toList());
        gameDTO.setReviews(reviews);
        return gameDTO;
    }
}
