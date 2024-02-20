package com.example.games.dto;

import jakarta.validation.constraints.NotBlank;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Setter
@Getter
@NoArgsConstructor
public class CreateGameDTO {
    @NotBlank(message = "title is required.")
    private String title;

    @NotBlank(message = "type is required.")
    private String type;

    @NotBlank(message = "platform is required.")
    private String platform;

    @NotBlank(message = "studio is required.")
    private String studio;
}
