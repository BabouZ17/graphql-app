package com.example.games.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.ArrayList;

@Data
@Setter
@Getter
@NoArgsConstructor
public class GameDTO {

    public GameDTO(String id, String title, String type, String platform) {
        this.id = id;
        this.title = title;
        this.type = type;
        this.platform = platform;
    }

    @NotBlank(message = "id is required.")
    private String id;

    @NotBlank(message = "title is requried.")
    private String title;

    @NotBlank(message = "type is required.")
    private String type;

    @NotBlank(message = "platform is required.")
    private String platform;

    @NotBlank(message = "studio is required.")
    private StudioDTO studio;

    @NotNull
    private List<ReviewDTO> reviews = new ArrayList<ReviewDTO>();
}
