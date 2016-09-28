/*****************************************************************************
 @author Sharma Chakravarthy
 LANGUAGE   : Java version 6
 OS         : Windows win 7 Ultimate
 PLATFORM   : PC
 Compiler   : javac
 
 CONCEPTS   : classes and methods
 PURPOSE    : defines an interface
******************************************************************************/

public interface Employee {  

 /**
  * Abstract class that needs to be overridden in WD, CS, and ACCT classes
  * calculates salary of unique objects
  * 
  * @param salaryParameter unique to the class its created from
  * @return age of current person       
  */
 public double computeSalary(int rate); 
 
 /**
  * Abstract class that needs to be overridden in WD, CS, and ACCT classes
  * Calculates the years of service of current employee
  * 
  * @return years of service      
  */
 public int lengthOfService();
 
}
