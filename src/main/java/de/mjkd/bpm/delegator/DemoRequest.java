package de.mjkd.bpm.delegator;

import de.mjkd.bpm.model.Person;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.util.logging.Logger;

public class DemoRequest implements JavaDelegate {

    public static final Logger LOGGER = Logger.getLogger(DemoRequest.class.getName());

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        Person person = (Person)delegateExecution.getVariable("customerData");
        LOGGER.info(person.getName());
    }
}
