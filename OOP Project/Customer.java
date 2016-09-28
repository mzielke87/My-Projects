
/**
 * A simple data type Customer for use in Project 3 (CSE 1325, Spring 2014)
 * <br>
 * Useful for creating Customer objects to hold customer info
 * 
 * @author Matt Zielke (
 */
import java.math.*;
import java.text.*;
import java.util.*;

public class Customer extends Person{       
    
    private enum Status{GOLD,SILVER};
    private enum Classification{SELLER,BUYER,BOTH};
    private int userID;
    private String address;
    private String zip;
    private String state;    
    private Status status;
    private Classification classification;
    private DecimalFormat three= new DecimalFormat("#000");    
    
    public Customer(int id,String firstName,String lastName,Date db,String gender, String ad,
                                              String st, String z) {
        super(firstName, lastName, gender,db);
        userID = id;
        address=ad;
        state=st;
        zip=z;
        status=status.SILVER;
        classification= classification.BOTH;
    }  
   
    
  public int getID(){
	  return this.userID;
  }

 /**
  * 
  * @override overrides Person
  * @return String that represents a Customer  
  */
 public String toString(){  
   
  String customerIDF= three.format(this.userID);
   
   return "{ UserID: "+customerIDF+"| Name: "+super.firstName+" "+super.lastName+"| D.O.B.: "+super.dob+
     "| Address: "+ this.address+" "+this.zip+"| Sales Status: "+this.status+
     "| Customer Class: "+this.classification+" }";   
 }
 
 /**
  * Method: gets age
  * 
  * @return age of current person       
  */
 public int age(){
   int old = 0;
   Date today= new Date();
   old = (dob.daysBetween(today))/365;
   return old;
 }
 
}
