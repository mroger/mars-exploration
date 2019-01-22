package org.nasa.exploration.api.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.nasa.exploration.api.exception.handler.ErrorResponse;
import org.nasa.exploration.api.model.ProbeCreationRequest;
import org.nasa.exploration.api.model.ProbeCreationResponse;
import org.nasa.exploration.api.model.ProbeInstructionRequest;
import org.nasa.exploration.api.model.ProbeListResponse;
import org.nasa.exploration.api.model.ProbeResponse;
import org.springframework.http.ResponseEntity;

@Api(value="marsexploration", description="Operations for probes sent to Mars")
public interface IProbeController {

    @ApiOperation(
        value = "Creates a probe and put it over a Mars plateau",
        nickname = "create",
        consumes = "application/json")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 400, message = "Bad request", response = ErrorResponse.class),
        @ApiResponse(code = 415, message = "Invalid Media Type", response = ErrorResponse.class),
        @ApiResponse(code = 409, message = "Position already taken by probe", response = ErrorResponse.class),
        @ApiResponse(code = 500, message = "Internal Server Error", response = ErrorResponse.class) })
    ResponseEntity<ProbeCreationResponse> create(ProbeCreationRequest request);

    @ApiOperation(
        value = "Returns all probes created and sent to Mars",
        nickname = "findAll",
        consumes = "application/json")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 415, message = "Invalid Media Type", response = ErrorResponse.class) })
    ResponseEntity<ProbeListResponse> findAll();

    @ApiOperation(
        value = "Find probe by Id",
        nickname = "findById",
        consumes = "application/json")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 400, message = "Bad request", response = ErrorResponse.class),
        @ApiResponse(code = 415, message = "Invalid Media Type", response = ErrorResponse.class) })
    ResponseEntity<ProbeResponse> findById(String probeId);

    @ApiOperation(
        value = "Find probe by position",
        nickname = "findByPosition",
        consumes = "application/json")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Created"),
        @ApiResponse(code = 400, message = "Bad request", response = ErrorResponse.class),
        @ApiResponse(code = 415, message = "Invalid Media Type", response = ErrorResponse.class) })
    ResponseEntity<ProbeResponse> findByPosition(int x, int y);

    @ApiOperation(
        value = "Send instruction to probe (M, R or L)",
        nickname = "processInstruction",
        consumes = "application/json")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Created"),
        @ApiResponse(code = 400, message = "Bad request", response = ErrorResponse.class),
        @ApiResponse(code = 415, message = "Invalid Media Type", response = ErrorResponse.class) })
    ResponseEntity<ProbeResponse> processInstruction(ProbeInstructionRequest request);
}
