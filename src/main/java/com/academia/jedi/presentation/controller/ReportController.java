package com.academia.jedi.presentation.controller;

import com.academia.jedi.business.service.JediService;
import com.academia.jedi.domain.entity.Report;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/jedi/report")
public class ReportController {

    private final JediService service;

    public  ReportController(JediService service) {
        this.service = service;
    }
    /*
    * Reporte
    * GET /academia/resumen → devuelve:
    * Total de maestros.
    * Total de padawans.
    * Promedio de padawans por maestro. (200 OK)
    */
    @Operation(
            summary = "Reporte de la academia Jedi..",
            description = "Devuelve: Total de maestros. Total de padawans. Promedio de padawans por maestro."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reporte creado"),
    })
    @GetMapping("/academy/summary")
    public Report getReport(){
        return service.getReport();
    }
}
