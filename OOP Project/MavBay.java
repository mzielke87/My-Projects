import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

/**
 * 
 * Useful for creating an enterprise object
 * 
 * @author Matt Zielke
 *
 */
public class MavBay {
	private String enterprise;
	private ArrayList<SalariedEmployee> employee = new ArrayList<SalariedEmployee>();
	private ArrayList<SalariedEmployee> releasedEmployee = new ArrayList<SalariedEmployee>();
	private ArrayList<Customer> customer = new ArrayList<Customer>();
	private ArrayList<Items> itemsForSale = new ArrayList<Items>();
	private ArrayList<Items> itemsSold = new ArrayList<Items>();
	private ArrayList<ArrayList<Bidding>> bidsArrayList = new ArrayList<ArrayList<Bidding>>();
	private double totalFeeCollection;
	private double revenue;
	private int totalItemsSold = 0;
	private NumberFormat number = NumberFormat.getCurrencyInstance(Locale.US);
	
	/**
	 * Constructor: create an object MavBay with Enterprise name
	 * 
	 * @param ent  represents name of Enterprise
	 */
	public MavBay(String ent) {
		enterprise = ent;
	}
	
	/**
	 * Method: get items for sale array list
	 * 
	 * @return   returns the array list of items
	 */
	public String displayBids(int itemID) {
		String print = "Bid";
    	for(int i=0; i<itemsForSale.size(); i++) {
    		if(itemsForSale.get(i).getID() == itemID)
    			print += itemsForSale.get(i).displayBids();
    	}
    	return print;
	}
	
	/**
	 * Method: get employees
	 * 
	 * @return   returns the array list of employees
	 */
	public ArrayList getEmployees() {
		return this.employee;
	}
	
	/**
	 * Method: get bids
	 * 
	 * @return   returns the array list of bids
	 */
	public ArrayList getBids() {
		
		ArrayList<Bidding> tmp = new ArrayList<Bidding>();
		
		for(int i=0; i<itemsForSale.size(); i++)
			bidsArrayList.add(itemsForSale.get(i).getBids());
		for(int i=0; i<itemsSold.size(); i++)
			bidsArrayList.add(itemsForSale.get(i).getBids());
		
		for(int i=0; i<bidsArrayList.size(); i++)
			for(int j=0; j<bidsArrayList.get(i).size(); j++)
				tmp.add(bidsArrayList.get(i).get(j));
		
		return tmp;
	}
	
	/**
	 * Method: adds item
	 * 
	 * @param item   represents item to be added to array of items
	 */
	public void addItem(Items item) {
		itemsForSale.add(item);
	}
	
	/**
	 * Method: adds customer
	 * 
	 * @param cus   represents customer to be added to array of customers
	 */
	public void addCustomer(Customer cus) {
		customer.add(cus);
	}
	
	/**
	 * Method: adds employee
	 * 
	 * @param emp   represents employee to be added to array of employees
	 */
	public void addEmployee(SalariedEmployee emp) {
		employee.add(emp);
	}
	
	
	  /**
	  * Method: Computes salary
	  * 
	  *       
	  */
	 public String computeSalary(int eid, int hours, int month){
	   double salary;
	   String print = "Salary";
		  
	   for(int i=0; i<employee.size(); i++) {
		   if((employee.get(i).getID()) == eid)
			   salary = employee.get(i).computeSalary(hours);
		}
		 
		for(int i=0; i<employee.size(); i++) {
			if((employee.get(i).getID()) == eid) 
				print += employee.get(i);
				//System.out.println(employee.get(i));
		}
		
		return print;
	  }
	  
	  /**
	  * Method: Calculates the years of service of current employee
	  * 
	  *     
	  */
	  public int lengthOfService(int id){
	   int length = 0;
		  
		   for(int i=0; i<employee.size(); i++) {
			   if((employee.get(i).getID()) == id)
				   length = employee.get(i).lengthOfService();
			}
		   
		   return length;
		   
	  }
	  
