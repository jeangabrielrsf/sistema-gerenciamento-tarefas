package com.br.supera.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ItemDTO {

    @NotBlank
    private String name;

    private boolean status = Boolean.FALSE;

    private boolean priority = Boolean.FALSE;
}
