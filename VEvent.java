import java.util.Calendar;
/**
 * This represent a calendar event
 * @author DiRienzo, Vincent
 *
 */
public class VEvent {

	private String summary;
	private Calendar start;
	private Calendar end;
	private Calendar created;
	private String description;
	private String location;
	private String uid;
	
	public VEvent() {
		created = Calendar.getInstance();
		uid = ICalendarUtility.generateUID();
	}
	
	
	public String getStart() {
		return ICalendarUtility.toUTCDateTime(start);
	}
	
	public String getEnd() {
		return ICalendarUtility.toUTCDateTime(end);
	}
	
	public String getCreated() {
		return ICalendarUtility.toUTCDateTime(created);
	}
	
	public String getDescription() {
		return description == null? "" : description;
	}
	
	public String getSummary() {
		return summary == null? "" : summary;
	}
	
	public String getLocation() {
		return location == null? "" : ICalendarUtility.formatAddress(location);
	}
	

	
	
	
	
	public void setSummary(String summary) {
		this.summary = summary;
	}


	public void setStart(Calendar start) {
		this.start = start;
	}


	public void setEnd(Calendar end) {
		this.end = end;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public void setLocation(String location) {
		this.location = location;
	}


	public String toString() {
		StringBuilder output = new StringBuilder();
		output.append("BEGIN:VEVENT");
		if(start!=null)
			output.append(ICalendarUtility.CRLF+"DTSTART:"+getStart());
		if(end!=null)
			output.append(ICalendarUtility.CRLF+"DTEND:"+getEnd());
		output.append(ICalendarUtility.CRLF+"DTSTAMP:"+ICalendarUtility.toUTCDateTime(Calendar.getInstance()));
		output.append(ICalendarUtility.CRLF+"CREATED:"+getCreated());
		output.append(ICalendarUtility.CRLF+"UID:"+uid);
		output.append(ICalendarUtility.CRLF+"DESCRIPTION:"+getDescription());
		output.append(ICalendarUtility.CRLF+"SUMMARY:"+getSummary());
		output.append(ICalendarUtility.CRLF+"END:VEVENT");
		return output.toString();
	}
	
	
	
}
