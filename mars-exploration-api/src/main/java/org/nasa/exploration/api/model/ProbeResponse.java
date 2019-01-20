package org.nasa.exploration.api.model;

import org.nasa.exploration.model.ProbeAggregate;

public class ProbeResponse {

    private String id;
    private Integer x;
    private Integer y;
    private String direction;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public static ProbeResponse fromModel(ProbeAggregate probeAggregate) {
        ProbeResponse response = new ProbeResponse();
        response.setId(probeAggregate.getId());
        response.setX(probeAggregate.getXPosition());
        response.setY(probeAggregate.getYPosition());
        response.setDirection(probeAggregate.getDirection());
        return response;
    }
}
