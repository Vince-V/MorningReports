//import java.sql.Date;
//import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTest {

	static Calendar cal = Calendar.getInstance();
	static int min = cal.get(Calendar.MINUTE);
	static int hour = cal.get(Calendar.HOUR_OF_DAY);

	public static void main(String[] args) {

		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);

		if (dayOfWeek == 6) {
			System.out.println("today is Friday "
					+ cal.get(Calendar.DAY_OF_WEEK));

		}

		System.out.println(getTodaysDate());
	}

	public static String isDateSatThruMon() {
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
		String WkndMonOrOtherday = "";

		if (dayOfWeek == 7 || dayOfWeek == 2) {
			WkndMonOrOtherday = "Monday";

		} else {
			WkndMonOrOtherday = "TuesThruFri";

		}

		return WkndMonOrOtherday;
	}

	public static final String getYesterdaysDate() {
		String prevDate = "";

		String pattern = "MM/dd/yyyy";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		Calendar yesterday = Calendar.getInstance();
		yesterday.add(Calendar.DATE, -1);
		Date test = yesterday.getTime();
		String date2 = simpleDateFormat.format(test);

		return date2;
	}

	public static final String getYestMMDD() {
		String pattern = "MM/dd";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		Calendar yesterday = Calendar.getInstance();
		yesterday.add(Calendar.DATE, -1);
		Date test = yesterday.getTime();
		String date2 = simpleDateFormat.format(test);

		String date = simpleDateFormat.format(new Date());

		return date2;
	}

	public static final String getFridaysDate() {

		String pattern = "MM/dd/yyyy";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		Calendar todayCalendar = Calendar.getInstance();
		todayCalendar.add(Calendar.DATE, -3);
		Date dateTime = todayCalendar.getTime();
		String today = simpleDateFormat.format(dateTime);

		return today;
	}

	public static final String getFridaysDateMMDD() {
		String pattern = "MM/dd";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		Calendar todayCalendar = Calendar.getInstance();
		todayCalendar.add(Calendar.DATE, -3);
		Date dateTime = todayCalendar.getTime();
		String todayMMDD = simpleDateFormat.format(dateTime);

		return todayMMDD;
	}

	public static final String getTodaysDate() {
		String prevDate = "";

		String pattern = "MM/dd/yyyy";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		Calendar yesterday = Calendar.getInstance();
		yesterday.add(Calendar.DATE, 0);
		Date test = yesterday.getTime();
		String date2 = simpleDateFormat.format(test);

		return date2;
	}
}