	  /**
	   * Method: display employee with a certain id
	   * 
	   * @param id
	   * @return
	   */
	  public String employeeById(int id) {
		  String print = "Employee";
		  
			for(int i=0; i<employee.size(); i++) {
				if((employee.get(i).getID()) == id) 
					print += employee.get(i);	
				else if(0 == id)
					print += employee.get(i);
			}
			
			return print;
	  }
	
	
	/**
	 * Method:  Search for items for sale to add bid to
	 * 
	 * @param id   represents Item ID that bid was made on
	 * @param bid  represents bid object that will be added if Item ID is found
	 */
	public void addBids(int id, Bidding bid) {
		
		for(int i=0; i<itemsForSale.size(); i++) {
			if((itemsForSale.get(i).getID()) == id) {
				if(itemsForSale.get(i).bidIsValid(bid, itemsForSale.get(i))) {
						itemsForSale.get(i).addBids(bid);	
						System.out.println("Bid added succesfully \n");
				}
				return;
		}
				
	}
		
		System.out.println("Item does not match any items in inventory \n");
			
	}
	
	
	/**
	 * Method: calculates whether an item sold or not based on the bids that were accepted
	 * 
	 */
	public void isSold() {
		for(int i=0; i<itemsForSale.size(); i++) {
			if(itemsForSale.get(i).getBids().size() != 0)
				if("FIXED".equals(itemsForSale.get(i).getType())) {
					if(itemsForSale.get(i).getLastBid().getAmount() >= itemsForSale.get(i).getReserve()) {
						if(itemsForSale.get(i).getQuantity() >= itemsForSale.get(i).getLastBid().getQuantity()) {
							totalFeeCollection += (itemsForSale.get(i).getLastBid().getAmount() * itemsForSale.get(i).getLastBid().getQuantity());
							itemsForSale.get(i).setFinalValueFee(itemsForSale.get(i).getLastBid().getAmount() * itemsForSale.get(i).getLastBid().getQuantity());
							totalItemsSold += itemsForSale.get(i).getLastBid().getQuantity();
							itemsSold.add(itemsForSale.get(i));
						}
						else {
							totalFeeCollection += (itemsForSale.get(i).getLastBid().getAmount() * itemsForSale.get(i).getQuantity());
							itemsForSale.get(i).setFinalValueFee(itemsForSale.get(i).getLastBid().getAmount() * itemsForSale.get(i).getQuantity());
							totalItemsSold += itemsForSale.get(i).getQuantity();
							itemsSold.add(itemsForSale.get(i));
						}
							
					}
				}
				else if("BOTH".equals(itemsForSale.get(i).getType())) {
					if(itemsForSale.get(i).getLastBid().getAmount() >= itemsForSale.get(i).getReserve()) {
						if(itemsForSale.get(i).getQuantity() >= itemsForSale.get(i).getLastBid().getQuantity()) {
							totalFeeCollection += (itemsForSale.get(i).getLastBid().getAmount() * itemsForSale.get(i).getLastBid().getQuantity());
							itemsForSale.get(i).setFinalValueFee(itemsForSale.get(i).getLastBid().getAmount() * itemsForSale.get(i).getLastBid().getQuantity());
							totalItemsSold += itemsForSale.get(i).getLastBid().getQuantity();
							itemsSold.add(itemsForSale.get(i));
						}
						else {
							totalFeeCollection += (itemsForSale.get(i).getLastBid().getAmount() * itemsForSale.get(i).getQuantity());
							itemsForSale.get(i).setFinalValueFee(itemsForSale.get(i).getLastBid().getAmount() * itemsForSale.get(i).getQuantity());
							totalItemsSold += itemsForSale.get(i).getQuantity();
							itemsSold.add(itemsForSale.get(i));
						}
						
					}
				else {
					if(itemsForSale.get(i).getLastBid().getAmount() >= ((itemsForSale.get(i).getReserve()) * .95)) {
						totalFeeCollection += (itemsForSale.get(i).getLastBid().getAmount() * itemsForSale.get(i).getLastBid().getQuantity());
						itemsForSale.get(i).setFinalValueFee(itemsForSale.get(i).getLastBid().getAmount() * itemsForSale.get(i).getLastBid().getQuantity());
						totalItemsSold += itemsForSale.get(i).getLastBid().getQuantity();
						itemsSold.add(itemsForSale.get(i));
					}
					else
						System.out.println("Bid: " + itemsForSale.get(i).getLastBid() + " \nDid not meet reserve");
					}
				}
				else if("AUCTION".equals(itemsForSale.get(i).getType())) {
					if(itemsForSale.get(i).getLastBid().getAmount() >= ((itemsForSale.get(i).getReserve()) * .95)) {
						totalFeeCollection += (itemsForSale.get(i).getLastBid().getAmount() * itemsForSale.get(i).getLastBid().getQuantity());
						itemsForSale.get(i).setFinalValueFee(itemsForSale.get(i).getLastBid().getAmount() * itemsForSale.get(i).getLastBid().getQuantity());
						totalItemsSold += itemsForSale.get(i).getLastBid().getQuantity();
						itemsSold.add(itemsForSale.get(i));
					}
				else
					System.out.println("Bid: " + itemsForSale.get(i).getLastBid() + " \nDid not meet reserve");
				}
				
			}
		
	}
	/**
	 * Method: Adds insertion fee from bid
	 */
	public void addInsertionFee(int bidItemId) {
		for(int i=0; i<itemsForSale.size(); i++)
			if((itemsForSale.get(i).getStartBid()) == bidItemId) {
				itemsForSale.get(i).setInsertionFee((itemsForSale.get(i).getStartBid()) * .01);
				totalFeeCollection += ((itemsForSale.get(i).getStartBid()) * .01);
			}
	}
	
