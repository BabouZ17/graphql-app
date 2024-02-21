package com.example.games.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GraphReviewDTO {
    @NotNull
    private String id;

    @NotNull
    private int numberOfStars;

    @NotEmpty
    private String comment;
}
