package org.nasa.exploration.api.controller;

import org.nasa.exploration.api.model.ProbeCreationRequest;
import org.nasa.exploration.api.model.ProbeCreationResponse;
import org.nasa.exploration.api.service.ProbeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/probes")
public class ProbeController {

    @Autowired
    private ProbeService probeService;

    @PostMapping
    public ResponseEntity<ProbeCreationResponse> create(@RequestBody @Valid ProbeCreationRequest request) {
        final ProbeCreationResponse createdProbe = probeService.createProbe(request);
        return ResponseEntity.ok(createdProbe);
    }
}