	/**
	 * Method: sets employee release date
	 * 
	 * @param empid   represents employee id to be released
	 */
	public void releaseEmployee(int empid, String date) {
		SalariedEmployee temp;
		boolean flag = false;
		Date dt;
		if(date.equals("*")) {
			dt = new Date();
		}else{
			dt= new Date(date);
		}  
		
		
		for(int i=0; i<employee.size(); i++)
			if((employee.get(i).getID()) == empid) {
				employee.get(i).setReleaseDate(dt);
				temp=employee.get(i);
		        releasedEmployee.add(temp);
				System.out.println(employee.get(i));
				flag = true;
			}
		if(flag)
			return;
		else
			System.out.println("Employee not found \n");
		
	}
	
	
	/**
	 * Method: displays all employees
	 * 
	 */
	public String displayEmployees() {
		String print = "Employee";
		for(int i=0; i<employee.size(); i++)
			print += employee.get(i);
			//System.out.println(employee.get(i));
		return print;
	}
	
	/**
	 * Method: displays all customers
	 * 
	 */
	public void displayCustomers() {
		for(int i=0; i<customer.size(); i++)
			System.out.println(customer.get(i));
		
	}
	
	/**
	 * Method: check if a string is a number
	 * 
	 * @param  tocheck  represents string to check
	 * 
	 * @return   true if it is a number and false if it is not
	 */
	public static boolean isNumeric(String tocheck)  
	{  
	  try  
	  {  
	    double num = Double.parseDouble(tocheck);  
	  }  
	  catch(NumberFormatException nfe)  
	  {  
	    return false;  
	  }  
	  return true;  
	}
	
