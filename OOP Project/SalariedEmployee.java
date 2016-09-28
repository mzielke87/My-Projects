

/**
 * A abstract data type SalariedEmployee 
 * 
 * Useful for creating SalariedEmployee objects 
 * 
 * @author Matt Zielke 
 */
public abstract class SalariedEmployee extends Person implements Employee{
  
  int id=0;
  Date hireDate=null;
  Date releaseDate=null;
  double basePay=0; 
  
  public SalariedEmployee(String firstName,String lastName,Date db,String gender,Date hd, String rd, double base) {
	  
        super(firstName, lastName, gender,db);
        
        hireDate=hd;
        if(rd.equals("null")){
          releaseDate=null;
         }else{   
          releaseDate= new Date(rd); 
         }
         basePay=base;        
    }   
  
  /**
  * Method: Returns the ID of the current employee
  *     
  */
  public int getID(){
    return this.id;
  }
  
  /**
  * Method: Gets the release date of employee
  * 
  * @return Date of release    
  */
  public Date getReleaseDate(){
     return this.releaseDate;
  }
  
  /**
  * Method: Releases current employee sets release date
  *  
  *  @param date   represents date to be set to
  */
  public void setReleaseDate(Date date){
   
	  this.releaseDate = date;
  }
  
  /**
  * Method: Sets Employee ID
  *  
  *  @param   represents id to be set 
  */
  public void setID(int empID){
   
    id=empID;
  }
  
   /**
  * Method: Calculates the years of service of current employee
  * 
  * @return years of service      
  */
  public int lengthOfService(){
   
    Date today= new Date();
    return (hireDate.daysBetween(today)/365);
  }
  
  /**
  * Method: Calculates the age 
  * 
  * @return age       
  */
 public int age(){
   int old = 0;
   Date today= new Date();
   old = (dob.daysBetween(today))/365;
   return old;
 }
 
 public String toString(){
	   return "Employee->"+firstName+" "+lastName+" Birthdate: "+dob+" Gender:"+gender+" ID: "+id+" HireDate: "+
			     hireDate+" Release Date: "+releaseDate+" basePay: "+basePay;  
 }
  
}