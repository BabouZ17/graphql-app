package com.example.games.exceptions;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Setter
@Getter
@Builder
public class ErrorResponse {
    private ErrorType type;
    private List<String> errors;
    private String message;
    private Long timeStamp;
}
