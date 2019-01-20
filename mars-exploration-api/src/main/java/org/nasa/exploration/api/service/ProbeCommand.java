package org.nasa.exploration.api.service;

import org.nasa.exploration.model.ProbeAggregate;

public class ProbeCommand {

    private ProbeAggregate probeAggregate;

    private ProbeCommand(ProbeAggregate probeAggregate) {
        this.probeAggregate = probeAggregate;
    }

    public static ProbeCommand instance(ProbeAggregate probeAggregate) {
        return new ProbeCommand(probeAggregate);
    }
}
