package com.example.games.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Setter
@Getter
@NoArgsConstructor
public class GraphStudioDTO {

    @NotBlank
    private String id;

    @NotBlank
    private String name;

    @NotNull
    private List<GraphGameDTO> games;
}
