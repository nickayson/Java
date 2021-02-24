//****************************************************************************************************************************
//Copyright (C) 2021 Nicholas Ayson.  This program is free software: you can redistribute it and/or modify it under the terms*
//of the GNU General Public License version 3 as published by the Free Software Foundation.                                  *
//This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied         *
//warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.     *
//A copy of the GNU General Public License v3 is available here:  <https://www.gnu.org/licenses/>.                           *
//****************************************************************************************************************************
//Program information:
  //Program name: Payroll Calculator
  //Programming language: Java
  //Files: Payrollmain.java, PayrollCalculatorframe.java, PayrollCalculatorOperations.java, Payrollrun.sh
  //Date project began: 2021-January-24.
  //Date of last update: 2021-January-30.
  //Status: Finished; testing completed.
  //Purpose: This program demonstrate the design of a simple UI (user interface) where the only implemented functions are
  //regular, overtime, and gross pay of doubles.  Also, this program demonstrates the use of multiple source files as one program.
  //Nice feature: If no values are entered into the input boxes then zero is assumed to be the input.
  //Base test system: Linux system with Bash shell and openjdk-14-jdk

//This module
  //File name: PayrollCalculatorOperations.java
  //Compile : javac PayrollCalculatorOperations.java
  //Purpose: This class completes the calculations of the payroll
  //This module (class) is called from the Payrollmain class.


//Author information:
  //Author: Nicholas Ayson
  //Mail: nick.ayson@csu.fullerton.edu

public class PayrollCalculatorOperations
{
  public static double regularpay(double hoursworked, double payrate)
  {
    //regular pay is hoursworked * payrate if less than 40 and 40 * payrate if greater
    double regular;
    //has to clear all of these conditions to execute
    // if the hoursworked is less than 40 and greater than zero and it cannot calculate with integers
    // then it can execute.
    if(hoursworked <= 40.0 && hoursworked > 0.0 && hoursworked <= 168.0)
    {
      regular = hoursworked * payrate;
    }
    //has to clear all of these conditions to execute
    else if(hoursworked > 40.0 && hoursworked <= 168.0)
    {
      regular = 40.0 * payrate;
    }
    else
    {
      regular = 0.0;
    }
    return regular;
  }
  public static double overtimepay(double hoursworked, double payrate)
  {
    //overtime pay is hoursworked over 40 times 1.5
    double overtime;
    //has to clear all of these conditions to execute
    hoursworked = hoursworked - 40.0;
    if(hoursworked > 0.0 && hoursworked <= 128.0 )
    {
      overtime = hoursworked * 1.5 * payrate;
    }
    else
    {
      overtime = 0.0;
    }
    return overtime;
  }
  public static double grosspay(double overtime, double regular)
  {
    //gross pay is overtime + regular;
   double gross;
   gross = overtime + regular;
   return gross;
  }

  public static boolean isDouble(String s)
  {double leng = s.length();
   boolean success = true;
   if(leng == 0.0)
         success = false;
   else  //(leng > 0)
   {
     int start = 0;
     char c = s.charAt(0);
     for(int k = start; k<leng && success; k++){
        c = s.charAt(k);
        if(! Character.isDigit(c) && c != '.' ) success = false;
     }
   }
    return success;
  }//End of method isDouble
}//End Payroll Class
