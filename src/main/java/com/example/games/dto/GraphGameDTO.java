package com.example.games.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.example.games.dto.ReviewDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.ArrayList;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GraphGameDTO {

    @NotBlank
    private String id;

    @NotBlank
    private String title;

    @NotBlank
    private String type;

    @NotBlank
    private String platform;

    @NotNull
    private GraphStudioDTO studio;

    @NotNull
    private List<GraphReviewDTO> reviews = new ArrayList<GraphReviewDTO>();
}
