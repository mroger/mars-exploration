package org.nasa.exploration.api.controller;

import org.nasa.exploration.api.exception.ProbeNotFoundByIdException;
import org.nasa.exploration.api.exception.ProbeNotFoundByPositionException;
import org.nasa.exploration.api.exception.ProbesNotFoundException;
import org.nasa.exploration.api.model.ProbeCreationRequest;
import org.nasa.exploration.api.model.ProbeCreationResponse;
import org.nasa.exploration.api.model.ProbeInstructionRequest;
import org.nasa.exploration.api.model.ProbeListResponse;
import org.nasa.exploration.api.model.ProbeResponse;
import org.nasa.exploration.api.service.ProbeService;
import org.nasa.exploration.model.ProbeAggregate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
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
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping("/probes")
public class ProbeController implements IProbeController {

    @Autowired
    private ProbeService probeService;

    @Override
    @PostMapping(produces = "application/json", consumes = "application/json")
    public ResponseEntity<ProbeCreationResponse> create(@RequestBody @Valid ProbeCreationRequest request) {
        final ProbeAggregate createdProbe = probeService.createProbe(request);

        ProbeCreationResponse probeCreationResponse = ProbeCreationResponse.fromModel(createdProbe);
        addLinks(createdProbe, probeCreationResponse);

        return ResponseEntity.ok(probeCreationResponse);
    }

    @Override
    @GetMapping(produces = "application/json")
    public ResponseEntity<ProbeListResponse> findAll() {
        final List<ProbeAggregate> probes =  probeService.findAllProbes();

        if (probes.isEmpty()) {
            throw new ProbesNotFoundException();
        }

        List<ProbeResponse> probeListResponse = probes.stream()
            .map(ProbeResponse::fromModel)
            .map(pr -> {
                addSelfLink(pr);
                return pr;
            })
            .collect(Collectors.toList());

        return ResponseEntity.ok(new ProbeListResponse(probeListResponse));
    }

    @Override
    @GetMapping(value = "/{probeId}", produces = "application/json")
    public ResponseEntity<ProbeResponse> findById(@PathVariable final String probeId) {
        final Optional<ProbeAggregate> probe =  probeService.findProbeById(probeId);

        if (!probe.isPresent()) {
            throw new ProbeNotFoundByIdException(probeId);
        }

        final ProbeResponse probeResponse = ProbeResponse.fromModel(probe.get());
        addAllLink(probeResponse);

        return ResponseEntity.ok(probeResponse);
    }

    @Override
    @GetMapping(value = "/findByPosition", produces = "application/json")
    public ResponseEntity<ProbeResponse> findByPosition(@RequestParam int x, @RequestParam int y) {
        final Optional<ProbeAggregate> probe =  probeService.findProbeByPosition(x, y);

        if (!probe.isPresent()) {
            throw new ProbeNotFoundByPositionException(x, y);
        }

        final ProbeResponse probeResponse = ProbeResponse.fromModel(probe.get());
        addSelfLink(probeResponse);
        addAllLink(probeResponse);

        return ResponseEntity.ok(probeResponse);
    }

    @Override
    @PutMapping(produces = "application/json", consumes = "application/json")
    public ResponseEntity<ProbeResponse> processInstruction(@RequestBody @Valid ProbeInstructionRequest request) {
        final Optional<ProbeAggregate> probe =  probeService.processInstruction(request.getId(), request.getInstruction());

        if (!probe.isPresent()) {
            throw new ProbeNotFoundByIdException(request.getId());
        }
        final ProbeResponse probeResponse = ProbeResponse.fromModel(probe.get());
        addSelfLink(probeResponse);
        addAllLink(probeResponse);

        return ResponseEntity.ok(probeResponse);
    }

    private void addLinks(ProbeAggregate createdProbe, ProbeCreationResponse probeCreationResponse) {
        Link allLink = linkTo(methodOn(ProbeController.class).findAll()).withRel("allProbes");
        Link selfLink = linkTo(methodOn(ProbeController.class).findById(createdProbe.getId())).withSelfRel();

        probeCreationResponse.add(allLink);
        probeCreationResponse.add(selfLink);
    }

    private void addSelfLink(ProbeResponse probeResponse) {
        Link selfLink = linkTo(methodOn(ProbeController.class).findById(probeResponse.getProbeId())).withSelfRel();
        probeResponse.add(selfLink);
    }

    private void addAllLink(ProbeResponse probeResponse) {
        Link allLink = linkTo(methodOn(ProbeController.class).findAll()).withRel("allProbes");
        probeResponse.add(allLink);
    }
}
