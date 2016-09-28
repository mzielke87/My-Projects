/**
 * A class CSupport 
 * 
 * Useful for creating CSupport objects 
 * 
 * @author Matt Zielke 
 */
public class Csupport extends SalariedEmployee{ 
  
   double rate;
  
  Csupport(String first,String last, Date DOB, String gender, Date hire, String release,double base, double pay){
    
    super(first,last,DOB,gender,hire,release,base); 
    rate=pay;    
  }
  
  /**
  * Method: overrides method in SalariedEmployee to calculate salary
  * 
  * @param   represents time worked
  * @return salary       
  */
  public double computeSalary(int hours){
	    
	System.out.println("Employee Type: Customer Support ");
		 
	return this.basePay+rate*hours;
	 
  }
    
  public String toString(){    
   
   return "Customer Support->"+firstName+" "+lastName+" Birthdate: "+dob+" Gender:"+gender+" ID: "+id+" HireDate: "+
     hireDate+" Release Date: "+releaseDate+" basePay: "+basePay;   
 }
}
