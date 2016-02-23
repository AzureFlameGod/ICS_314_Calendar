import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;
import java.util.TimeZone;
/**
 * Contains helper methods to generate iCalendar file
 * @author DiRienzo, Vincent
 *
 */
public abstract class ICalendarUtility {
	
	public static final String CRLF ="\r\n";

	/**
	 * Generates a unique UID
	 * @return the uid gnereated
	 */
	public static String generateUID(){
		
		//get domain name
		String domainName = "";
		try {
			domainName = InetAddress.getLocalHost().getHostName();
		} catch (UnknownHostException e) {	}
		
		//generate a random number
		long randomNum = Math.abs(new Random().nextLong());
		
		//return the random uid
		return toUTCDateTime(Calendar.getInstance())+"-"+randomNum+"@"+domainName;
	}
	
	/**
	 * To convert calendar to UTC formatted string
	 * @param date the calendar to be parsed
	 * @return the string representation
	 */
	public static String toUTCDateTime(Calendar date){
		if(date == null)
			return null;
		
		DateFormat formatter = new SimpleDateFormat("yyyyMMdd'T'hhmmss'Z'");
		formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
		return formatter.format(date.getTime());
	}
	
	//to add \ before comma in every address location
	public static String formatAddress(String address){
		return address.replaceAll(",", "\\\\,");
	}
	

	
}
