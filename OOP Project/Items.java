import java.util.ArrayList;


/**
 * 
 * Useful for creating Item object
 * 
 * @author Matt Zielke
 *
 */
public class Items {
	private int itemID;
	private String category;
	private String name;
	private String description;
	private enum SaleType {
		FIX_PRICE, AUCTION, BOTH}
	SaleType saletype;
	private int quantity;
	private enum ItemCondition {
		NEW, USED}
	ItemCondition itemcondition;
	private double minStartingBid;
	private double increment;
	private double reserveAmount;
	private DateTime startDate;
	private int numberOfDays;
	private int sellerID;
	private int sellerFeedbackScore;
	private DateTime endDate;
	private ArrayList<Bidding> bids = new ArrayList<Bidding>();
	private ArrayList<DateTime> bidTimes = new ArrayList<DateTime>();
	private double insertionFee = 0;
	private double finalValueFee = 0;
	private double cost;
	private double customerMon=0;
	
	
	
	/**
	 * constructor:    creates an item given parameters
	 * 
	 * @param id         represents item's ID
	 * @param cat        represents item's category
	 * @param name       represents item's name
	 * @param type       represents item's sale type
	 * @param quan       represents item's quantity
	 * @param cond       represents item's condition
	 * @param startbid   represents item's starting bid
	 * @param increment  represents item's bid increment
	 * @param reserve    represents item's reserve amount
	 * @param start      represents item's starting bid
	 * @param days       represents item's number of days up for bidding
	 * @param seller     represents item's seller ID
	 * @param score      represents item's seller feedback score
	 * @param shipper    represents item's shipper ID
	 * @param description represents item's description
	 */
	public Items (int id, String cat, String itemname, String type, int quan, String cond, double startbid, double incr,
			 double reserve, DateTime start, int days, int seller, int score, String descrip, DateTime end) {

		itemID = id;
		category = cat;
		name = itemname;
		saletype = SaleType.valueOf(type);
		quantity = quan;
		itemcondition = ItemCondition.valueOf(cond);
		minStartingBid = startbid;
		increment = incr;
		reserveAmount = reserve;
		startDate = start;
		numberOfDays = days;
		sellerID = seller;
		sellerFeedbackScore = score;
		description = descrip;
		endDate = end;

	}
	
	
	/**
	 * Method: checks if a bid is valid
	 * 
	 * @param bid            represents bid object to check for validity
	 * @param itemsForSale   represents item bid is for
	 * @return    returns true or false depending on whether bid is valid or not
	 */
	public boolean bidIsValid(Bidding bid, Items itemsForSale) {
		double tempStartBid = this.minStartingBid;
		tempStartBid += itemsForSale.increment;

		
		if(bid.getTime().isBefore(itemsForSale.startDate)) {                   //check if bid date is valid
			System.out.println("Bid Date: " + bid.getTime() + " " + " Item start date: " + itemsForSale.startDate + " \n");
			System.out.println("Bid " + bid + " \n Has an invalid start date \n");
			return false;
		}
		else if(bid.getAmount() < itemsForSale.minStartingBid) {             //check if bid amount is valid
			System.out.println("Bid " + bid + " \n Has an invalid bid amount \n");
			return false;
		}																	
		else if(bid.getAmount() < (tempStartBid)) {							//check if bid is at least greater than increment
				if(bids.isEmpty())
					if(bid.getAmount() >= itemsForSale.minStartingBid){
						customerMon += (bid.getQuantity() * bid.getAmount());
						return true;
					}
			System.out.println("Bid " + bid + " \n Has an invalid bid amount. Bid must be more than or equal to increment \n");
			return false;
		}
		else if(bid.getTime().isAfter(itemsForSale.endDate)) {                     //check if bid is after end of auction
			System.out.println("Bid " + bid + " \n Has an invalid bid time. Bidding has closed \n");
			return false;
		}
		else
			customerMon += (bid.getQuantity() * bid.getAmount());
			return true;
	}
	
	/**
	 * Method: displays all bids for an item
	 * 
	 */
	public String displayBids() {
		String print = "Bid";
		for(int i=0; i<bids.size(); i++)
			print += bids.get(i);
			//System.out.println(bids.get(i));
		return print;
	}
	/**
	 * Method: gives the last bid 
	 * 
	 * @return  returns the last bid in the bids array list
	 */
	public Bidding getLastBid() {
		int lastIndex = (this.bids.size() - 1);
		return this.bids.get(lastIndex);
	}
	
