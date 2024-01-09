/** 
 * Prints the calendars of all the years in the 20th century.
 */
public class Calendar1 {	
    // Starting the calendar on 1/1/1900
	static int dayOfMonth = 1;   
	static int month = 1;
	static int year = 1900;
	static int dayOfWeek = 2;     // 1.1.1900 was a Monday
	static int nDaysInMonth = 31; // Number of days in January
	
	/** 
	 * Prints the calendars of all the years in the 20th century. Also prints the  
	 * number of Sundays that occured on the first day of the month during this period.
	 */
	public static void main(String args[]) {
	    int debugDaysCounter = 0; 
		int counter = 0;
		// Create a loop that will run as long as we are in the 20th century
	 	while (year < 2000) {
			// Identifies if the first day of the month is also a Sunday, counts it and advances one day
			if(dayOfWeek == 1 && dayOfMonth == 1 ) {
				System.out.println(dayOfMonth + "/" + month + "/" + year + " Sunday");
				counter++; 
				advance();
	 			debugDaysCounter++; 
			// Identifies the first Sunday of each month and prints it nicely and advances one day
			} else if (dayOfWeek == 1 && dayOfMonth < 8) {
				System.out.println(dayOfMonth + "/" + month + "/" + year + " Sunday");
				advance();
	 			debugDaysCounter++; 				
			// Simply prints the date and advances one day
			} else {
				System.out.println(dayOfMonth + "/" + month + "/" + year + "    " + dayOfWeek);	
				advance(); 
				debugDaysCounter++;
			}
			// For debugging purposes
	 		// if (debugDaysCounter == 500) { 
	 			// break;
	 		// }
        }
		/// Prints out how many special sundays were found
		System.out.println("During the 20th century, " + counter + " Sundays fell on the first day of the month");
	 }
	
	 // Advances the date (day, month, year) and the day-of-the-week.
	 // If the month changes, sets the number of days in this month.
	 // Side effects: changes the static variables dayOfMonth, month, year, dayOfWeek, nDaysInMonth.
	 private static void advance() {
		if (nDaysInMonth(month, year) == dayOfMonth)
		{
			dayOfMonth = 0; 
			month++;
		}
		if (month == 13){
			year++; 
			month = 1; 
			dayOfMonth = 0; 
		}
		if (dayOfWeek == 7) {
			dayOfWeek = 0;
		}
		dayOfMonth++;
		dayOfWeek++;

	 } 
		 
    // Returns true if the given year is a leap year, false otherwise.
	private static boolean isLeapYear(int year) {
	    if (year % 4 == 0 && year % 100 != 0) {
			return true;
		}
		if (year % 400 == 0) {
			return true;
		}
		return false;
	}
	 
	// Returns the number of days in the given month and year.
	// April, June, September, and November have 30 days each.
	// February has 28 days in a common year, and 29 days in a leap year.
	// All the other months have 31 days.
	private static int nDaysInMonth(int month, int year) {
		if (isLeapYear(year)) {
			if (month == 2) {
				return 29; 
			} else if ( month == 4 || month == 6 || month == 9 || month == 11) {
				return 30; 
			} else {
				return 31;
			}
		} else {
		   if (month == 2) {
				return 28; 
			} else if ( month == 4 || month == 6 || month == 9 || month == 11) {
				return 30; 
			} else {
				return 31;
			}
		}
	}
}