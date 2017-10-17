package lab.schedule;

import java.util.TimerTask;

public class PrintingTask extends TimerTask {

    public void run() {
        ScheduleLog.append("I'm printing task...\n");
    }
}


