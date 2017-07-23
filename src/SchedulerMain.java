/* 3/22/17
 * Executing SendMailExchange from spring injection 
 */
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import java.util.ArrayList;

public class SchedulerMain implements Runnable {
	//private static Calendar cal;
	ArrayList obj;
	private static int millsecondsMin = 1800000;
	private static Timer time;

	public static void main(String[] args) throws InterruptedException {

		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"applicationContext.xml");

		SendMailExchange sendMailBean = (SendMailExchange) context.getBean(
				"sendMailExchange", SendMailExchange.class);

		SendMailExchReportOne sendMailExchReportOneBean = (SendMailExchReportOne) context
				.getBean("sendMailExchAnalysis",
						SendMailExchReportOne.class);

		SendMailExchReportTwo sendMailExcBean = (SendMailExchReportTwo) context
				.getBean("sendMailExchException",
						SendMailExchReportTwo.class);
 

		time = new Timer();
		ScheduledTask st = new ScheduledTask();

		time.schedule(st, 0, millsecondsMin); // Create Repetitively task for
												// every 1 secs

		time.schedule(sendMailBean, 0, millsecondsMin); // utilizing bean
		time.schedule(sendMailExchReportOneBean, 0, millsecondsMin); // utilizing
																		// bean
		time.schedule(sendMailExcBean, 0, millsecondsMin); // utilizing bean
		// time.schedule(sendMailBV, 0, 1800000 );

		// for demo only.
		for (int i = 0; i <= 5; i++) {
			System.out.println("Execution in Main Thread...." + i);
			Thread.sleep(millsecondsMin);
			if (i == 5) {
				System.out.println("Application Terminates");
				System.out.println("executed this many times " + i);
				// System.exit(0);
			}
		}

		/* test */
		// }
		// },
		// 68400000
		// );aud
		/* test */
		context.close();
	} // end main

	public static void update() {
		TimerTask timerTask = new TimerTask() {

			@Override
			public void run() {
				System.out.println("Updated timer");

			}
		};
		time.cancel();
		time = new Timer();
		time.schedule(timerTask, millsecondsMin); // time.schedule(timerTask,
													// 4500000);
	}

	@Override
	public void run() {
		System.out.println("Updated timer");

	}
}