/*************************************************************************
 *  Compilation:  javac DateTime.java <br>
 *  Execution:    java DateTime <br>
 *************************************************************************/

/**
 * A simple data type DateTime to be fleshed out for Project 1 (CSE 1325, Spring 2014)
 * <br>
 * Useful for creating DateTime objects (a la DATETIME type supported in Oracle)
 * 
 * @author skeleton developed by Sharma Chakravarhty 
 * @author To be completed by the student
 * @see java.util.Calendar
 * 
 */
public class DateTime {

	private Date date; // from Date Class
	private Time time; // from Time class
	
        /**
	 * Constructor: default; returns today's date and time
	 */
        public DateTime() {
            date = new Date();
            time = new Time();
        }
        
        public Date getDate(){
            return date;
        }
        
        public Time getTime(){
            return time;
        }
	/**
	 * Constructor: Does bounds-checking to ensure object represents a valid
	 * date and time
	 * 
	 * @param m    represents the month between 1 and 12
	 * @param d    represents the date between 1 and the corresponding number
	 *             from array DAYS
	 * @param y    represents the year
	 * @exception RuntimeException
	 *                if the date is invalid
	 */
	public DateTime(int mo, int d, int y, int h, int mi, int s, int hun) {
		date = new Date(mo, d, y);
                time = new Time(h, mi, s, hun);
	}

	/**
	 * Constructor: Does bounds-checking to ensure string represents a valid
	 * date
	 * 
	 * @param sDate    represents a date given in format mm-dd-yyyy,hh:mm:ss:hund as a string
	 * @exception RuntimeException if the date is invalid
	 */
	public DateTime(String sDateTime) {
		int m, d, y;
		String[] chopDateTime = sDateTime.split(",");
                //System.out.println(chopDateTime[0] + "," + chopDateTime[1]);
                date = new Date(chopDateTime[0]);
                time = new Time(chopDateTime[1]);
	}

    
	// is this DateTime after b?
	/**
	 * compares two DateTime objects
	 * 
	 * @param b DateTime object
	 * @return true if this DateTime is after DateTime b
	 */
	public boolean isAfter(DateTime b) {
		if (this.compareTo(b) > 0)
                    return true;
        return false; 
	}

	// is this DateTime a before b?
	/**
	 * compares two date objects
	 * 
	 * @param b DateTime object
	 * @return true if this DateTime is before DateTime b
	 */
	public boolean isBefore(DateTime b) {
		if (this.compareTo(b) <= 0)
            return true;
        return false; 
	}


	// comparison function between two dates
	/**
	 * compares two DateTime objects
	 * 
	 * @param b    DateTime object
	 * @return     0 if this DateTime is the same as DateTime b <br>
	 *             negative integer if this DateTime is earlier than DateTime b <br>
	 *             positive integer if this DateTime is after DateTime b
	 */
	public int compareTo(DateTime b) {
		//need to flesh out
            //if (this.getDate().compareTo(b.getDate()) < 0)
            if (date.compareTo(b.date) < 0)
                return -1;
            else if (date.compareTo(b.date) > 0)
                return 1;
            if (date.compareTo(b.date) == 0){
                if(date.compareTo(b.date) < 0)
                    return -1;
                else if(time.compareTo(b.time) > 0)
                    return 1;
                     
                    return 0;
            }
            return 0; //not actually needed
	}

	
	// advance DateTime by days
	/**
	 * advances the datetime by days
	 * 
	 * @param d    represents the  days to advance
	 * @return     modifies the same DateTime object)
	 */
	
        public DateTime addDays(int d){
            for (int i = 0; i < d; i++) 
                this.date.next();
            return this;  
	}
        
        public DateTime addMonths(int m){
            this.date.addMonths(m);
            return this;
        }
        
        public DateTime addYears(int y){
            this.date.addMonths(y*12);
            return this;
        }
        
        public DateTime addHours(int h){
            int overflow = this.time.addHours(h);
            if (overflow > 0)
                addDays(overflow);
            return this;
        }
        
        public DateTime addMinutes(int m){
            int overflow = this.time.addMinutes(m);
            if (overflow > 0) addDays(overflow);
      
            return this;   
	}
        
        public DateTime addSeconds(int s){
            int overflow = this.time.addSeconds(s);
            if(overflow > 0)
                addDays(overflow);
            return this;
        }

	// return a string representation of this date
	/**
	 * replaces the default toString of Object class
         * @override
	 */
	public String toString() {
            //need to flesh out
      
            return date + "," + time;
	}
        
    public DateTime addTime(Time t1){
            time.addTime(t1);
            return this;
    }
        
    public DateTime subtractTime(Time t1){
            time.subtractTime(t1);
            return this;
    }
        
