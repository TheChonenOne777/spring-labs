package lab.schedule;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ScheduledTask {

    private static final Log log = LogFactory.getLog(ScheduledTask.class);

    @Scheduled(fixedDelay = 5500)
    void doSomething() {
        log.info("Appending log message into ScheduleLog ...");
        ScheduleLog.append("I'm printing job...\n");
    }
}
