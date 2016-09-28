/**
 * Programmer: 	Sharma Chakravarthy
 * Language:	Java
 * date:        09/10/2013
 * Purpose:	This program uses MayBayMgmtTest class to read data from a text file to initialize
 * 		employees, items for sale, customers who bid, and actual bids
 * 				
 *              It checks and recovers from some exceptions while reading the input file
 * 
 * USAGE:       You need to initialize your data structures (creation of objects) as the first step. 
 *              once the values are read into local variables, 
 *              it  is YOUR responsibility to add code at proper places to create objects and manage them!
 *
 *              filename is given as a command line argument (e.g, java MayBayMgmtTest dataFile-proj2.txt)
 *              for the naming convention used in this file. if you forget to give the data 
 *              file as a command line argument, it will prompt you for that.	
 *          
 *              you can remove or comment out print staatements once you are sure it is reading the input correctly
 *
 * MAKE SURE:   your program is completely case in-sensitive (for gender, employee type etc.)
 */

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map.Entry;
import java.util.Scanner;

/**
 * @param fileName
 *            as input data filename containing input items with  as item separators
 *  Note that multiple interfaces can be used with a class
 */

public class MavBayMgmtTest implements Proj3Constants, DateConstants {

	// introduce your (class and instance) attributes (if needed) for this test class.
    // As i have indicated, it is preferable to have a MavBayMgmt class for the enterprise
    // and use this ONLY as a driver or test class
    // that way, this class can be removed to make your system a library!!

	private static BufferedReader finput;   //for reading from a file
    private static Scanner cp;              //this is still command prompt
    private static PrintWriter foutput;     //for writing to a file

    //define other variables as needed

    //Note that we are using a DIFFERENT method for reading input file;
	/**
	 * @param iFileName is the input data file name
	 */		

    public static BufferedReader openReadFile(String iFileName){
        BufferedReader bf = null;
        try{
            bf = new BufferedReader(new FileReader(iFileName));
        }     
        catch(FileNotFoundException FNFE){    
          bf = null;
        }
       finally{
          return bf;
       }
    }

/**
	 * @param oFileName is the input data file name
	 */		
    
    public static PrintWriter openWriteFile(String oFileName){
        PrintWriter outputFile = null;
        try{
            outputFile = new PrintWriter(new FileWriter(oFileName));
        }     
        catch(IOException IOE){    
          outputFile = null;
        }
       finally{
          return outputFile;
       }
    }
    	

