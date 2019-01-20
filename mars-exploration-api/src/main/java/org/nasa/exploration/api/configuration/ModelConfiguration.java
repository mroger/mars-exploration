package org.nasa.exploration.api.configuration;

import org.nasa.exploration.model.MissionControl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelConfiguration {

    @Value("${missioncontrol.plateau.width}")
    private int width;
    @Value("${missioncontrol.plateau.height}")
    private int height;

    @Bean
    public MissionControl missionControl() {
        return new MissionControl(width, height);
    }

}
