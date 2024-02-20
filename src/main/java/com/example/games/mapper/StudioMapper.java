package com.example.games.mapper;

import org.springframework.beans.factory.annotation.Autowired;
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

    private GameMapper gameMapper;

    public StudioMapper(GameMapper mapper) {
        this.gameMapper = mapper;
    }

    public StudioDTO fromStudiotoStudioDTO(Studio studio) {
        StudioDTO studioDTO = new StudioDTO();
        studioDTO.setId(studio.getId());
        studioDTO.setName(studio.getName());
        List<GameDTO> gamesDTO = studio
                .getGames()
                .stream()
                .map((game) -> this.gameMapper.fromGametoGameDTO(game))
                .collect(
                        Collectors.toList());

        studioDTO.setGames(gamesDTO);
        return studioDTO;
    }

    public static Studio fromCreateStudioDTOtoStudio(CreateStudioDTO studioDTO) {
        Studio studio = new Studio();
        studio.setName(studioDTO.getName());
        studio.setGames(new ArrayList<Game>());
        return studio;
    }
}
