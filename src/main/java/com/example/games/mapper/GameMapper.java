package com.example.games.mapper;

import com.example.games.model.Game;
import com.example.games.model.Studio;
import com.example.games.model.GameType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.games.dto.GameDTO;
import com.example.games.dto.CreateGameDTO;

@Component
public class GameMapper {

    public static GameDTO fromGametoGameDTO(Game game) {
        GameDTO gameDTO = new GameDTO();
        gameDTO.setId(game.getId());
        gameDTO.setTitle(game.getTitle());
        gameDTO.setPlatform(game.getPlatform());
        gameDTO.setType(game.getType().toString());
        gameDTO.setStudio(game.getStudio().getName());
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
