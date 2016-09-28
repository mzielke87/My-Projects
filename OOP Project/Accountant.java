/**
 * A class Accountant 
 * 
 * Useful for creating Accountant objects 
 * 
 * @author Matt Zielke 
 */
public class Accountant extends SalariedEmployee{ 
  
   double rate;
  
  Accountant(String first,String last, Date DOB, String gender, Date hire, String release,double base, double pay){
    
    super(first,last,DOB,gender,hire,release,base); 
    rate=pay;    
  }
  
  /**
  * Method:oOverrides method in SalariedEmployee to calculate salary
  * 
  * @param  represents time worked
  * @return salary       
  */
  public double computeSalary(int hours){
	 
	 System.out.println("Employee Type: Accountant ");
	 
	 return this.basePay+rate*hours;
  }
    
  public String toString(){    
   
   return "Accountant->"+firstName+" "+lastName+" Birthdate: "+dob+" Gender:"+gender+" ID: "+id+" HireDate: "+
     hireDate+" Release Date: "+releaseDate+" basePay: "+basePay;   
 }
}