	/**
	 * Method: displays items for sale
	 * 
	 */
	public void displayItemsForSale(String todisplayforsale) {
		int todisplayforsaleint=0;
	
		if(todisplayforsale.equals("*")) {
			for(int i=0; i<itemsForSale.size(); i++) {
				System.out.println("Items for sale: " + itemsForSale.get(i));
			}
			displayMenu();
		}
		else if(isNumeric(todisplayforsale)) {
			todisplayforsaleint = Integer.parseInt(todisplayforsale);
			for(int j=0; j<itemsForSale.size(); j++) {
				if(itemsForSale.get(j).getReserve() >= todisplayforsaleint) 
					System.out.println("Items with reserve price above " + todisplayforsale + ": " + itemsForSale.get(j));
			}
			displayMenu();
		}
		System.out.println("Incorrect entry \n");
			
		displayMenu();
	}
	
	
	/**
	 * Method: display items sold based on selection of user
	 * 
	 * @param todisplay  represents user selection of what type of item to print
	 */
	public String displayItemsSold(String todisplay) {
		String print = "Items";
		
		if(todisplay.equals("*")) {
			for(int i=0; i<itemsSold.size(); i++)
				print += itemsSold.get(i);
				//System.out.println("Items sold: " + itemsSold.get(i));
			return print;
		}
		else if(todisplay.contains("FIXED")) {
			for(int j=0; j<itemsSold.size(); j++)
				if("FIXED".equals(itemsSold.get(j).getType()))
					print += itemsSold.get(j);
					//System.out.println("Fixed price items sold: " + itemsSold.get(j));
			return print;
		}
		else if(todisplay.contains("AUCTION")) {
			for(int k=0; k<itemsSold.size(); k++)
				if("AUCTION".equals(itemsSold.get(k).getType()))
					print += itemsSold.get(k);
					//System.out.println("Auction items sold: " + itemsSold.get(k));
			return print;
		}
		else if(todisplay.contains("BOTH")) {
			for(int l=0; l<itemsSold.size(); l++)
				if("BOTH".equals(itemsSold.get(l).getType()))
					print += itemsSold.get(l);
					//System.out.println("Both items sold: " + itemsSold.get(l));
			return print;
		}
		
		System.out.println("No items to display \n");
		return null;
	}
	
	/**
	 * Method: compute revenue based on year
	 * 
	 * @param todisplay  represents what type of item to look for
	 * @param year       represents year of items sold 
	 */
	public double computeRevenue(String todisplay, int year) {
		revenue = 0;
		
		if(todisplay.equals("*")) {
			for(int i=0; i<itemsSold.size(); i++){
				if((itemsSold.get(i).getStartDate().getDate().getYear()) == year) {
						revenue += (itemsSold.get(i).getInsertionFee() + itemsSold.get(i).getFinalValueFee());
				}
			}
			return revenue;
			/*for(int i=0; i<itemsSold.size(); i++){
				if((itemsSold.get(i).getStartDate().getDate().getYear()) == year)
					System.out.println("All items sold: " + itemsSold.get(i));
			}*/
			//System.out.println("Total revenue for all items sold in year " + year + ": " + num + "\n");
		}
		else if(todisplay.contains("FIXED")) {
			for(int i=0; i<itemsSold.size(); i++){
				if((itemsSold.get(i).getStartDate().getDate().getYear()) == year) {
						revenue += (itemsSold.get(i).getInsertionFee() + itemsSold.get(i).getFinalValueFee());
				}
			}
			return revenue;
			/*for(int i=0; i<itemsSold.size(); i++){
				if((itemsSold.get(i).getStartDate().getDate().getYear()) == year)
					System.out.println("Fixed items sold: " + itemsSold.get(i));
			}*/
			//System.out.println("Total revenue for fixed items sold in year " + year + ": " + num + "\n");
		}
		else if(todisplay.contains("AUCTION")) {
			for(int i=0; i<itemsSold.size(); i++){
				if((itemsSold.get(i).getStartDate().getDate().getYear()) == year) {
						revenue += (itemsSold.get(i).getInsertionFee() + itemsSold.get(i).getFinalValueFee());
				}
			}
			return revenue;
			/*for(int i=0; i<itemsSold.size(); i++){
				if((itemsSold.get(i).getStartDate().getDate().getYear()) == year)
					System.out.println("Auction items sold: " + itemsSold.get(i));
			}*/
			//System.out.println("Total revenue for auction items sold in year " + year + ": " + num + "\n");
		}
		else if(todisplay.contains("BOTH")) {
			for(int i=0; i<itemsSold.size(); i++){
				if((itemsSold.get(i).getStartDate().getDate().getYear()) == year) {
						revenue += (itemsSold.get(i).getInsertionFee() + itemsSold.get(i).getFinalValueFee());
				}
			}
			return revenue;
			/*for(int i=0; i<itemsSold.size(); i++){
				if((itemsSold.get(i).getStartDate().getDate().getYear()) == year)
					System.out.println("Both auction and fixed items sold: " + itemsSold.get(i));
			}*/
			//System.out.println("Total revenue for both auction and fixed items sold in year " + year + ": " + num + "\n");
		}
		return revenue;
		//System.out.println("\n\n");
	}
	
