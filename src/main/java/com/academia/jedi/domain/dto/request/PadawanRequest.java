package com.academia.jedi.domain.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PadawanRequest extends JediRequest {
    private boolean readyForTrials = false;
    // Validations for masterID
    @NotNull(message = "Master id is required")
    private long masterID; // Master ID for searching
}