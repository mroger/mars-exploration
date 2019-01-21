package org.nasa.exploration.api.controller;

import org.nasa.exploration.api.model.ProbeCreationRequest;
import org.nasa.exploration.api.model.ProbeCreationResponse;
import org.nasa.exploration.api.model.ProbeInstructionRequest;
import org.nasa.exploration.api.model.ProbeListResponse;
import org.nasa.exploration.api.model.ProbeResponse;
import org.nasa.exploration.api.service.ProbeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

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

    @GetMapping
    public ResponseEntity<ProbeListResponse> findAll() {
        final List<ProbeResponse> probes =  probeService.findAllProbes();
        return ResponseEntity.ok(new ProbeListResponse(probes));
    }

    @GetMapping("/{probeId}")
    public ResponseEntity<ProbeResponse> findById(@PathVariable final String probeId) {
        final ProbeResponse probe =  probeService.findProbeById(probeId);
        return ResponseEntity.ok(probe);
    }

    @GetMapping("/position")
    public ResponseEntity<ProbeResponse> findByPosition(@RequestParam int x, @RequestParam int y) {
        final ProbeResponse probe =  probeService.findProbeByPosition(x, y);
        return ResponseEntity.ok(probe);
    }

    @PutMapping
    public ResponseEntity<ProbeResponse> processInstruction(@RequestBody @Valid ProbeInstructionRequest request) {
        final ProbeResponse probe =  probeService.processInstruction(request.getId(), request.getInstruction());
        return ResponseEntity.ok(probe);
    }
}