    public DateTime addDateTime(DateTime date1){
            //add the date first
            addDays(date1.date.getDay());
            addMonths(date1.date.getMonth());
            addYears(date1.date.getYear());
            //add the time then and update the date if overflow happens
            int overflow = time.addTime(date1.time);
            if(overflow > 0)
                addDays(overflow);
            return this;
        }
        /*
         * sdas: This has been implemented as discussed below:
         * If first date is after the second date only then subtraction can go through
         * The days between the two days are evaluated
         * the days are added to the start of calender at 1-1-0 (mm-dd-yy) to find the subtracted date
         * subtract 29 as we have added 1 month and 1 day at the start
         * for subtracting time if first time is less than second
         * we find the difference from 2nd time to 1st time and subtract it from 23:59:59:0 and finally add 1 sec
         * one day is decremented as 1st time < 2nd time by using function previous in Date class
         * this function needs to be reevaluated critically
         */
        public DateTime subtractDateTime(DateTime date1){
            //subtracting the date first
            if(date.isBefore(date1.date)){
                System.out.println("Can not subtract");
                return this;
                
            }
            else{
                int daysBetwn = date1.date.daysBetween(date);
                System.out.println("Days between ="+daysBetwn);
                Date base = new Date(1,1,0);
                for(int i=0;i<daysBetwn-29;i++){
                    this.date = base.next();
                }
                //now subtract time
                if(this.time.isBefore(date1.time)){
                    date1.time.subtractTime(time);
                    //int overflow = 1;
                    Time t1 = new Time(23,59,59,0);
                    t1.subtractTime(date1.time);
                    this.time = t1;
                    this.time.addSeconds(1);
                    this.date.previous();
                    return this;
                }
                else{
                    this.time.subtractTime(date1.time);
                    return this;
                }
                
                //System.out.println("overflow ="+overflow);
                //return this;
            }
        }
	/**
	 * Code for testing the DateTime class
	 * 
	 * @param args Array of String arguments
	 */

