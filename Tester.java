import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
/**
 * Creates a simple ics file with one event
 * @author SONY
 *
 */
public class Tester {

	public static void main(String[] args) throws FileNotFoundException, ParseException {
		
		VEvent event = new VEvent();
		event.setSummary("Study for exam");
		event.setLocation("Hamilton Library");
		event.setStart(parseDate("12-05-2016 1:00 pm"));
		event.setEnd(parseDate("12-05-2016 2:00 pm"));
		event.setGeo(37.38, -122.082);
		ICalendar calendar = new ICalendar();
		calendar.addVEvent(event);
		
		calendar.saveCalendar(new File("test.ics"));
	
		System.out.println("File test.ics created with the sample event");
		
		
	
	}
	
	public static Calendar parseDate(String date){
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm a");
		sdf.setLenient(false);
		Calendar c =  new GregorianCalendar();
		try {
			c.setTime(sdf.parse(date));
			return c;
		} catch (ParseException e) {
			return null;
		}
		
		
	}
}
