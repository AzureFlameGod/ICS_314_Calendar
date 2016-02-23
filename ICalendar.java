import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Class to represent a calendar
 * @author DiRienzo, Vincent
 *
 */
public class ICalendar {
	
	

	private static final String PRODID = "-//Buisiness Name//Product Name/En";
	private static final String VERSION = "2.0";
	
	
	private List<VEvent> events;
	
	
	/**
	 * Default constructor
	 */
	public ICalendar() {
		events = new ArrayList<>();
	}
	
	/**
	 * Add an even to list of events
	 * @param newVEvent the new event to add
	 */
	public void addVEvent(VEvent newVEvent){
		events.add(newVEvent);
	}
	
	@Override
	public String toString() {
		StringBuilder output = new StringBuilder();

		output.append("BEGIN:VCALENDAR");
		output.append(ICalendarUtility.CRLF+"PRODID:"+PRODID);
		output.append(ICalendarUtility.CRLF+"VERSION:"+VERSION);
		for (VEvent vEvent : events) {
			output.append(ICalendarUtility.CRLF+vEvent.toString());
		}
		output.append(ICalendarUtility.CRLF+"END:VCALENDAR");
		
		
		return output.toString();
	}
	
	
	/**
	 * To save the calendar to ics file
	 * @param outputFile the file where calendar needs to be saved
	 * @throws FileNotFoundException if file cannot not be created
	 */
	public void saveCalendar(File outputFile) throws FileNotFoundException{
		PrintWriter printWriter = new PrintWriter(outputFile);
		printWriter.print(toString());
		printWriter.close();
	}
	
}
