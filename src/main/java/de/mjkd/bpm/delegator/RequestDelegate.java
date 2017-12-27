package de.mjkd.bpm.delegator;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.util.logging.Logger;

public class RequestDelegate implements JavaDelegate {

    public static final Logger LOGGER = Logger.getLogger(RequestDelegate.class.getName());

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        LOGGER.info("Processing request by '" + delegateExecution.getVariable("riskAssessment") + "'...");
    }
}
