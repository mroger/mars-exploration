package org.nasa.exploration.api.controller;

import org.nasa.exploration.api.model.ProbeCreationRequest;
import org.nasa.exploration.api.model.ProbeCreationResponse;
import org.nasa.exploration.api.service.MarsExplorationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/probes")
public class MarsExplorationController {

    @Autowired
    private MarsExplorationService marsExplorationService;

    @PostMapping
    public ResponseEntity<ProbeCreationResponse> createProbe(@RequestBody @Valid ProbeCreationRequest request) {
        final ProbeCreationResponse createdProbe = marsExplorationService.createProbe(request);
        return ResponseEntity.ok(createdProbe);
    }
}