	/**
	 * Method: displays items by a certain seller
	 * 
	 * @param sellId   represents seller id to search for
	 */
	public String displayItemsForSaleBySeller(int sellId, int ye) {
		String printForSale = "Items For Sale";
		
		System.out.println("Item(s) if any put up for sale by seller id-> " + sellId + ": ");
		for(int i=0; i<itemsForSale.size(); i++) {
			if(itemsForSale.get(i).getSellerID() == sellId)
				if(itemsForSale.get(i).getStartDate().getDate().getYear() == ye)
					printForSale += itemsForSale.get(i);
		}
		
		return printForSale;
	}
	
	/**
	 * Method: displays items by a certain seller
	 * 
	 * @param sellId   represents seller id to search for
	 */
	public String displayItemsSoldBySeller(int sellId, int ye) {
		String printSold = "Items Sold";
		
		for(int i=0; i<itemsSold.size(); i++) {
			if(itemsSold.get(i).getSellerID() == sellId)
				if(itemsSold.get(i).getStartDate().getDate().getYear() == ye)
					printSold += itemsSold.get(i);
		}
		
		return printSold;
		
	}
	
	/**
	 * Method: returns revenue by seller id for certain year
	 * 
	 * @param sellId
	 * @param ye
	 * @return
	 */
	public double computeRevenueBySellerId(int sellId, int ye) {
		revenue = 0;
		
		for(int i=0; i<itemsSold.size(); i++) {
			if(itemsSold.get(i).getSellerID() == sellId) 
				if(itemsSold.get(i).getStartDate().getDate().getYear() == ye)
					revenue += itemsSold.get(i).getFinalValueFee();
		}
		
		return revenue;
	}
	
	/**
	 * Method: Prints items sold by seller id and quantity sold
	 * 
	 * @param sellId
	 * @param ye
	 * @return
	 */
	public String printSellerInfo(int sellId, int ye) {
		String print = "Seller info";
		
		for(int i=0; i<itemsSold.size(); i++) {
			if(itemsSold.get(i).getSellerID() == sellId) 
				if(itemsSold.get(i).getStartDate().getDate().getYear() == ye) {
					print += (itemsSold.get(i) + "\n   Quantity sold: " + 
						itemsSold.get(i).getLastBid().getQuantity() +"\n    Amount sold for: " + itemsSold.get(i).getFinalValueFee());
			}
		}
		return print;
	}
	
	  /**
	  * Method: Prints the Max Number of bids and the Item with that max bid
	  * 
	  */
	public void displayMaxBid(){
	   int maxBidSold=0;
	   int maxBidForSale=0;
	   int maxBid=0;
	   int index1=0;
	   int index2=0;
	    for(int i=0;i<itemsSold.size();i++){     
	      if(itemsSold.get(i).getNumBids()>maxBidSold){
	        maxBidSold=itemsSold.get(i).getNumBids();
	        index1=i;
	     }
	    }
		for(int j=0;j<itemsForSale.size();j++){     
		  if(itemsForSale.get(j).getNumBids()>maxBidForSale){
		    maxBidForSale=itemsForSale.get(j).getNumBids();
		    index2=j;
		 }
	    } 
		
		if(maxBidSold > maxBidForSale) {
			System.out.println("Item with most amount of Bids: "+itemsSold.get(index1));
			System.out.println("Number of Bids: " + maxBidSold);
		}
		else {
			System.out.println("Item with most amount of Bids: "+itemsForSale.get(index2));
			System.out.println("Number of Bids: " + maxBidForSale);
	    }
	  }
	
