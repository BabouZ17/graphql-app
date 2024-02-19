package com.example.games.mapper;

import com.example.games.model.Game;
import com.example.games.model.Studio;
import com.example.games.model.GameType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.games.dto.GraphGameDTO;

@Component
public class GraphGameMapper {

    @Autowired
    private StudioMapper mapper;

    public static GraphGameDTO toGraphGameDTO(Game game) {
        GraphGameDTO gameDTO = new GraphGameDTO();
        gameDTO.setId(game.getId());
        gameDTO.setTitle(game.getTitle());
        gameDTO.setPlatform(game.getPlatform());
        gameDTO.setType(game.getType().toString());
        gameDTO.setStudio(StudioMapper.fromStudiotoStudioDTO(game.getStudio()));
        return gameDTO;
    }

    public static Game toGame(GraphGameDTO gameDTO, Studio studio) {
        Game game = new Game();
        game.setId(gameDTO.getId());
        game.setTitle(gameDTO.getTitle());
        game.setPlatform(gameDTO.getPlatform());
        game.setType(GameType.valueOf(gameDTO.getType()));
        game.setStudio(studio);
        return game;
    }
}