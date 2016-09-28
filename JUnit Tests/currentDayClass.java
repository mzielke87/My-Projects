package problem1;

public class CurrentDayClass {

	public int calcCurrentDay (DateClass date)
	{
		int result=0,daysIn[] = {0, 31, 0, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

		if ((date.getYear()%4==0) /*&& ((date.getYear()%100!=0) || (date.getYear() % 400==0))*/)
		  daysIn[2] = 29; 
		else
		  daysIn[2] = 28;

		for (int i=1;i<date.getMonth();i++)
			result+=daysIn[i];
		
		return result+=date.getDay();
	}
}