	/**
	 * Method:  displays project 2 menu for user input
	 * 
	 */
	public void displayMenu() {
		String num = number.format(totalFeeCollection);
		Scanner input = new Scanner(System.in);
		
		System.out.println("Please enter selection based on following menu \n");
		System.out.println("1) List all employees with details including specialization (no input) \n"
				+ "2) List items to be sold with details (* means all, amount means list items whose reserve amount is "
				  + "above that amount specified) including their min starting bid, duration, etc. \n"
				+ "3) List all the customers with details (no input) \n"
				+ "4) Display items sold ('*', 'fixed', or 'auction' or 'both' to list all items or items"
				+ " sold in that category) \n"
				+ "5) Display total fee collection \n"
				+ "6) Display items that received maximum number of bids irrespective of whether the item was"
				+ " sold or not and including fixed price bids (no input) \n"
				+ "7) Release an employee (input: employee id) \n"
				+ "8) Exit program");
		
		int myint = input.nextInt();
		
		switch(myint) {
		
		case 1: 
			displayEmployees();
			
		case 2:
			System.out.println("Please input '*' for all, or an amount to display all items for sale above that amount \n");
			String todisplayforsale = input.next().toString().toLowerCase();
			displayItemsForSale(todisplayforsale);
			
		case 3:
			displayCustomers();
			
		case 4:
			System.out.println("Please input '*' for all, 'fixed', or 'auction' for type of items sold to display \n");
			String todisplay = input.next().toString().toUpperCase();
			displayItemsSold(todisplay);
			
		case 5:
			for(int i=0;i<itemsSold.size();i++)
				totalFeeCollection += itemsSold.get(i).getFees();
			
			System.out.println("Total fee's collected is: " + num);
			
			displayMenu();
			
		case 6:
			displayMaxBid();
				
			displayMenu();
			
		case 7:
			System.out.println("Please input employee id to release");
			int myempid = input.nextInt();
			releaseEmployee(myempid, "*");
			
		case 8:
			System.out.println("Thank you for using MavBay \n");
			System.exit(0);
			
		default:
			System.out.println("invalid entry please re-enter choice \n");
			displayMenu();
		}
		
		
	}
	
	 public String [] convertCust(ArrayList<Customer> cust){
		  String tmp="";
		  String[] empList= new String[cust.size()];
		  for(int i=0;i<empList.length;i++){
			  tmp= Integer.toString(cust.get(i).getID())+"    "+cust.get(i).firstName+" "+cust.get(i).lastName;
			  empList[i]=tmp;
		  }	  
		  return empList;
	  }
	
	 public String [] convertEmp(ArrayList<SalariedEmployee> emp){
		  String temp="";
		  String[] empList= new String[emp.size()];
		  for(int i=0;i<empList.length;i++){
			  temp= Integer.toString(emp.get(i).getID())+"    "+emp.get(i).firstName+" "+emp.get(i).lastName;
			  empList[i]=temp;
		  }	  
		  return empList;
	  }
	  
	  public String [] convertItem(ArrayList<Items> item){
		  String temp="";
		  String[] itemList= new String[item.size()];
		  for(int i=0;i<itemList.length;i++){
			  temp = Integer.toString(item.get(i).getID())+"    "+item.get(i).getDescription();
			  itemList[i]=temp;
		  }	  
		  return itemList;
	  }
	  
	  public String empServiceLength(){
		  String tmp="";
		  int length=0;
		  
		  for(int i=0;i<employee.size();i++){
			  if(employee.get(i).lengthOfService()>length){
				  length=employee.get(i).lengthOfService();
			  }
		  }
		  for(int i=0;i<employee.size();i++){
			  if(employee.get(i).lengthOfService()==length){
				  tmp += "ID: "+employee.get(i).getID()+" "+employee.get(i).firstName+" "
			  +employee.get(i).lastName+" Years Served "+employee.get(i).lengthOfService()+ "\n";
			  }
		  }
		  System.out.println("Employees: \n"+tmp);
		  return tmp;
	  }
	  
	  public ArrayList<SalariedEmployee> getReleasedEmployees(){
		  return releasedEmployee;
	  }
	  
	  public ArrayList<Customer> getCustomer(){
		  return customer;
	  }

	  public ArrayList<Items> getItems(){
		  return this.itemsForSale;
	  }
	  
	  public ArrayList<Items> getItemsSold(){
		  return this.itemsSold;
	  }
	  
	  public String stringEmp(int ind){
		  
		  return this.employee.get(ind).toString();
	  }
	   
