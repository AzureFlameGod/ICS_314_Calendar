import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Class to represent a calendar
 * @author 
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
		
		//sort events based on start time
		Collections.sort(events);
		addComments(); //add comments
		
		StringBuilder output = new StringBuilder();

		output.append("BEGIN:VCALENDAR");
		
		//add prod id and version
		output.append(ICalendarUtility.CRLF+"PRODID:"+PRODID);
		output.append(ICalendarUtility.CRLF+"VERSION:"+VERSION);
		
		//add all events toString
		for (VEvent vEvent : events) {
			output.append(ICalendarUtility.CRLF+vEvent.toString());
		}
		
		
		output.append(ICalendarUtility.CRLF+"END:VCALENDAR");
		
		
		return output.toString();
	}
	
	
	private void addComments(){
		
		//if no events return
		if(events.isEmpty())
			return;
		
		//set previous to first
		VEvent previous = events.get(0);
		
		for (int i = 1; i < events.size(); i++) {
			//get next
			VEvent next = events.get(i);
			
			//if both events have coordinates
			if(previous.getGeo()!=null && next.getGeo()!=null){
				//calculate geo distance
				double distance = ICalendarUtility.calcGeoDistance(previous.getGeo(), next.getGeo());
				//set comments
				previous.setComments(distance+" km to next event");
			}
			
			previous = next;
		}
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
