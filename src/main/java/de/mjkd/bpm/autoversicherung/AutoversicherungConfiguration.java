package de.mjkd.bpm.autoversicherung;


import org.camunda.bpm.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.camunda.bpm.spring.boot.starter.util.SpringBootProcessEnginePlugin;
import org.springframework.context.annotation.Configuration;

import java.util.logging.Logger;

@Configuration
public class AutoversicherungConfiguration extends SpringBootProcessEnginePlugin {

    public static final Logger LOGGER = Logger.getLogger(AutoversicherungConfiguration.class.getName());

    @Override
    public void preInit(ProcessEngineConfigurationImpl processEngineConfiguration) {
        super.preInit(processEngineConfiguration);
        LOGGER.info("++++++++++++++++++++++++++++++ Pre Init Call Config+++++++++++");
        if(!processEngineConfiguration.isAuthorizationEnabled()) {
            LOGGER.info("Not enabled");
            processEngineConfiguration.setAuthorizationEnabled(true);
        } else {
            LOGGER.info("Enabled");
        }

    }
}