	  public String stringReleasedEmp(int index){
		  
		  return this.releasedEmployee.get(index).toString();
	  }
	  
	  public String stringCustRev(){
		  double tmp=0;
		  double low=0;
		  double high=0;
		  int ind=0;
		  String string="";
		  
		  for(int i=0;i<customer.size();i++){
			  for(int j=0;j<itemsSold.size();j++){
				  if(customer.get(i).getID()==itemsSold.get(j).getSellerID()){
					  tmp+=itemsSold.get(i).getCustMon();
				  }
			  }
			  if(tmp>=high){
				  ind=i;
				  high=tmp;
			  }
		  }
		  String topRevDollars = number.format(high);
		  string+="Buyer All Years\n";
		  string+="Highest Buyer: "+customer.get(ind).firstName+" "+customer.get(ind).lastName+"\n";
		  string+="Revenue: "+" "+topRevDollars+"\n";
		  
		  low=tmp;
		  tmp=0;
		  ind=0;
		  
		  for(int i=0;i<customer.size();i++){
			  for(int j=0;j<itemsSold.size();j++){
				  if(customer.get(i).getID()==itemsSold.get(j).getSellerID()){
					  tmp+=itemsSold.get(i).getCustMon();
				  }
			  }
			  if(tmp<=low){
				  ind=i;
				  low=tmp;
			  }
		  }
		  String lowRevDollars =number.format(low);
		  string+="Lowest Buyer: "+customer.get(ind).firstName+" "+customer.get(ind).lastName+"\n";
		  string+="Revenue: "+" "+lowRevDollars+"\n";
		  
		  return string;
	  }

	  public String stringCustRev(String yr){
		  double tmp=0;
		  double low=0;
		  double high=0;
		  int ind=0;
		  String stringYr="";
		  String string="";
		  
		  for(int i=0;i<customer.size();i++){
			  for(int j=0;j<itemsSold.size();j++){
				  stringYr=Integer.toString(itemsSold.get(i).getEnd().getDate().getYear());
				  if(customer.get(i).getID()==itemsSold.get(j).getSellerID() && stringYr.equals(yr)){
					  tmp+=itemsSold.get(i).getCustMon();				  
				  }
			  }
			  
			  if(tmp>=high){
				  ind=i;
				  high=tmp;
			  }
		  }
		  String topRevDollars = number.format(high);
		  string+="Buyer Year"+yr+"\n";
		  string+="Highest Buyer: "+customer.get(ind).firstName+" "+customer.get(ind).lastName+"\n";
		  string+="Revenue: "+" "+topRevDollars+"\n";
		  
		  low=tmp;
		  tmp=0;
		  ind=0;  
		  
		  for(int i=0;i<customer.size();i++){
			  for(int j=0;j<itemsSold.size();j++){
				  stringYr=Integer.toString(itemsSold.get(i).getEnd().getDate().getYear());
				  if(customer.get(i).getID()==itemsSold.get(j).getSellerID()&& stringYr.equals(yr)){
					  tmp+=itemsSold.get(i).getCustMon();				  
				  }
			  }		  
			  if(tmp<=low){
				  ind=i;
				  low=tmp;
			  }
		  }
		  String lowRev =number.format(low);
		  string+="Lowest Buyer: "+customer.get(ind).firstName+" "+customer.get(ind).lastName+"\n";
		  string+="Revenue: "+" "+lowRev+"\n";
		  
		  return string;
	  }
	  
