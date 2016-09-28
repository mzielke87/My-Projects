/*************************************************************************
 *  Compilation:  javac Time.java <br>
 *  Execution:    java Time <br>
 *************************************************************************/

/**
 * A simple time type (CSE 1325, Fall 2012)
 * <br>
 * Useful for creating Time objects
 * 
 * @author Sharma Chakravarhty (Sent the skeleton 08/25/2012)
 * @author extended by Yuanzhe Cai & Das (08/27/2012)
 * @see java.util.Calendar
 * @since 09/04/2012, Fixed a logical error to wrapping around 24 hour and add 
 *  addHour(), addMinute(), addSecond() (Cai)
 */

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Time implements DateConstants{

	private  int hour; // hour (larger or equal to than 0)
	private  int minute; // minute (between 0 and 59)
	private  int second; // second (between 0 and 59)
	private  int hundredth; // hundredth (between 0 and 99)

	static private final int HOUR_CHANGE_TIME = 60 * 60 * 100;
	static private final int MINUTE_CHANGE_TIME = 60 * 100;
	static private final int SECOND_CHANGE_TIME = 100;
	static private final int HOURS_FOR_DAY = 24;

	/**
	 * Constructor: default; returns today's Time
	 */
	public Time() {
		GregorianCalendar c = new GregorianCalendar();
		hour = c.get(Calendar.HOUR); 
		minute = c.get(Calendar.MINUTE);
		second = c.get(Calendar.SECOND);
		hundredth = 0;
	}

	/**
	 * Constructor: create a time with a given hour, minute, second and
	 * hundredth ; fills in valid time;
	 * 
	 * @param hour
	 *            represents the hour
	 * @param minute
	 *            represents the minute between 0 and 59
	 * @param second
	 *            represents the second between 0 and 59
	 * @param hundredth
	 *            represents the hundredth between 0 and 99
	 */
	public Time(int hour, int minute, int second, int hundredth) {

		if (!isValid(hour, minute, second, hundredth))
			throw new RuntimeException("Time 1: Invalid time " + hour + ":" + 
                            minute + ":" + second + ":" + hundredth);

		this.hour = hour;
		this.minute = minute;
		this.second = second;
		this.hundredth = hundredth;
	}

/**
	 * Constructor: creates a valid time object with a string in hh:mm:ss format as a string;
	 * 
	 * @param hour
	 *            represents the hour
	 * @param minute
	 *            represents the minute between 0 and 59
	 */
	public Time(String aTime) {

        this(Integer.parseInt(aTime.split(":")[ZEROI]), 
                Integer.parseInt(aTime.split(":")[ONEI]), 
                Integer.parseInt(aTime.split(":")[TWOI]), ZEROI);	
	}

	// return hundredth
	/**
	 * change a time to hundredth;
	 * 
	 */
	private int timeToHundredth() {
		return this.hour * this.HOUR_CHANGE_TIME + this.minute
				* this.MINUTE_CHANGE_TIME + this.second
				* this.SECOND_CHANGE_TIME + this.hundredth;
	}

	// return hundredths
	/**
	 * change hundredths to a time;
	 * returns extra days obtained in that conversion
     * 
	 */
	private int hundredthToTime(int hundredths) {

		int tmpHour = ((int) (hundredths / this.HOUR_CHANGE_TIME));

		int tmpMinute = (int) (hundredths - tmpHour * this.HOUR_CHANGE_TIME)
				/ this.MINUTE_CHANGE_TIME;

		int tmpSecond = (int) (hundredths - tmpHour * this.HOUR_CHANGE_TIME - tmpMinute
				* this.MINUTE_CHANGE_TIME)
				/ this.SECOND_CHANGE_TIME;

		int tmpHundredth = hundredths - tmpHour * this.HOUR_CHANGE_TIME
				- tmpMinute * this.MINUTE_CHANGE_TIME - tmpSecond
				* this.SECOND_CHANGE_TIME;

		int daysToAdd = tmpHour / this.HOURS_FOR_DAY;
        tmpHour = tmpHour % this.HOURS_FOR_DAY;
        
		//if (!isValid(tmpHour, tmpMinute, tmpSecond, tmpHundredth))
		//	throw new RuntimeException("Time 2: Invalid time" + hour + ":" + minute + ":" + second + ":" + hundredth);

        this.hour = tmpHour;
        this.minute = tmpMinute;
        this.second = tmpSecond;
        this.hundredth = tmpHundredth;
		return daysToAdd;
	}

	// return add one hour
	/**
	 * add an hour and returns a new Time object
	 * 
	 * @return returns a new Time object adding an hour
	 */
	int addHours() {
		return addHours(1);
	}

	// return add hours
	// This approach does not support to add the negative values.
	/**
	 * adds hours and returns a new Time object
	 * 
	 * @return returns a new Time object adding hours
	 */
	int addHours(int hours) {

		if (hours < 0)
			throw new RuntimeException("Time 3: Invalid hours:" + hour);

		int originalHundredths = timeToHundredth();

		int hundredths = originalHundredths + hours * this.HOUR_CHANGE_TIME;

		return hundredthToTime(hundredths);
	}

	// return add one minute
	/**
	 * add a minute and returns a new Time object
	 * 
	 * @return returns a new Time object adding an hour
	 */
	int addMinutes() {
		return addMinutes(1);
	}

	// return add minutes
	// This approach does not support to add the negative values.
	/**
	 * adds minutes and returns a new Time object
	 * 
	 * @return returns a new Time object adding minutes
	 */
	int addMinutes(int minutes) {

		if (minutes < 0)
			throw new RuntimeException("Time 4: Invalid minutes:" + minute );

		int originalHundredths = timeToHundredth();

		int hundredths = originalHundredths + minutes * this.MINUTE_CHANGE_TIME;

		return hundredthToTime(hundredths);

	}

	// return add one second
	/**
	 * add a second and returns a new Time object
	 * 
	 * @return returns a new Time object adding an hour
	 */
	int addSeconds() {
		return addSeconds(1);
	}

	// return add seconds
	// This approach does not support to add the negative values.
	/**
	 * adds seconds and returns a new Time object
	 * 
	 * @return returns a new Time object adding seconds
	 */
	int addSeconds(int seconds) {

		if (seconds < 0)
			throw new RuntimeException("Time 5: Invalid seconds:" + second);

		int originalHundredths = timeToHundredth();

		int hundredths = originalHundredths + seconds * this.SECOND_CHANGE_TIME;

		return hundredthToTime(hundredths);

	}

    int addHundredths() {
		return addHundredths(1);
	}

    int addHundredths(int hundredth) {

		if (hundredth < 0)
			throw new RuntimeException("Time 6: Invalid hundredths of a second" + hundredth);

		int originalHundredths = timeToHundredth();

		int hundredths = originalHundredths + hundredth;

		return hundredthToTime(hundredths);

	}

	// is this Time after t?
	/**
	 * compares two Time objects
	 * 
	 * @param t
	 *            Time object
	 * @return true if this Time is after Time t
	 */
	public boolean isAfter(Time t) {
		return compareTo(t) > 0;
	}

	// is this Time before t?
	/**
	 * compares two Time objects
	 * 
	 * @param t
	 *            Time object
	 * @return true if this Time is after Time t
	 */
	public boolean isBefore(Time t) {
		return compareTo(t) < 0;
	}

	// comparison function between two Times
	/**
	 * compares two Time objects
	 * 
	 * @param t
	 *            Time object
	 * @return 0 if this Time is the same as Time t <br>
	 *         negative integer if this Time is earlier than Time t <br>
	 *         positive integer if this Time is after Time t
	 */
	public int compareTo(Time t) {
		if (this.hour != t.hour)
			return this.hour - t.hour;
		if (this.minute != t.minute)
			return this.minute - t.minute;
		if (this.second != t.second)
			return this.second - t.second;

		return this.hundredth - t.hundredth;
	}

	// substract of Times
	/**
	 * substract two Time objects
	 * 
	 * @param t
	 *            Time object
	 * @return new Time object
	 */
	int subtractTime(Time t) {

		if (t.isAfter(this)) {
			System.out.println("T2 is greater than T1; -1 is returned");
            return -1;
		}

		int originalHundredths = timeToHundredth();

		int hundredths = originalHundredths - t.timeToHundredth();

		return hundredthToTime(hundredths);

	}

	// add  two Times
	/**
	 * add two Time objects
	 * 
	 * @param t
	 *            Time object
	 * @return new Time object
	 */
	int addTime(Time t) {

		int originalHundredths = timeToHundredth();

		int hundredths = originalHundredths + t.timeToHundredth();

		return hundredthToTime(hundredths);

	}

	// return a string representation of this Time
	/**
	 * replaces the default toString of Object class
	 */
	public String toString() {
		return "[" + this.hour + ":" + this.minute + ":" + this.second + ":"
				+ this.hundredth + "]";
	}

	/**
	 * Is the given time valid?
	 * 
	 * @param hour
	 *            , minute, second and hundredth
	 * @return false if hour exceeds 24 or is less than 1
	 * @return false if day exceeds the corresponding days for a month from
	 *         array DAYS
	 * @return false if the year is not a leap year and has 29 days
	 */
	private static boolean isValid(int hour, int minute, int second,
			int hundredth) {
		if (hour < 0 || hour > 24)
			return false;
		if (minute < 0 || minute > 60)
			return false;
		if (second < 0 || second > 60)
			return false;
		if (hundredth < 0 || hundredth > 99)
			return false;

		return true;
	}

	// return hour
	/**
	 * return hour
	 * 
	 */
	public int getHour() {
		return this.hour;
	}

	// return minute
	/**
	 * return minute
	 * 
	 */
	public int getMinute() {
		return this.minute;
	}

	// return second
	/**
	 * return second
	 * 
	 */
	public int getSecond() {
		return this.second;
	}

	// return hundredth
	/**
	 * return hundredth
	 * 
	 */
	public int getHundredth() {
		return this.hundredth;
	}

	public static void main(String[] args) {

		System.out.println("Test Default Constructor: ");

		Time current = new Time();
		System.out.println("Current Time is " + current);
		System.out.println("Current hour is " + current.getHour());
		System.out.println("Current minute is " + current.getMinute());
		System.out.println("Current second is " + current.getSecond());
		System.out.println("Current hundredth is " + current.getHundredth());

		System.out.println("\nTest Constructor (4 params): ");

		Time t1 = new Time(0, 20, 31, 12);
		System.out.println("Input t1 Time is " + t1);

		Time t2 = new Time(13, 0, 1, 1);
		System.out.println("Input t2 Time is " + t2);

		Time t3 = new Time(23, 59, 59, 99);
		System.out.println("Input t3 Time is " + t3);

		Time t4 = new Time(0, 0, 0, 0);
		System.out.println("Input t4 Time is " + t4);

        System.out.println("\nTest Constructor (hh:mm): ");

		t1 = new Time("10:34");
		System.out.println("Input t1 Time is " + t1);

		 t2 = new Time("13:45");
		System.out.println("Input t2 Time is " + t2);

		t3 = new Time("23:59");
		System.out.println("Input t3 Time is " + t3);

		t4 = new Time("0:1");
		System.out.println("Input t4 Time is " + t4);


		// testing addHours, addMinutes, addSeconds, and addHundredths
		System.out.println("\nTESTING add hour method");
		
		current = new Time(12, 59, 30, 99);
        System.out.println("Current Time: " + current);
        int daysToAdd = current.addHours();
		System.out.println("adding 1 hours to current means, adding: " + 
                    daysToAdd + " days and new time is: " + current);
        current = new Time(12, 59, 59, 99);
        System.out.println("Current Time: " + current);
		daysToAdd= current.addHours(14);
		System.out.println("adding 14 hours to current means, adding: " + 
                    daysToAdd + " days and new time is: " + current);
		
		System.out.println("\nTESTING addMinutes method");
		
		current = new Time(12, 59, 30, 99);
        System.out.println("Current Time: " + current);
        daysToAdd = current.addMinutes();
		System.out.println("adding 1 min to current means, adding: " + 
                                daysToAdd + " days and new time is: " + current);
		daysToAdd = current.addMinutes(1440);
		System.out.println("adding 1440 mins to current means, adding: " + 
                                daysToAdd + " days and new time is: " + current);
		
		System.out.println("\nTESTING addSeconds");
		current = new Time(23, 59, 59, 99);
        System.out.println("Current Time: " + current);
        daysToAdd = current.addSeconds();
		System.out.println("adding 1 sec to current means, adding: " + 
                                daysToAdd + " days and new time is: " + current);
		current = new Time(23, 59, 59, 99);
        System.out.println("Current Time: " + current);
        daysToAdd = current.addSeconds(86401);
		System.out.println("adding 86401 secs to current means, adding: " + 
                                daysToAdd + " days and new time is: " + current);
        
        System.out.println("\nTESTING addHundredths");
		current = new Time(12, 59, 59, 99);
        System.out.println("Current Time: " + current);
        daysToAdd = current.addHundredths();
		System.out.println("adding 1 hundredth to current means, adding: " + 
                                daysToAdd + " days and new time is: " + current);
		daysToAdd = current.addHundredths(10000);
		System.out.println("adding 10000 hundredths to current means, adding: " + 
                                daysToAdd + " days and new time is: " + current);

		// testing isAfter
		System.out.println("\nTESTING isAfter");
		t2 = new Time(23, 59, 59, 99);

		boolean isAfter = t2.isAfter(new Time(23, 59, 59, 99));
		System.out.println(t2 + " is after " + new Time(23, 59, 59, 99) + ": " + isAfter);

		t1 = new Time(23, 59, 59, 99);
        t2 = new Time(23, 59, 59, 98);
        System.out.println(t1 + " is after " + t2 + ":" + t1.isAfter(t2));

		// test isBefore
		System.out.println("\nTESTING isBefore");
		t1 = new Time(1, 20, 31, 12);
		t2 = new Time(0, 18, 11, 13);

		boolean isBefore = t2.isBefore(t1);
		System.out.println(t2 + " is before " + t1 + ": " + isBefore);

		t1 = new Time(1, 0, 0, 0);
		t2 = new Time(0, 59, 59, 59);
		System.out.println(t2 + " is before " + t1 + ": " + t2.isBefore(t1));

		t1 = new Time(0, 0, 0, 0);
		t2 = new Time(1, 59, 59, 59);
		isBefore = t2.isBefore(t1);
		System.out.println(t2 + " is before " + t1 + ": " + isBefore);

        t1 = new Time(0, 0, 0, 1);
		t2 = new Time(0, 0, 0, 2);
		System.out.println(t2 + " is before " + t1 + ": " + t2.isBefore(t1));

		// test subtractTime
		System.out.println("\nTESTING subtractTime");
		t1 = new Time(1, 58, 31, 12);
        t3 = new Time(1, 58, 31, 12);
		t2 = new Time(0, 58, 30, 11);
		int interval = t3.subtractTime(t2);
		System.out.println(t1 + " - " + t2 + " is " + t3 + " and subtract " + interval + " days");

/*
		t1 = new Time(1, 20, 31, 12);
		t2 = new Time(0, 0, 0, 0);
		interval = t1.subtractTime(t2);
		System.out.println(t1 + " - " + t2 + " is " + interval);

		t1 = new Time(23, 59, 59, 99);
		t2 = new Time(1, 59, 59, 99);
		interval = t1.subtractTime(t2);
		System.out.println(t1 + " - " + t2 + " is " + interval);

		t1 = new Time(1, 0, 0, 0);
		t2 = new Time(0, 59, 59, 59);
		interval = t1.subtractTime(t2);
		System.out.println(t1 + " - " + t2 + " is " + interval);

		t1 = new Time(0, 0, 0, 0);
		t2 = new Time(0, 0, 0, 0);
		interval = t1.subtractTime(t2);
		System.out.println(t1 + " - " + t2 + " is " + interval);

        t1 = new Time(0, 0, 0, 5);
		t2 = new Time(0, 0, 0, 4);
		interval = t1.subtractTime(t2);
		System.out.println(t1 + " - " + t2 + " is " + interval);

        t1 = new Time(0, 0, 0, 4);
		t2 = new Time(0, 0, 0, 5);
		interval = t1.subtractTime(t2);
		System.out.println(t1 + " - " + t2 + " is " + interval);

		System.out.println("\nTESTING addTime");
		t1 = new Time(1, 20, 31, 99);
		t2 = new Time(1, 1, 1, 1);
		Time totalTime = t1.addTime(t2);
		System.out.println(t1 + " + " + t2 + " is " + totalTime);

        t1 = new Time(23, 59, 59, 99);
		t2 = new Time(0, 0, 0, 1);
		totalTime = t1.addTime(t2);
		System.out.println(t1 + " + " + t2 + " is " + totalTime);

		t1 = new Time(1, 20, 31, 12);
		t2 = new Time(0, 0, 0, 0);

		totalTime = t1.addTime(t2);
		System.out.println(t1 + " add " + t2 + " is " + totalTime);

		t1 = new Time(1, 0, 0, 1);
		t2 = new Time(23, 59, 59, 99);
		totalTime = t1.addTime(t2);
		System.out.println(t1 + " add " + t2 + " is " + totalTime);

		t1 = new Time(23, 59, 59, 99);
		t2 = new Time(0, 0, 1, 0);
		totalTime = t1.addTime(t2);
		System.out.println(t1 + " add " + t2 + " is " + totalTime);

		t1 = new Time(1, 59, 59, 59);
		t2 = new Time(0, 1, 0, 0);

		totalTime = t1.addTime(t2);
		System.out.println(t1 + " add " + t2 + " is " + totalTime);

		t1 = new Time(0, 0, 0, 99);
		t2 = new Time(0, 0, 0, 1);

		totalTime = t1.addTime(t2);
		System.out.println(t1 + " add " + t2 + " is " + totalTime);
*/
		// test compareTo
		System.out.println("\nTESTING compareTo ");
		t1 = new Time(0, 0, 0, 99);
		t2 = new Time(0, 0, 0, 99);

		System.out.println(t1 + " compare to " + t2 + " is " + t1.compareTo(t2));

		t1 = new Time(23, 5, 8, 99);
		t2 = new Time(23, 5, 8, 98);

		System.out.println(t1 + " compare to " + t2 + " is " + t1.compareTo(t2));

		t1 = new Time(1, 4, 3, 34);
		t2 = new Time(8, 23, 13, 31);

		System.out.println(t1 + " compare to " + t2 + " is " + t1.compareTo(t2));

        //the following should generate exceptions
        //t1 = new Time(25, 0, 0, 0);
        //t2 = new Time(23, -2, 1, 0);
        //t2 = new Time(1, 1, 1, 100);
        
	}
}
