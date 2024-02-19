package com.example.games.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Setter
@Getter
@NoArgsConstructor
public class GraphGameDTO {
    private String id;
    private String title;
    private String type;
    private String platform;
    private StudioDTO studio;
}
