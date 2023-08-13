import java.util.Calendar;
import java.util.Scanner;

public class Practice3 {

	public static void main(String[] args) {

		int year, month;
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("Please Input Year:");
		year = scanner.nextInt();
		
		System.out.println("Please Input Month:");
		month = scanner.nextInt();
		
		printCalender(year, month);
	}
	
	 public static void printCalender(int year, int month) {

	        //input
	        Calendar calendar = Calendar.getInstance();
	        calendar.set(year, month - 1, 1);
	        int startDay = calendar.get(Calendar.DAY_OF_WEEK); 
	        int count = startDay - 1; 
	        int maxDay = maxDayInMonth(year, month);
	        System.out.println("Sun Mon Tue Wed Thu Fri Sat");
	        fillSpace(startDay);
	        for (int i = 1; i <= maxDay; i++) {
	            System.out.printf("%3d%s", i, " "); 
	            count++;
	            if (count >= 7) { 
	                count = 0;
	                System.out.print('\n');
	            }
	        }
	    }
	 
	 public static void fillSpace(int startDay) {
	        for (int i = 1; i < startDay; i++) {
	            System.out.printf("%4s", " ");
	        }
	    }
	 
	 public static int maxDayInMonth(int year, int month) {
	        int max = 30;
	        if (month == 1 | month == 3 | month == 5 | month == 7 | month == 8 | month == 10 | month == 12) max = 31;
	        else if (month == 2) max = 28;
	        else if (month == 2 & (year % 4 == 0) && (year % 100 != 0) || (year % 400 == 0)) max = 29;
	        return max;
	    }

}
