package com.academia.jedi.presentation.controller;

import com.academia.jedi.business.service.JediMasterService;
import com.academia.jedi.common.exception.BadMasterRequest;
import com.academia.jedi.common.exception.JediNotFoundException;
import com.academia.jedi.common.exception.NullIDException;
import com.academia.jedi.domain.dto.JediMasterDTO;
import com.academia.jedi.domain.dto.request.JediMasterPatchRequest;
import com.academia.jedi.domain.dto.request.JediMasterRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jedi/master")
public class JediMasterController {

    private final JediMasterService service;

    public JediMasterController(JediMasterService service) {
        this.service = service;
    }


    /*Gestión de Maestros
    * GET /maestros → devuelve lista de todos los maestros.
    * POST /maestros → crea un nuevo maestro.
    * PUT /maestros/{id} → reemplaza la información completa de un maestro.
    * PATCH /maestros/{id} → actualiza solo un campo (ej: especialidad).
    * DELETE /maestros/{id} → elimina un maestro de la orden.
    */
    @Operation(
            summary = "Obtener todos los maestros",
            description = "Devuelve lista de todos los maestros"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de Maestros Jedi"),
    })
    //@ResponseStatus(HttpStatus.OK)
    @GetMapping("/get/all")
    public List<JediMasterDTO> getPadawans(){
        return service.getAll();
    }


    @Operation(
            summary = "Registra un nuevo maestro.",
            description = "Agrega un nuevo maestro a la academia"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Maestro registrado"),
            @ApiResponse(responseCode = "400", description = "Mal envio de json")
    })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/create")
    public JediMasterDTO create(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            examples = {
                                @ExampleObject(name = "Ejemplo de maestro",
                                        summary = "Maestro de la academia Jedi",
                                        value = "{ \"name\": \"Qui-Gon Jinn\", \"age\": 48, \"lightSaberColor\": \"Green\", \"midiChlorianCount\": 10500, \"memberOfCouncil\": false, \"padawanIds\": [101, 102, 103] }"),
                                @ExampleObject(name = "Ejemplo de",
                                        summary = "Maestro de la academia Jedi",
                                        value = "{ \"name\": \"Mace Windu\", \"age\": 53, \"lightSaberColor\": \"Purple\", \"midiChlorianCount\": 12000, \"memberOfCouncil\": true, \"padawanIds\": [] }")
                            }
                    )
            )
            @RequestBody JediMasterRequest jediMasterRequest)
            throws BadMasterRequest {
        JediMasterDTO master;
        try{
            master = service.create(jediMasterRequest);
        } catch (Exception e) {
            throw new BadMasterRequest("Bad request body");
        }
        return master;
    }

    @Operation(
            summary = "Actualiza o cambia la informacion de un maestro",
            description = "Reemplaza la información completa de un maestro"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Maestro actualizado"),
            @ApiResponse(responseCode = "400", description = "Mal envio de json"),
            @ApiResponse(responseCode = "404", description = "El maestro no existe")
    })
    @PutMapping("/update/{id}")
    public JediMasterDTO updateJediMaster(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            examples = {
                                @ExampleObject(name = "Ejemplo de maestro",
                                        summary = "Maestro de la academia Jedi",
                                        value = "{ \"name\": \"Qui-Gon Jinn\", \"age\": 48, \"lightSaberColor\": \"Green\", \"midiChlorianCount\": 10500, \"memberOfCouncil\": false, \"padawanIds\": [101, 102, 103] }"),
                                @ExampleObject(name = "Ejemplo de",
                                        summary = "Maestro de la academia Jedi",
                                        value = "{ \"name\": \"Mace Windu\", \"age\": 53, \"lightSaberColor\": \"Purple\", \"midiChlorianCount\": 12000, \"memberOfCouncil\": true, \"padawanIds\": [] }")
                            }
                    )
            )
            @Parameter(description = "Identificador de un maestro", example = "100")
            @PathVariable Long id ,
            @RequestBody JediMasterRequest jediMasterRequest)
            throws NullIDException {
        if (id == null){
            throw new NullIDException("ID is null");
        }

        JediMasterDTO master;
        try {
            master = service.findById(id);
        }catch (JediNotFoundException e){
            throw new JediNotFoundException("Maestro no encontrado");
        }

        return service.update(id,jediMasterRequest);
    }

    @Operation(
            summary = "Actualiza o cambia la informacion de un maestro",
            description = "Reemplaza la información de forma parcial de un maestro"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Informacion de Maestro actualizado"),
            @ApiResponse(responseCode = "400", description = "Mal envio de json"),
            @ApiResponse(responseCode = "404", description = "El maestro no existe")
    })
    @PatchMapping("/patch/{id}")
    public JediMasterDTO patchJediMaster(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            examples = {
                                @ExampleObject(name = "Ejemplo de maestro",
                                        summary = "Maestro de la academia Jedi",
                                        value = "{ \"age\": 48, \"lightSaberColor\": \"Green\", \"midiChlorianCount\": 10500 }"),
                                @ExampleObject(name = "Ejemplo de",
                                        summary = "Maestro de la academia Jedi",
                                        value = "{ \"age\": 53, \"midiChlorianCount\": 12000, \"memberOfCouncil\": true }")
                            }
                    )
            )
            @Parameter(description = "Identificador de un maestro", example = "100")
            @PathVariable Long id,
            @RequestBody JediMasterPatchRequest request)
            throws NullIDException {

        if (id == null){
            throw new NullIDException("ID is null");
        }

        JediMasterDTO master;
        try {
            master = service.findById(id);
        }catch (JediNotFoundException e){
            throw new JediNotFoundException("Maestro no encontrado");
        }

        return service.patch(id, request);
    }

    @Operation(
            summary = "Elimina un maestro de la lista.",
            description = "Elimina un maestro de la orden."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Maestro eliminado"),
            @ApiResponse(responseCode = "400", description = "Mal envio de datos"),
            @ApiResponse(responseCode = "404", description = "El maestro no existe")
    })
    @DeleteMapping("/delete/{id}")
    public void deleteJediMaster(
            @Parameter(description = "Identificador de un maestro", example = "100")
            @PathVariable Long id) throws NullIDException {
        if (id == null) {
            throw new NullIDException("Id null");
        }
        JediMasterDTO master;
        try {
            master = service.findById(id);
        }catch (JediNotFoundException e){
            throw new JediNotFoundException("Maestro no encontrado");
        }

        service.deleteById(id);
    }

}
