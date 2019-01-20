package org.nasa.exploration.api.model;

import org.nasa.exploration.model.ProbeAggregate;

public class ProbeCreationResponse {

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

    public static ProbeCreationResponse fromModel(ProbeAggregate probe) {
        ProbeCreationResponse response = new ProbeCreationResponse();
        response.setId(probe.getId());
        response.setX(probe.getXPosition());
        response.setY(probe.getYPosition());
        response.setDirection(probe.getDirection());
        return response;
    }
}
