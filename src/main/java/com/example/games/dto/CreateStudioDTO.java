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
public class CreateStudioDTO {
    @NotBlank(message = "name is required.")
    private String name;
}