	public static void main(String[] args) {

        DateTime today = new DateTime();
	    System.out.println("Testing no arg constructor: "+today);
    
        DateTime dateTime1 = new DateTime("2-28-2016,23:59:59:99");
        System.out.println("testing string as input:" + dateTime1);
        System.out.println("Adding 26 hrs to"+dateTime1+" gives "+dateTime1.addHours(26));
        
        DateTime dateTime2 = new DateTime("2-27-2017,23:59:00:00");
        System.out.println("Adding 24hrs to "+dateTime2+" gives "+dateTime2.addHours(24));
        System.out.println("Adding 7 min to "+dateTime2+ "gives "+dateTime2.addMinutes(7));
        
        dateTime2 = new DateTime("12-31-2013,11:30:00:00");
        System.out.println("Adding 13 hrs to "+dateTime2+" gives "+dateTime2.addHours(13));
        System.out.println("Adding 31 min to "+dateTime2+ "gives "+dateTime2.addMinutes(31));
        
        dateTime2 = new DateTime("1-1-2012,23:59:00:00");
        System.out.println("Adding 86400 sec to "+dateTime2+" gives "+dateTime2.addSeconds(86400));
        System.out.println("Adding 3600 sec to "+dateTime2+ "gives "+dateTime2.addSeconds(3600));
        
        DateTime dateTime3 = new DateTime("1-11-2014,23:59:57:00");
        System.out.println("Adding 4 sec to "+dateTime3+" gives "+dateTime3.addSeconds(4));
        
        dateTime1 = new DateTime(2,28,2015,12,30,30,0);
        System.out.println("testing 7 arg constructor with initial date: "+dateTime1);
        System.out.println("Increasing day by 366 "+dateTime1.addDays(366));
        System.out.println("Increasing  month by 12 "+dateTime1.addMonths(12));
        System.out.println("Increasing  year by 2 "+dateTime1.addYears(2));
        
        dateTime1 = new DateTime(2,28,2016,12,30,30,0);
        System.out.println("Initial date is "+dateTime1);
        System.out.println("Increasing day by 365 "+dateTime1.addDays(365));
        System.out.println("Increasing  month by 11 "+dateTime1.addMonths(11));
        System.out.println("Increasing  year by 30 "+dateTime1.addYears(30));
        
        dateTime2 = new DateTime(12,31,2013,12,45,30,0);
        dateTime3 = new DateTime(12,31,2013,12,45,29,99);
        
        System.out.println("checking "+dateTime2+" is before "+dateTime3+" gives "+dateTime2.isBefore(dateTime3));
        System.out.println("checking "+dateTime2+" is after "+dateTime3+" gives "+dateTime2.isAfter(dateTime3));
        
        dateTime2 = new DateTime(12,30,2013,12,45,30,0);
        dateTime3 = new DateTime(12,31,2013,12,45,29,99);
        
        System.out.println("checking "+dateTime2+" is before "+dateTime3+" gives "+dateTime2.isBefore(dateTime3));
        System.out.println("checking "+dateTime2+" is after "+dateTime3+" gives "+dateTime2.isAfter(dateTime3));
        
        dateTime2 = new DateTime(12,31,2012,12,45,30,0);
        dateTime3 = new DateTime(8,31,2013,12,45,29,99);
        
        System.out.println("checking "+dateTime2+" is before "+dateTime3+" gives "+dateTime2.isBefore(dateTime3));
        System.out.println("checking "+dateTime2+" is after "+dateTime3+" gives "+dateTime2.isAfter(dateTime3));
        
        dateTime2 = new DateTime(12,31,2013,11,45,29,99);
        dateTime3 = new DateTime(12,31,2013,12,45,29,99);
        
        System.out.println("checking "+dateTime2+" is before "+dateTime3+" gives "+dateTime2.isBefore(dateTime3));
        System.out.println("checking "+dateTime2+" is after "+dateTime3+" gives "+dateTime2.isAfter(dateTime3));
        
        dateTime2 = new DateTime(12,31,2013,12,44,59,0);
        dateTime3 = new DateTime(12,31,2013,12,45,0,0);
        
        System.out.println("checking "+dateTime2+" is before "+dateTime3+" gives "+dateTime2.isBefore(dateTime3));
        System.out.println("checking "+dateTime2+" is after "+dateTime3+" gives "+dateTime2.isAfter(dateTime3));
        
        dateTime2 = new DateTime(12,31,2013,12,45,29,98);
        dateTime3 = new DateTime(12,31,2013,12,45,29,99);
        
        System.out.println("checking "+dateTime2+" is before "+dateTime3+" gives "+dateTime2.isBefore(dateTime3));
        System.out.println("checking "+dateTime2+" is after "+dateTime3+" gives "+dateTime2.isAfter(dateTime3));
        
        dateTime2 = new DateTime(12,31,2013,13,50,30,0);
        dateTime3 = new DateTime("12-29-2014,12:45:29:0");
        
        System.out.println("checking "+dateTime2+" is before "+dateTime3+" gives "+dateTime2.isBefore(dateTime3));
        System.out.println("checking "+dateTime2+" is after "+dateTime3+" gives "+dateTime2.isAfter(dateTime3));
        
        System.out.println("Comparing dateTimes now");
        
        dateTime2 = new DateTime(12,31,2013,13,50,30,0);
        dateTime3 = new DateTime("12-29-2014,12:45:29:0");
        
        System.out.println("Comparing "+dateTime2+" with "+dateTime3+" gives "+dateTime2.compareTo(dateTime3));
        
        dateTime2 = new DateTime(12,31,2013,13,50,30,0);
        dateTime3 = new DateTime("12-29-2013,12:45:29:0");
        
        System.out.println("Comparing "+dateTime2+" with "+dateTime3+" gives "+dateTime2.compareTo(dateTime3));
        
        dateTime2 = new DateTime(12,29,2014,12,45,29,0);
        dateTime3 = new DateTime("12-29-2014,12:45:29:0");
        
        System.out.println("Comparing "+dateTime2+" with "+dateTime3+" gives "+dateTime2.compareTo(dateTime3));
        
        dateTime2 = new DateTime(12,29,2014,12,45,31,0);
        dateTime3 = new DateTime("12-29-2014,12:45:29:0");
        
        System.out.println("Comparing "+dateTime2+" with "+dateTime3+" gives "+dateTime2.compareTo(dateTime3));
        
        dateTime2 = new DateTime(12,29,2014,12,44,31,0);
        dateTime3 = new DateTime("12-29-2014,12:45:29:0");
        
        System.out.println("Comparing "+dateTime2+" with "+dateTime3+" gives "+dateTime2.compareTo(dateTime3));
        
        System.out.println("Adding dateTime");
        dateTime2 = new DateTime(1,20,2014,11,46,31,0);
        dateTime3 = new DateTime("2-20-2013,10:45:29:0");
        System.out.println(dateTime2+" + "+dateTime3);
        System.out.println(dateTime2.addDateTime(dateTime3));
        
        dateTime2 = new DateTime(8,31,2014,11,46,31,0);
        dateTime3 = new DateTime("7-31-2013,10:45:29:0");
        System.out.println(dateTime2+" + "+dateTime3);
        System.out.println(dateTime2.addDateTime(dateTime3));
        
        dateTime2 = new DateTime(8,31,2014,11,46,31,0);
        dateTime3 = new DateTime("7-31-2013,20:30:30:0");
        System.out.println(dateTime2+" + "+dateTime3);
        System.out.println(dateTime2.addDateTime(dateTime3));
        
        System.out.println("Subtracting dateTime");
        
        dateTime2 = new DateTime(6,21,2014,10,45,29,0);
        dateTime3 = new DateTime("4-20-2013,11:46:31:0");
        System.out.println(dateTime2+" - "+dateTime3);
        System.out.println(dateTime2.subtractDateTime(dateTime3));
        
        dateTime2 = new DateTime(4,20,2014,14,45,29,0);
        dateTime3 = new DateTime("5-21-2013,11:46:31:0");
        System.out.println(dateTime2+" - "+dateTime3);
        System.out.println(dateTime2.subtractDateTime(dateTime3));
	}
}

