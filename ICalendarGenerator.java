import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;
/**
 * The UI class
 * @Vincent DiRienzo
 *
 */
public class ICalendarGenerator {

	public static void main(String[] args) throws FileNotFoundException {
		
		
		ICalendar calendar = new ICalendar();
		String choice = "";
		Scanner scanner = new Scanner(System.in);
		
		
		do{
			//create vevent
			VEvent vEvent = new VEvent();
			
			//ask user evry detail in the event file
			System.out.print("Event Title: ");
			vEvent.setSummary(scanner.nextLine());
			System.out.print("Event Description: ");
			vEvent.setDescription(scanner.nextLine());
			System.out.print("Event Location: ");
			vEvent.setLocation(scanner.nextLine());
			
			//ask start date
			Calendar start;
			do{
				System.out.print("Start Date (dd-mm-yyyy hh:mm (AM/PM): ");
				start = parseDate(scanner.nextLine());
			}while(start == null);
			vEvent.setStart(start);
			
			
			//ask end date
			Calendar end;
			do{
				System.out.print("End Date (dd-mm-yyyy hh:mm (AM/PM): ");
				end = parseDate(scanner.nextLine());
			}while(end == null);
			vEvent.setEnd(end);
			
			//ask event coordinates
			System.out.print("Event coordinates (Longitude Latitute): ");
			vEvent.setGeo(scanner.nextDouble(), scanner.nextDouble());
			
			scanner.nextLine();
			
			//add event to calendar
			calendar.addVEvent(vEvent);
			
			//Asks user for input on classification
			System.out.println("Input classification (Public/Private/Confidential)");
			vEvent.setClassification(scanner.nextLine());
			
			
			//ask if user wants to add more
			System.out.println("\nDo you want to add more? (y/n) ");
			choice = scanner.nextLine();
		}while(choice.startsWith("y"));
		
		
		//ask for file name
		System.out.print("Enter the file name to save the calendar file: ");
		calendar.saveCalendar(new File(scanner.nextLine()));
		
		//close
		scanner.close();
		
	}
	
	/**
	 * Helper method to parse a date
	 * @param date the string representation of date
	 * @return the calendar with local time zone and date parsed
	 */
	private static Calendar parseDate(String date){
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