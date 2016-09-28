/**
 * A class WebDesigner 
 * 
 * Useful for creating WebDesigner objects 
 * 
 * @author Matt Zielke 
 */
public class WebDesigner extends SalariedEmployee{ 
  
  double rate;
  
  WebDesigner(String first,String last, Date DOB, String gender, Date hire, String release,double base, double pay){
    
    super(first,last,DOB,gender,hire,release,base); 
    rate=pay;    
  }
  
  /**
  * Method: Overrides method in SalariedEmployee to calculate salary
  * 
  * @param hours  represents time worked
  * @return salary       
  */
  public double computeSalary(int hours){
    
	System.out.println("Employee Type: Web Designer ");
		 
	return this.basePay+rate*hours;
  }
  
 /**
  * 
  *  * @override
  */
  public String toString(){    
   
   return "Web Designer->"+firstName+" "+lastName+" Birthdate: "+dob+" Gender:"+gender+" ID: "+id+" HireDate: "+
     hireDate+" Release Date: "+releaseDate+" basePay: "+basePay;   
 }
  
}

