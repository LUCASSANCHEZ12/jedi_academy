package com.academia.jedi.presentation.controller;

import com.academia.jedi.business.service.JediMasterService;
import com.academia.jedi.business.service.PadawanService;
import com.academia.jedi.common.exception.JediNotFoundException;
import com.academia.jedi.common.exception.NullIDException;
import com.academia.jedi.domain.dto.JediMasterDTO;
import com.academia.jedi.domain.dto.PadawanDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/jedi/assign")
public class AssignController {

    private final JediMasterService service_master;
    private final PadawanService service_padawan;

    public AssignController(JediMasterService service_master, PadawanService service_padawan) {
        this.service_master = service_master;
        this.service_padawan = service_padawan;
    }

    /*Asignaciones
    * POST /maestros/{id}/padawans/{padawanId} → asigna un aprendiz a un maestro. (201 Created / 404 Not Found)
    * GET /maestros/{id}/padawans → devuelve todos los aprendices de un maestro.
    */
    @Operation(
            summary = "Asigna a un padawan su maestro del consejo Jedi.",
            description = "Asigna un aprendiz a un maestro."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Aprendiz asignado"),
            @ApiResponse(responseCode = "404", description = "Padawan No encontrado")
    })
    @PostMapping("/masters/{id}/padawans/{padawanId}")
    public JediMasterDTO assignPadawan(
            @Parameter(description = "Identificador de un maestro", example = "100")
            @PathVariable Long id,
            @Parameter(description = "Identificador de un padawan", example = "200")
            @PathVariable Long padawanId) throws NullIDException {
        if (id == null || padawanId == null) {
            throw new NullIDException("id or padawanId cannot be null");
        }
        PadawanDTO padawan;
        JediMasterDTO master;
        try {
            master = service_master.findById(id);

        }catch (JediNotFoundException e){
            throw new JediNotFoundException("Maestro no encontrado");
        }
        try {
            padawan = service_padawan.findById(id);

        }catch (JediNotFoundException e){
            throw new JediNotFoundException("Padawan no encontrado");
        }
        service_master.assignPadawan(id,padawanId);
        service_padawan.assignMaster(id,padawanId);
        return service_master.findById(id);
    }

    @Operation(
            summary = "Ver la lista de padawans de un maestro del consejo Jedi.",
            description = "devuelve todos los aprendices de un maestro"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de aprendices"),
            @ApiResponse(responseCode = "404", description = "Padawan No encontrado")
    })
    @GetMapping("/masters/{id}/padawans")
    public Map<Long, String> getPadawans(
            @Parameter(description = "Identificador de un maestro", example = "100")
            @PathVariable Long id) throws NullIDException {
        if (id == null) {
            throw new NullIDException("padawanId cannot be null");
        }
        JediMasterDTO master;
        try {
            master = service_master.findById(id);
        }catch (JediNotFoundException e){
            throw new JediNotFoundException("Padawan no encontrado");
        }
        return master.getPadawans();
    }
}
