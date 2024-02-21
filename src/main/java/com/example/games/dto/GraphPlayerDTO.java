package com.example.games.dto;

import java.util.List;
import java.util.Arrays;

public record GraphPlayerDTO(String id, String firstName, String LastName) {
    public static List<GraphPlayerDTO> players() {
        return Arrays.asList(
                new GraphPlayerDTO("1", "Dimitri", "XXX"),
                new GraphPlayerDTO("2", "Liam", "XXX"),
                new GraphPlayerDTO("3", "Julia", "XXX"));
    }

}
