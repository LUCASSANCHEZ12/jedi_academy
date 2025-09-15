package com.academia.jedi.domain.entity;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@Schema(description = "Entidad que representa a un maestro Jedi")
public class JediMaster extends Jedi {
    // A list of padawans
    @Schema(description = "Lista de padawans de un maestro")
    private List<Padawan> padawans = null;
    @Schema(description = "Valor condicional de un maestro miembro del consejo", example = "false")
    private boolean memberOfCouncil;
}