	/**
	 * @param args takes
	 *           2 fileNames as command line argument. prompts if either is not given
	 */
	public static void main(String[] args) {

		// declare variables used for input handling
        String enterprisename = DEFAULT_ENTERPRISE_NAME;
        String inputLine = EMPTY_STRING, ifName = EMPTY_STRING, ofName = EMPTY_STRING;
        int empID = 1;
		// determine if input file is provided

		cp = new Scanner(System.in);
		if (args.length < 1) {
			System.out.println("Input Data file name was not supplied");
			System.out.printf("Please type input data file name: ");
			ifName = cp.nextLine();
		} 
        else if (args.length < 2){
            ifName =  args[ZEROI];
            System.out.println("Output file name was not supplied");       
            System.out.printf("Please type output file name: ");
            ofName = cp.nextLine();
        } else {
            ifName = args[ZEROI];
            ofName = args[ONEI];
            }

		// See HOW RECOVERY is done (will cover in a few weeks)

		finput = openReadFile(ifName);
		while (finput == null) {
			System.out.printf("Error: input data FILE %s %s", ifName,
					" does not exist.\nEnter correct INPUT data file name: ");
			ifName = cp.nextLine();
			finput = openReadFile(ifName);
		}
        foutput = openWriteFile(ofName);
        while (foutput == null){
			System.out.printf("Error: OUTPUT FILE %s %s",  ofName,  
                        " does not exist.\nEnter correct OUTPUT FILE name: ");
            ofName = cp.nextLine();
            foutput = openWriteFile(ofName);
		}  

        System.out.printf("Input data File: %s\nOutput File: %s\n\n", ifName, ofName);
        foutput.printf("Input data File: %s\nOutput File: %s\n\n", ifName, ofName); 

		/* GET MayBay DETAILS */
		// start reading from data file
		// get name
		try {
			inputLine = finput.readLine();
            System.out.println(inputLine);
			while (inputLine.charAt(BASE_INDEX) == '/'){
				inputLine = finput.readLine();
                System.out.println(inputLine);
            }
			String enterpriseName = inputLine;
			System.out.printf("\n%s %s \n", "Enterprise name is: ",
					enterpriseName);
			MavBay myEnt = new MavBay(enterpriseName);
            //MENU PROCESSING STARTS HERE!!!!
			System.out.printf("\nStarting Menu Processing: \n\n");
            foutput.println("\nStarting Menu Processing: \n\n");
          
          //read each line as before and process according to the menu number
            inputLine = finput.readLine();
            System.out.println(inputLine);
            foutput.println(inputLine);
			while (inputLine.charAt(BASE_INDEX) == '/'){
				inputLine = finput.readLine();
                System.out.println(inputLine);
                foutput.println(inputLine);
            }

			ArrayList<Bidding> bidArrayList = new ArrayList<Bidding>();
        	HashMap<Integer, ArrayList<Bidding>> myHash = new HashMap<Integer, ArrayList<Bidding>>();
			
            while ( (!inputLine.toLowerCase().equals("end"))){
				String[] chopMenuLine = inputLine.split("!");
				
            switch (Integer.parseInt(chopMenuLine[ZEROI])){
            
            case 10: 
            		myEnt.displayMenu();

                    break;
            case 11: // process new item information
                   
                     // get fields of a item from one line of input
                     // if you want to make it modular, you can create a
                     // method proceddHireEmp() and move the code there.
                     // you will have to adjust the visibility of variables!!
			
                    int  itemId = Integer.parseInt(chopMenuLine[ONEI]);
                    String  itemCategory = chopMenuLine[TWOI];
                    String itemName = chopMenuLine[THREEI];
                    String itemSaleType = chopMenuLine[FOURI].toUpperCase();
                    int itemQty = Integer.parseInt(chopMenuLine[FIVEI]);
                    String itemCondition = chopMenuLine[SIXI].toUpperCase();
                    double itemMinStartBid = Double.parseDouble(chopMenuLine[SEVENI]);
                    double itemBidIncrement = Double.parseDouble(chopMenuLine[EIGHTI]);
                    double itemReserveAmt = Double.parseDouble(chopMenuLine[NINEI]);
                    String itemAuctionStartDate = chopMenuLine[TENI];
                    String end = chopMenuLine[TENI];
                    int    itemAuctionDays      = Integer.parseInt(chopMenuLine[ELEVENI]);
                    int    itemSellerId      = Integer.parseInt(chopMenuLine[TWELVEI]);
                    int    sellerFeedbackScore      = Integer.parseInt(chopMenuLine[THIRTEENI]);
                    String  itemDescription     = chopMenuLine[FOURTEENI];
                    
                    DateTime auctionend = new DateTime(end);
                    auctionend.addDays(itemAuctionDays);
                    DateTime auctionstart = new DateTime(itemAuctionStartDate);

                    System.out.printf("[%d, %s, %s, %s, %d, %s,  %f, %f, %f, %s, %d, %d, %d, %s]\n",
                        itemId,itemCategory,itemName,itemSaleType, itemQty, itemCondition,
                        itemMinStartBid,itemBidIncrement, itemReserveAmt, itemAuctionStartDate,
                        itemAuctionDays, itemSellerId, sellerFeedbackScore, itemDescription);
                
                    
                    Items item = new Items(itemId,itemCategory,itemName,itemSaleType, itemQty, itemCondition,
                            itemMinStartBid,itemBidIncrement, itemReserveAmt, auctionstart,
                            itemAuctionDays, itemSellerId, sellerFeedbackScore, itemDescription, auctionend);
                    
                    myEnt.addItem(item);
                    
                    break;
            case 12:// process new customer information
                
				    int userId = Integer.parseInt(chopMenuLine[ONEI]);
                    String userFName = chopMenuLine[TWOI];
                    String userLName = chopMenuLine[THREEI];
                    String userDob = chopMenuLine[FOURI];
                    String userGender = chopMenuLine[FIVEI].toLowerCase();
                    String userAddress = chopMenuLine[SIXI];
                    String userState = chopMenuLine[SEVENI].toUpperCase();
                    String userZipcode = chopMenuLine[EIGHTI];
                
                    Date dobUser = new Date(userDob); //how to construct a Data object!
                    
                    System.out.printf("{%d, %10s, %10s, %10s, %7s, %20s, %6s, %6s} \n",
                        userId, userFName, userLName,dobUser,  userGender, userAddress, 
                        userState, userZipcode);

                    Customer customer = new Customer(userId, userFName, userLName, dobUser, userGender, userAddress, userState, 
                    		userZipcode);
                    
                    myEnt.addCustomer(customer);
                    // create a user object as appropriate
                     break;
            case 13://process hiring a new employee
            
                    String empType = chopMenuLine[ONEI].toUpperCase();
				    String empFName = chopMenuLine[TWOI];
				    String empLName = chopMenuLine[THREEI];
				    String empBDate = chopMenuLine[FOURI];
				    String empGender = chopMenuLine[FIVEI].toLowerCase();
				    String empHireDate = chopMenuLine[SIXI];
                    String empReleaseDate = chopMenuLine[SEVENI];
				    double empBaseSalary = Double.parseDouble(chopMenuLine[EIGHTI]);
				    double timePay = Double.parseDouble(chopMenuLine[NINEI]);

				    // add code here: in order to convert a date string to a Date object,
				    // invoke the appropriate constructor of the Date class (shown below)
				
					Date dob = new Date(empBDate); 
					Date hire = new Date(empHireDate);

				    System.out.printf("(%6s, %10s, %6s, %12s, %12s,  %10.2f, %4s, %12s)\n",
						empFName, empLName, empGender, empHireDate,
						empReleaseDate, empBaseSalary, empType, dob);

				    // also, empType is read as a string; if you are using a
				    // enum, you need to convert it  using a switch 
                    //or if statement or enum valueOf() method
				    if(empType.toUpperCase().equals("WD")) {
						WebDesigner emp = new WebDesigner(empFName, empLName, dob, empGender, hire, 
								empReleaseDate, empBaseSalary, timePay);
						emp.setID(empID);
						empID++;
						myEnt.addEmployee(emp);
				    }
				    else if(empType.toUpperCase().equals("ACCT")) {
						Accountant emp = new Accountant(empFName, empLName, dob, empGender, hire, 
								empReleaseDate, empBaseSalary, timePay);
						emp.setID(empID);
						empID++;
						myEnt.addEmployee(emp);
				    }
				    else if(empType.toUpperCase().equals("CUST_SUPPORT")) {
						Csupport emp = new Csupport(empFName, empLName, dob, empGender, hire, 
								empReleaseDate, empBaseSalary, timePay);
						emp.setID(empID);
						empID++;
						myEnt.addEmployee(emp);
				    }
					
				    
                    //create employee object here using Employee class constructors
                    //make sure you set the release date properly (will be used later)
                    
                    break;
			
			case 14:// accept a new bid    
                
					String printBid;
				
                	int bidItemId = Integer.parseInt(chopMenuLine[ONEI]);
                	int bidUserId = Integer.parseInt(chopMenuLine[TWOI]);
                	String bidDateTime = chopMenuLine[THREEI];
                	double bidAmount = Double.parseDouble(chopMenuLine[FOURI]);
                	int bidQty = Integer.parseInt(chopMenuLine[FIVEI]);
                
                	DateTime biddatetime = new DateTime(bidDateTime);
                				
                	System.out.printf("{%d, %d, %15s, %f, %d} \n",
                			bidItemId, bidUserId, biddatetime, bidAmount, bidQty);
                    
                
                	Bidding bid = new Bidding(bidItemId, bidUserId, biddatetime, bidAmount, bidQty);
                
                	myEnt.addInsertionFee(bidItemId);
                	
                	myEnt.addBids(bidItemId, bid);
                	bidArrayList.add(bid);
                	
                	printBid = myEnt.displayBids(bidItemId);
                	
                	foutput.println(printBid + "\n\n\n");
				
                    break;
            case 15:// terminate an employee
            	
            		String printEmp;
            	
    				int id =  Integer.parseInt(chopMenuLine[ONEI]);
    				String releaseDate = chopMenuLine[TWOI];
    				
    				myEnt.releaseEmployee(id, releaseDate);
    				
    				printEmp= myEnt.displayEmployees();
    				
    				foutput.println(printEmp + "\n\n\n");
    				
                    break;
            case 16://compute monthly salary
            	
            		String printSal;
            	
					int eid =  Integer.parseInt(chopMenuLine[ONEI]);
					int rate = Integer.parseInt(chopMenuLine[TWOI]);
					int month = Integer.parseInt(chopMenuLine[THREEI]);
					
    				printSal = myEnt.computeSalary(eid, rate, month);
					
    				foutput.println("Employee ID: " + eid + " ");
    				foutput.println(printSal + "\n\n\n");
    				
                    break;
                    
            case 17:// compute length of service
            	
            		int length = 0;
            		String printLength;
            	
            		int lengthid = Integer.parseInt(chopMenuLine[ONEI]);
            		
            		length = myEnt.lengthOfService(lengthid);
            		printLength = myEnt.employeeById(lengthid);
            		
            		foutput.println(printLength);
					foutput.println("Length of Service: " + length + "\n\n\n");
                    break;
                    
            case 18: // compute revenue
            	
            		String printItemsSold;
            		double printRevenue = 0;
            	
            		String whatToCompute = chopMenuLine[ONEI].toUpperCase();
            		int year = Integer.parseInt(chopMenuLine[TWOI]);
            		
            		myEnt.isSold();
            		printItemsSold = myEnt.displayItemsSold(whatToCompute);
            		printRevenue = myEnt.computeRevenue(whatToCompute, year);
            		
            		foutput.println("Items sold in year " + year + ": " + printItemsSold);
            		foutput.println("Revenue in year " + year + ": " + printRevenue + "\n\n\n");
            	
                    break;
                    
            case 19: // generate seller report
            	
            		String printForSale;
            		String printSold;
            		String printSellerInfo;
            		double printRev = 0;
            	
        			int sellId = Integer.parseInt(chopMenuLine[ONEI]);
        			int ye = Integer.parseInt(chopMenuLine[TWOI]);
            	
        			foutput.println("Item(s) if any put up for sale by seller id-> " + sellId + ": ");
        			printForSale = myEnt.displayItemsForSaleBySeller(sellId, ye); 
        			foutput.println(printForSale);
        			printSold = myEnt.displayItemsSoldBySeller(sellId, ye);
        			foutput.println(printSold + "\n\n");
        			
        			foutput.println("Item(s) sold by seller id-> " + sellId + ": ");
           			printSold = myEnt.displayItemsSoldBySeller(sellId, ye);
           			foutput.println(printSold + "\n\n");
           			
           			printSellerInfo = myEnt.printSellerInfo(sellId, ye);
           			foutput.println(printSellerInfo + "\n\n");
        			
           			printRev = myEnt.computeRevenueBySellerId(sellId, ye);
           			foutput.println("Total revenue by seller id " + sellId + ": " + printRev + "\n\n\n");
           			
                    break;
                    
            case 20: //sort bids          	
            
            		Collections.sort(bidArrayList, new DateTimeComparator());
                    
            	    for (int i=0;i<bidArrayList.size();i++){
                	  	  System.out.println(bidArrayList.get(i));
                	    }  
            		
            		break;
            		
            case 21: //store bids in hash map and print 
            	
            	String userValue = chopMenuLine[ONEI];          	     
     	
            	for(int i=0; i<myEnt.getCustomer().size(); i++) {
            		ArrayList<Bidding> tmpBids = new ArrayList<Bidding>();
            		for(int j=0; j<bidArrayList.size(); j++) {
            			if(myEnt.getCustomer().get(i).getID() == bidArrayList.get(j).getUserID()) {
            				tmpBids.add(bidArrayList.get(j));            	
            			}
            		}
            	
            	myHash.put(myEnt.getCustomer().get(i).getID(), tmpBids);
            	}
            	
        	    if(userValue.equals("*")) {
        	    	for(Entry<Integer, ArrayList<Bidding>>  Entry: myHash.entrySet()){
        	    		Collections.sort(Entry.getValue());
            	    	System.out.println("Key = " + Entry.getKey() + ", bids " + Entry.getValue());
            	    		
            	    	}
        	    }
        	    else {
        	    	int custBidID= Integer.parseInt(userValue);
        
        	    	if(myHash.get(custBidID) == null)
        	    		System.out.println("Customer ID entered does not exist \n");
        	    	else {
        	    	 Collections.sort(myHash.get(custBidID));
        	    	 System.out.println("Key = " + custBidID + ", bids " + myHash.get(custBidID));
        	    	}   	
        	    }	
        	    
            	break;
            		
            case 0: //process exit
            	
            	System.out.println("Thank you for using MavBay \nHave a nice day \n");
            	System.exit(0);
            	
            	//MySwing gui= new MySwing(myEnt);
            
            	//break;
                    
            default: System.out.printf("unknown command: %s: SKIPPED\n", inputLine);
                    foutput.printf("unknown command: %s: SKIPPED\n", inputLine);
                    break;       
                 
            }	
            inputLine = finput.readLine();
            System.out.println(inputLine);
            foutput.println(inputLine);
			while (inputLine.charAt(BASE_INDEX) == '/'){
				inputLine = finput.readLine();
                System.out.println(inputLine);
                foutput.println(inputLine);
            }
        } 
         System.out.printf("Finished processing all commands. bye!"); 
         foutput.printf("Finished processing all commands. bye!");      
        		// DO NOT REMOVE or DISTURB the REST OF THE CODE	

		} //try
        catch(NullPointerException NPE){
            System.out.println("null pointer exception: " + "\nPlease correct " + NPE.getMessage());
          } 
        catch (NumberFormatException NFE) {
			System.out.println("I/O Error in File: " + ifName + "\ncheck for: "
					+ NFE.getMessage() + " and correct it in: " + inputLine);
		  } 
        catch (RuntimeException RE) {
			System.out.println("Invalid Data error in File: " + ifName
					+ "\nPlease correct " + RE.getMessage() + " in the file!" + inputLine);
		  }
        catch(IOException IOE){
            System.out.println("input/output Data error in File: " + ifName + "\nPlease correct " + IOE.getMessage() + " in the file!" + inputLine);
          } 
        
        finally {
		  foutput.close();
		  }
	}//main
} // MavBayMgmtTest
