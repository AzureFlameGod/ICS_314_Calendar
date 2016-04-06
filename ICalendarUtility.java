import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;
import java.util.TimeZone;
/**
 * Contains helper methods to generate iCalendar file
 * @author 
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
		
		DateFormat formatter = new SimpleDateFormat("yyyyMMdd'T'HHmmss'Z'");
		formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
		return formatter.format(date.getTime());
	}
	
	//to add \ before comma in every address location
	public static String formatAddress(String address){
		return address.replaceAll(",", "\\\\,");
	}
	

	/**
	 * Calculate distance between two locations
	 * @param location1 location 1
	 * @param location2 location 2
	 * @return distance
	 */
	public static double calcGeoDistance(Location location1, Location location2){
		double lng1 = location1.getLongitude();
		double lng2 = location2.getLongitude();
		
		
		
		double lat1 = location1.getLatitude();
		double lat2 = location2.getLatitude();
		
		 	double earthRadius = 6371; //meters
		    
		 	double dLat = Math.toRadians(lat2-lat1);
		    double dLng = Math.toRadians(lng2-lng1);
		    
		    double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
		               Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
		               Math.sin(dLng/2) * Math.sin(dLng/2);
		    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
		    
		    return (earthRadius * c);

	}
	
}
