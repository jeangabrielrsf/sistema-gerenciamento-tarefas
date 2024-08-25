package com.br.supera.dtos;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ListDTO {
    private final static String message = "O tamanho do nome da lista deve ter pelo menos 4 caracteres";

    @NotBlank
    @Size(min = 4, message = message)
    private String name;
}
