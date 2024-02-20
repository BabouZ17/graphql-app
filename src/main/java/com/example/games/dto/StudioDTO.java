package com.example.games.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import jakarta.validation.constraints.NotBlank;

@Data
@Setter
@Getter
@NoArgsConstructor
public class StudioDTO {
    @NotBlank(message = "id is required.")
    private String id;

    @NotBlank(message = "name is required.")
    private String name;

    @NotBlank(message = "games are required.")
    private List<GameDTO> games;
}
