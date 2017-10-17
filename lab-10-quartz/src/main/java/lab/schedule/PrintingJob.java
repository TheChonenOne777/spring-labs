package lab.schedule;

import org.quartz.JobExecutionException;
import org.quartz.JobExecutionContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class PrintingJob extends QuartzJobBean {
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        ScheduleLog.append("I'm printing job...\n");
    }
}
