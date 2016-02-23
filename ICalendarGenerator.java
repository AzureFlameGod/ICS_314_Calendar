import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;
/**
 * The UI class
 * @author DiRienzo, Vincent
 *
 */
public class ICalendarGenerator {

	public static void main(String[] args) throws FileNotFoundException {
		
		
		ICalendar calendar = new ICalendar();
		String choice = "";
		Scanner scanner = new Scanner(System.in);
		
		
		do{
			VEvent vEvent = new VEvent();
			System.out.print("Event Title: ");
			vEvent.setSummary(scanner.nextLine());
			System.out.print("Event Description: ");
			vEvent.setDescription(scanner.nextLine());
			System.out.print("Even Location: ");
			
			Calendar start;
			do{
				System.out.print("Start Date (dd-mm-yyyy hh:mm (AM/PM): ");
				start = parseDate(scanner.nextLine());
			}while(start == null);
			vEvent.setStart(start);
			
			Calendar end;
			do{
				System.out.print("End Date (dd-mm-yyyy hh:mm (AM/PM): ");
				end = parseDate(scanner.nextLine());
			}while(end == null);
			vEvent.setEnd(end);
			
			calendar.addVEvent(vEvent);
			
			System.out.println("\nDo you want to add more? (y/n) ");
			choice = scanner.nextLine();
		}while(choice.startsWith("y"));
		
		
		
		System.out.print("Enter the file name to save the calendar file: ");
		calendar.saveCalendar(new File(scanner.nextLine()));
		
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
