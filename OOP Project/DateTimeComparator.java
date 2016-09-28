import java.util.Comparator;


public class DateTimeComparator implements Comparator<Bidding> {

	public int compare(Bidding bid1, Bidding bid2) {
		
		if(bid1.getTime().isAfter(bid2.getTime()))
			return 1;
		else if(bid1.getTime().isBefore(bid2.getTime()))
			return -1;
		else 
			return 0;
		
	}

}
