package com.pragma.smallsquare.insfrastructure.input.rest.employee;

import com.pragma.smallsquare.application.dto.request.EmployeeRequest;
import com.pragma.smallsquare.application.handler.employee.IEmployeeHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/small-square")
public class EmployeeRestController {

    private final IEmployeeHandler employeeHandler;

    @Operation(summary = "Save new employee")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "CREATED. Employee created successfully", content = @Content),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST. JSON request is invalid", content = @Content),
            @ApiResponse(responseCode = "401", description = "UNAUTHORIZED. User is not authorized", content = @Content)
    })
    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping(value = "/employee/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveEmployee(@Valid @RequestBody EmployeeRequest employeeRequest,
                                             HttpServletRequest request) {
        Integer currentOwnerId = Integer.valueOf(request.getAttribute("userId").toString());
        employeeHandler.saveEmployeeFromSmallSquare(employeeRequest, currentOwnerId);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
