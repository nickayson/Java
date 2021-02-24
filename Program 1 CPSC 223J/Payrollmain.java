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
  //File name: Payrollmain.java
  //Compile : javac Payrollmain.java
  //Purpose: This class displays the UI
  //This module (class) is called from the Payrollmain class.


//Author information:
  //Author: Nicholas Ayson
  //Mail: nick.ayson@csu.fullerton.edu

import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.Rectangle;

public class Payrollmain
{public static void main(String[] args)
    {PayrollCalculatorframe myframe = new PayrollCalculatorframe();
     myframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     myframe.setSize(300,350);
     myframe.setResizable(true);

     myframe.setMinimumSize(new Dimension(290,300));
     myframe.setPreferredSize(new Dimension(300,350));
     Rectangle max_size = new Rectangle(0,0,600,762);
     myframe.setMaximizedBounds(max_size);

     myframe.setVisible(true);
    }//End of main
}
