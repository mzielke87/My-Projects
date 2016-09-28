/**
 * A superclass Person 
 * 
 * Useful for creating Person objects 
 * 
 * @author Matt Zielke 
 */
public abstract class Person extends Object implements Proj3Constants {
        
    
    private enum Gender{MALE,FEMALE};  
    String firstName;
    String lastName;
    Date dob;
    Gender gender;    
        
   public Person(String first, String last, String gen,Date DOB) {
        lastName = last;
        firstName = first;
        gender = Gender.valueOf(gen.toUpperCase());
        dob = DOB;
    }   
  
 /**
  * Abstract class 
  * 
  * @return age of current person       
  */
 public abstract int age();
 
 /**
  * 
  * @override overides object class
  * @return String  
  */
 public String toString(){
    
   return "Person->"+firstName+" "+lastName+" Birthdate: "+dob+" Gender:"+gender;   
 }
}