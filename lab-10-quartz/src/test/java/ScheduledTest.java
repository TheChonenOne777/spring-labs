import static org.junit.Assert.assertTrue;
import lab.schedule.ScheduleLog;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application-context-ad.xml")
public class ScheduledTest{

	@Before
	public void setUp() throws Exception {
        ScheduleLog.clear();
    }

	@Test
    public void testRepeatableJob (){
        try {
            assertTrue(ScheduleLog.getStringValue().isEmpty());
            System.out.println("1: " + ScheduleLog.getStringValue());
            Thread.sleep(3000);
            assertTrue(ScheduleLog.getStringValue().isEmpty());
            System.out.println("2: " + ScheduleLog.getStringValue());
            int logLength = ScheduleLog.getStringValue().length();
            Thread.sleep(3000);
            System.out.println("3: " + ScheduleLog.getStringValue());
            assertTrue(ScheduleLog.getStringValue().contains("I'm printing job..."));
            for (int i = 0; i < 5; i ++){
                System.out.println(i + 4  + ": " + ScheduleLog.getStringValue());
                assertTrue(ScheduleLog.getStringValue().length() > logLength);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

	@After
    public void tearDown() throws Exception {
        ScheduleLog.clear();
    }
}