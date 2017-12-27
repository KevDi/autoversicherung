package de.mjkd.bpm.autoversicherung;

import com.camunda.demo.environment.DefaultFilter;
import com.camunda.demo.environment.UserDataGenerator;
import org.camunda.bpm.application.PostDeploy;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.authorization.Permission;
import org.camunda.bpm.engine.authorization.Permissions;
import org.camunda.bpm.engine.authorization.Resources;
import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.camunda.bpm.spring.boot.starter.event.PostDeployEvent;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.event.EventListener;

import java.util.logging.Logger;

@SpringBootApplication
@EnableProcessApplication
public class AutoVersicherungApplication {

    public static final String PROCESS_DEFINITION_KEY = "Autoversicherung";

    public static final Logger LOGGER = Logger.getLogger(AutoVersicherungApplication.class.getName());

    public static void main(String[] args) {
        SpringApplication.run(AutoVersicherungApplication.class, args);
    }


    @EventListener
    public void onPostDeploy(PostDeployEvent event) {
        LOGGER.info("Start Creating Demo Users for the Showcases");
        createUsers(event.getProcessEngine());
    }

    private void createUsers(ProcessEngine processEngine) {
        UserDataGenerator.createDefaultUsers(processEngine);

        UserDataGenerator.addUser(processEngine, "rudi", "rudi", "Rudi", "Guselmann");
        UserDataGenerator.addUser(processEngine, "alex", "alex", "Alexander", "Weidner");
        UserDataGenerator.addUser(processEngine, "kevin", "kevin", "Kevin", "Dick");
        UserDataGenerator.addUser(processEngine, "antragsteller", "antragsteller", "Antrag", "Steller");

        UserDataGenerator.addGroup(processEngine, "management", "Management", "kevin");
        UserDataGenerator.addGroup(processEngine, "mitarbeiter", "Mitarbeiter", "rudi", "alex");
        UserDataGenerator.addGroup(processEngine, "extern", "extern", "antragsteller");

        UserDataGenerator.addFilterGroupAuthorization(processEngine, "mitarbeiter", DefaultFilter.FILTER_myTasks, DefaultFilter.FILTER_groupTasksFilter, DefaultFilter.FILTER_allTasksFilter);
        UserDataGenerator.addFilterGroupAuthorization(processEngine, "management", DefaultFilter.FILTER_myTasks, DefaultFilter.FILTER_groupTasksFilter, DefaultFilter.FILTER_allTasksFilter);
        UserDataGenerator.addFilterGroupAuthorization(processEngine, "extern", DefaultFilter.FILTER_myTasks, DefaultFilter.FILTER_groupTasksFilter, DefaultFilter.FILTER_allTasksFilter);




        UserDataGenerator.createGrantGroupAuthorization(processEngine,
                new String[]{"mitarbeiter"},
                new Permission[] {Permissions.READ, Permissions.READ_HISTORY, Permissions.UPDATE_INSTANCE},
                Resources.PROCESS_DEFINITION,
                new String[]{PROCESS_DEFINITION_KEY});
        UserDataGenerator.createGrantUserAuthorization(processEngine, //
                new String[] { "kevin" }, //
                new Permission[] { Permissions.READ, Permissions.READ_HISTORY, Permissions.READ_INSTANCE, Permissions.UPDATE_INSTANCE }, //
                Resources.PROCESS_DEFINITION, //
                new String[] { PROCESS_DEFINITION_KEY });
        UserDataGenerator.createGrantUserAuthorization(processEngine,
                new String[]{"kevin"},
                new Permission[]{Permissions.READ, Permissions.READ_HISTORY},
                Resources.DECISION_DEFINITION,
                new String[] {"riskAssessment"});
        UserDataGenerator.createGrantUserAuthorization(processEngine, //
                new String[] { "kevin" }, //
                new Permission[] { Permissions.READ, Permissions.UPDATE }, //
                Resources.TASK, //
                new String[] { "*" });
        UserDataGenerator.createGrantUserAuthorization(processEngine, //
                new String[] { "kevin" }, //
                new Permission[] { Permissions.ALL }, //
                Resources.DEPLOYMENT, //
                new String[] { "*" });
        UserDataGenerator.createGrantUserAuthorization(processEngine, //
                new String[] { "kevin" }, //
                new Permission[] { Permissions.ACCESS }, //
                Resources.APPLICATION, //
                new String[] { "cockpit" });

        UserDataGenerator.createGrantUserAuthorization(processEngine,
                new String[] {"antragsteller"},
                new Permission[] {Permissions.CREATE},
                Resources.PROCESS_INSTANCE,
                new String[]{"*"});

        UserDataGenerator.createGrantUserAuthorization(processEngine, //
                new String[] { "antragsteller" }, //
                new Permission[] { Permissions.READ, Permissions.READ_HISTORY, Permissions.READ_INSTANCE, Permissions.UPDATE_INSTANCE, Permissions.CREATE_INSTANCE }, //
                Resources.PROCESS_DEFINITION, //
                new String[] { PROCESS_DEFINITION_KEY });

    }
}
