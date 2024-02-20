package com.example.games.mapper;

import com.example.games.model.Game;
import com.example.games.model.Studio;
import com.example.games.model.GameType;

import org.springframework.stereotype.Component;

import com.example.games.dto.GameDTO;
import com.example.games.dto.CreateGameDTO;
import com.example.games.dto.ReviewDTO;
import com.example.games.dto.StudioDTO;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GameMapper {

    public GameDTO fromGametoGameDTO(Game game) {
        GameDTO gameDTO = new GameDTO();
        gameDTO.setId(game.getId());
        gameDTO.setTitle(game.getTitle());
        gameDTO.setPlatform(game.getPlatform());
        gameDTO.setType(game.getType().toString());

        StudioDTO studio = new StudioDTO();
        studio.setId(game.getStudio().getId());
        studio.setName(game.getStudio().getName());
        gameDTO.setStudio(studio);

        List<ReviewDTO> reviews = game.getReviews().stream()
                .map((r) -> new ReviewDTO(
                        r.getId(),
                        r.getNumberOfStars(),
                        r.getComment()))
                .collect(Collectors.toList());
        gameDTO.setReviews(reviews);
        return gameDTO;
    }

    public static Game fromCreateGameDTOtoGame(CreateGameDTO gameDTO, Studio studio) {
        Game game = new Game();
        game.setTitle(gameDTO.getTitle());
        game.setPlatform(gameDTO.getPlatform());
        game.setType(GameType.valueOf(gameDTO.getType()));
        game.setStudio(studio);
        return game;
    }
}