	  public String stringRevenue(String yr){
		  String temp="";
		  revenue = 0;
		  String stringYr="";
		  double insertionFees=0.00;
		  double collected=0.00;
		  temp+="Revenue\n";
		  if(yr.equals("All")){
			  for(int i=0;i<itemsSold.size();i++){		  
				 stringYr=Integer.toString(itemsSold.get(i).getStartDate().getDate().getYear());  
				temp+=itemsSold.get(i)+"\n";
				insertionFees=itemsSold.get(i).getInsertionFee();
				collected=itemsSold.get(i).getCostCollected();
				String sInsertion = number.format(insertionFees);
				String sCostCollected=number.format(collected);
				temp+="Insertion Fee "+sInsertion+ " totalCost "+sCostCollected+"\n";			  
				revenue+= itemsSold.get(i).getInsertionFee()+itemsSold.get(i).getCostCollected();
					
			  }
			  String num = number.format(revenue);
			  temp+="\nRevenue: "+num;
			  return temp;
		  }
		  
		  
		  for(int i=0;i<itemsSold.size();i++){		  
			  stringYr=Integer.toString(itemsSold.get(i).getStartDate().getDate().getYear());
			  if(stringYr.equals(yr)){			  
				  
				  temp+=itemsSold.get(i)+"\n";
				  insertionFees=itemsSold.get(i).getInsertionFee();
				  collected=itemsSold.get(i).getCostCollected();
				  String stringInsertion = number.format(insertionFees);
				  String stringCollected=number.format(collected);
				  temp+="Insertion Fee "+stringInsertion+ " totalCost "+stringCollected+"\n";			  
				  revenue+= itemsSold.get(i).getInsertionFee()+itemsSold.get(i).getCostCollected();
				  
				  
			  }
		  }
		  String num = number.format(revenue);
		  temp+="\nRevenue: "+num;
		  return temp;
	  }

	  public String stringCustBids(int ind, String yr){
		  String tmp="";
		  ArrayList<Bidding> tmpBid=new ArrayList<Bidding>();
		  String stringYr="";
		  tmp+= "Bids for Customer: "+customer.get(ind).firstName+" "+customer.get(ind).lastName+"\n";
		  if(yr.equals("All")){
			  for(int i=0;i<itemsSold.size();i++){
				  tmpBid=itemsSold.get(i).getBids();
				  for(int j=0;j<tmpBid.size();j++){
					  stringYr=Integer.toString(tmpBid.get(j).getTime().getDate().getYear());
					  if(tmpBid.get(j).getID()==customer.get(ind).getID()){
						tmp+= tmpBid.get(j)+"\n";  
					  }
				  }
			  } 
			  return tmp;
		  }
		  for(int i=0;i<itemsSold.size();i++){
			  tmpBid=itemsSold.get(i).getBids();
			  for(int j=0;j<tmpBid.size();j++){
				  stringYr=Integer.toString(tmpBid.get(j).getTime().getDate().getYear());
				  if(stringYr.equals(yr) && tmpBid.get(j).getID()==customer.get(ind).getID()){
					tmp+= tmpBid.get(j)+"\n";  
				  }
			  }
		  }
		  
		  return tmp;
	  }
	  
	  public String getCustName(int id){
		  String string="";
		  for(int i=0;i<customer.size();i++){
			  if(customer.get(i).getID()==id){
				  return customer.get(i).firstName+" "+customer.get(i).lastName;
			  }
		  }
		  return string;
	  }

	  public String stringSoldItemBids(int ind){
		  ArrayList<Bidding> tmpBid=new ArrayList<Bidding>();
		  tmpBid= itemsSold.get(ind).getBids();
		  String string="";
		  string+="Customer that owns item: "+this.getCustName(itemsSold.get(ind).getSellerID())+"\n";	  
		  string+="Item Details\n";
		  string+= itemsSold.get(ind)+"\n";
		  string+= "Bids for item \n";
		  for(int i=0;i<tmpBid.size();i++){
			  string+=tmpBid.get(i)+"\n";
		  }
		  String dollarCost=number.format(itemsSold.get(ind).getCustMon());
		  string+="Customer profit: "+dollarCost;
		  return string;
	  }
	  
	  public String stringItemBids(int ind){
		  ArrayList<Bidding> tmpBid=new ArrayList<Bidding>();
		  tmpBid= itemsForSale.get(ind).getBids();
		  String string="";
		  string+="Customer that owns item: "+this.getCustName(itemsForSale.get(ind).getSellerID())+"\n";	  
		  string+="Item Details\n";
		  string+= itemsForSale.get(ind)+"\n";
		  string+= "Bids for item \n";
		  for(int i=0;i<tmpBid.size();i++){
			  string+=tmpBid.get(i)+"\n";
		  }
		  return string;
	  }
	
}
