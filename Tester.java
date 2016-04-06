import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.plaf.synth.SynthSpinnerUI;
/**
 * Creates a simple ics file with one event
 * @author SONY
 *
 */
public class Tester {

	public static void main(String[] args) throws FileNotFoundException, ParseException {
		
		VEvent event1 = new VEvent();
		event1.setSummary("Study for exam");
		event1.setLocation("Hamilton Library");
		event1.setStart(parseDate("12-05-2016 1:00 PM"));
		event1.setEnd(parseDate("12-05-2016 2:00 PM"));
		event1.setGeo(37.38, -122.082);
		
		VEvent event2 = new VEvent();
		event2.setSummary("EVENT2");
		event2.setLocation("LOCATION2");
		event2.setStart(parseDate("12-05-2016 12:00 AM"));
		event2.setEnd(parseDate("12-05-2016 2:00 AM"));
		event2.setGeo(15.38, 12.082);
		
		
		
		ICalendar calendar = new ICalendar();
		calendar.addVEvent(event1);
		calendar.addVEvent(event2);
		
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
