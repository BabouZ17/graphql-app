package com.example.games.mapper;

import org.springframework.stereotype.Component;

import com.example.games.dto.StudioDTO;
import com.example.games.dto.CreateStudioDTO;
import com.example.games.dto.GameDTO;
import com.example.games.model.Studio;
import com.example.games.model.Game;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

@Component
public class StudioMapper {
    public StudioDTO fromStudiotoStudioDTO(Studio studio) {
        StudioDTO studioDTO = new StudioDTO();
        studioDTO.setId(studio.getId());
        studioDTO.setName(studio.getName());
        List<GameDTO> games = studio
                .getGames()
                .stream()
                .map((game) -> new GameDTO(
                        game.getId(),
                        game.getTitle(),
                        game.getType().toString(),
                        game.getPlatform()))
                .collect(
                        Collectors.toList());

        studioDTO.setGames(games);
        return studioDTO;
    }

    public Studio fromCreateStudioDTOtoStudio(CreateStudioDTO studioDTO) {
        Studio studio = new Studio();
        studio.setName(studioDTO.getName());
        studio.setGames(new ArrayList<Game>());
        return studio;
    }
}
