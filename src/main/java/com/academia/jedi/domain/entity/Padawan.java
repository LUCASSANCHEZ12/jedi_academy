package com.academia.jedi.domain.entity;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@Schema(description = "Entidad que representa a un padawan Jedi")
public class Padawan extends Jedi{
    // 1 Master
    @Schema(description = "Entidad maestro de un padawan", example = "Obiwan Kenobi")
    private JediMaster master = null;
    @Schema(description = "Valor condicional para un padawan si esta listo para las pruebas Jedi", example = "false")
    private boolean readyForTrials; // to become a Jedi
}
