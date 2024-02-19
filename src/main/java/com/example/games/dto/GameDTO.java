package com.example.games.dto;

import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Setter
@Getter
@NoArgsConstructor
public class GameDTO {

    @NotBlank(message = "id is required.")
    private String id;

    @NotBlank(message = "title is requried.")
    private String title;

    @NotBlank(message = "type is required.")
    private String type;

    @NotBlank(message = "platform is required.")
    private String platform;

    @NotBlank(message = "studio is required.")
    private String studio;
}
