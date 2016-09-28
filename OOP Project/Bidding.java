import java.util.Comparator;
import java.util.Collections;

/**
 * 
 * Useful for creating bidding object
 * 
 * @author Matt Zielke
 *
 */
public class Bidding implements Comparable<Bidding>{
	private int itemID;
	private int userID;
	private DateTime timeOfBid;
	private double bidAmount;
	private int quantity;
	
	
	
	/**
	 * constructor:    creates a bid given parameters
	 * 
	 * @param item   represents bid's item ID
	 * @param user   represents bid's user ID
	 * @param time   represents bid's time of bid
	 * @param amount represents bid's amount
	 * @param quan   represents bid's quantity
	 */
	public Bidding (int item, int user, DateTime time, double amount, int quan) {

		itemID = item;
		userID = user;
		timeOfBid = time;
		bidAmount = amount;
		quantity = quan;
	}
	
	
	/**
	 * Method: gets time of bid
	 * 
	 * @return   returns the time of the bid
	 */
	public DateTime getTime() {
		return this.timeOfBid;
	}
	
	
	/**
	 * Method: gets item quantity
	 * 
	 * @return   returns the quantity of a certain bid
	 */
	public int getQuantity() {
		return this.quantity;
	}
	
	
	/**
	 * Method: gets bid amount
	 * 
	 * @return  returns the amount of a bid
	 */
	public double getAmount() {
		return this.bidAmount;
	}
	
	
	/**
	 * Method: gets item id bid is for
	 * 
	 * @return  id of a specific item
	 */
	public int getID() {
		return this.itemID;
	}
	
	/**
	 * Method: gets user id bid is for
	 * 
	 * @return  id of a specific user
	 */
	public int getUserID() {
		return this.userID;
	}
	
	
	
	// return a string representation of this bid
	/**
	 * replaces the default toString of Object class
     * @override
	 */
	public String toString() {
		return "[" + itemID + " " + userID + " " + timeOfBid + " " + bidAmount + " " + quantity + "]";
	}


	public int compareTo(Bidding myBid) {
        if (this.timeOfBid.compareTo(myBid.timeOfBid) < 0)
            return -1;
        else if (timeOfBid.compareTo(myBid.timeOfBid) > 0)
            return 1;
        else
                 
                return 0;
        }


	

		
}