	/**
	 * Method: gets number of bids
	 * 
	 * @return   returns the number of bids
	 */
	public int getNumBids() {
		return this.bids.size();
	}
	
	/**
	 * Method: gets DateTime of bids
	 * 
	 * @return   returns the DateTime of bids
	 */
	public ArrayList getBidTimes() {
		for(int i=0; i<this.bids.size(); i++)
			bidTimes.add(bids.get(i).getTime());
		return bidTimes;
	}
	
	/**
	 * Method: gets seller id
	 * 
	 * @return   returns the seller id
	 */
	public int getSellerID() {
		return this.sellerID;
	}
	
	/**
	 * Method: gets array list of bids
	 * 
	 * @return   returns array list of bids
	 */
	public ArrayList getBids() {
		return this.bids;
	}
	
	/**
	 * Method: gets item type
	 * 
	 * @return   returns the type if a certain item
	 */
	public String getType() {
	    String typec="";
        
	    if(this.saletype==SaleType.FIX_PRICE){
	      typec="FIXED";
	    }else if(this.saletype==SaleType.AUCTION){
	      typec="AUCTION";     
	    }else if(this.saletype==SaleType.BOTH){
	      typec="BOTH";     
	    }    
	    return typec; 
	}
	
	
	/**
	 * Method: gets item quantity
	 * 
	 * @return   returns the quantity of a certain item
	 */
	public int getQuantity() {
		return this.quantity;
	}
	
	
	/**
	 * Method: gets item id
	 * 
	 * @return   returns the id of a certain item
	 */
	public int getID() {
		return this.itemID;
	}
	
	
	
	/**
	 * Method: gets Item increment
	 * 
	 * @return   returns the increment of certain item
	 */
	public double getIncrement() {
		return this.increment;
	}
	
	
	/**
	 * Method: gets Item auction end date
	 * 
	 * @return   returns the end date of an item
	 */
	public DateTime getEnd() {
		return this.endDate;
	}
	
	
	/**
	 *Method: returns minimum starting bid 
	 * 
	 * @return   returns the starting bid of a certain item
	 */
	public double getStartBid() {
		return this.minStartingBid;
	}
	
	
	/**
	 *Method: returns start date of item 
	 * 
	 * @return   returns the DateTime an items auction started
	 */
	public DateTime getStartDate() {
		return this.startDate;
	}
	
	
	/**
	 *Method: returns reserve price
	 * 
	 * @return   returns the reserve price of an item
	 */
	public double getReserve() {
		return this.reserveAmount;
	}

	public void setInsertionFee(double fee) {
		this.insertionFee += fee;
	}
	
	public void setFinalValueFee(double fee) {
		this.finalValueFee = fee;
	}
	
	public double getInsertionFee() {
		return this.insertionFee;
	}
	
	public double getFinalValueFee() {
		return this.finalValueFee;
	}
	
	public String getDescription(){
		return this.description;
	}
	
	public double getCostCollected(){
		    
		  return this.cost; 
	}
	
	public double getCustMon() {
		return this.customerMon;
	}
	
	
	/**
	 * Method: adds a bid
	 * 
	 * @param bid   represents bid to be added to array list
	 */
	public void addBids(Bidding bid) {
		this.bids.add(bid);
	}
	
	/**
	 * Method: gets shipping and other fees
	 * 
	 * @return   returns amount of all fees collected
	 */
	public double getFees(){
		int lastIndex=this.bids.size()-1;
	    double insertFee = this.minStartingBid*.01;
	    double finalValFee =  this.bids.get(lastIndex).getAmount()*.10;//fee if item is sold
		    
	    if(this.getQuantity()==1){//fee for 1 item sold
	      cost+= (29.95 +finalValFee);      
	    }else if(this.getQuantity()>1){//fee for multiple items sold
	      cost+= (29.95 +finalValFee+((this.getQuantity()-1)*.10));
	    }
	      cost+=insertFee;//everyone gets an insertion fee
		      
	   return cost; 
	  }
	
	// return a string representation of this item
	/**
	 * replaces the default toString of Object class
     * @override
	 */
	public String toString() {
		return "[" + itemID + " " + category + " " + name + " " + saletype + " " + quantity + " " + itemcondition +
				" " + minStartingBid + " " + increment + " " + reserveAmount + " " + startDate + " " + numberOfDays + 
				 " " + sellerID + " " + sellerFeedbackScore + " " + description + "]";
	}
}
