package de.mjkd.bpm.adapter;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class MapDmn implements ExecutionListener {

    private static final Logger LOGGER = Logger.getLogger(MapDmn.class.getName());

    @Override
    public void notify(DelegateExecution delegateExecution) throws Exception {
        List<Map<String,String>> result = (List<Map<String,String>>)delegateExecution.getVariable("risikoBewertungErgebnis");
        String risk = result.get(0).get("risk");
        String riskAssessment = result.get(0).get("riskAssessment");

        delegateExecution.setVariable("risk", risk);
        delegateExecution.setVariable("riskAssessment", riskAssessment);
        LOGGER.info("Risiko: " + delegateExecution.getVariable("risk"));
        LOGGER.info("Risiko Bewertung: " + delegateExecution.getVariable("riskAssessment"));
    }
}
