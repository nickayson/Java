//****************************************************************************************************************************
//Program name: "Payroll Calculator".  This program shows how to calculate the overtime and regular pay of two numbers using a simple UI with three     *
//active buttons.
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
  //File name: PayrollCalculatorframe.java
  //Compile : javac PayrollCalculatorframe.java
  //Purpose: This class defines the user interface
  //This module (class) is called from the Payrollmain class.


//Author information:
  //Author: Nicholas Ayson
  //Mail: nick.ayson@csu.fullerton.edu

//====Begin executable code========

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.Timer;
import java.util.Scanner;

public class PayrollCalculatorframe extends JFrame
{
  //private declarations user will not see
  private final int framewidth = 100;
  private final int frameheight = 100;

  private JPanel titlepanel;
  private JLabel titlelabel;

  private JPanel informationpanel1;
  private JLabel employeename;
  private JTextField textfield1;
  private JLabel hoursworked;
  private JTextField textfield2;
  private JLabel hourpayrate;
  private JTextField textfield3;

  private JPanel informationpanel2;
  private JLabel nameofemployee;
  private JLabel employeenameab;
  private JLabel regularpay;
  private JLabel pay1;
  private JLabel overtimepay;
  private JLabel pay2;
  private JLabel grosspay;
  private JLabel pay3;

  private JPanel buttonpanel;
  private JButton clearbutton;
  private JButton computebutton;
  private JButton quitbutton;
  private FlowLayout simplelayout;
  private GridLayout otherlayout1;
  private GridLayout otherlayout2;
  private GridLayout framelayout;

  private String namestring;
  private String numberstring1;
  private double number1;
  private String numberstring2;
  private double number2;
  private String regularstring;
  private double regular;
  private String overtimestring;
  private double overtime;
  private String grossstring;
  private double gross;

  private double leng=10.0;
  private boolean input_is_valid;
  private boolean successful_calculation = true;
  private PayrollCalculatorOperations payrollcalculatormachine;
  private Timer delayedclosing;private final int length_of_delay = 3500;

 public PayrollCalculatorframe()//constructor
 { //properties of the frame
   simplelayout = new FlowLayout();
   framelayout = new GridLayout(4,1);
   otherlayout1 = new GridLayout(3,3);
   otherlayout2 = new GridLayout(4,4);
   setLayout(framelayout);
   setTitle("Program 1");
   setSize(framewidth,frameheight);
   setLocationRelativeTo(null);//Pop up window in middle of monitor

   //first panel title
   titlepanel = new JPanel();
   titlepanel.setBackground(java.awt.Color.pink);
   titlepanel.setLayout(simplelayout);
   titlelabel = new JLabel();
   titlelabel.setText("Nicholas Ayson Burger Pay System");
   titlepanel.add(titlelabel);

   //second panel for text inputs
   informationpanel1 = new JPanel();
   informationpanel1.setLayout(otherlayout1);
   informationpanel1.setBackground(java.awt.Color.cyan);
   employeename = new JLabel();
   employeename.setText("Employee name: ");
   informationpanel1.add(employeename);
   textfield1 = new JTextField(5);
   add(textfield1);
   informationpanel1.add(textfield1);
   hoursworked = new JLabel();
   hoursworked.setText("Hours worked: ");
   informationpanel1.add(hoursworked);
   textfield2 = new JTextField(5);
   add(textfield2);
   informationpanel1.add(textfield2);
   hourpayrate= new JLabel();
   hourpayrate.setText("Hourly pay rate: ");
   informationpanel1.add(hourpayrate);
   textfield3 = new JTextField(5);
   informationpanel1.add(textfield3);

   //third panel for display of calculations
   informationpanel2 = new JPanel();
   informationpanel2.setLayout(otherlayout2);
   informationpanel2.setBackground(java.awt.Color.orange);
   nameofemployee = new JLabel();
   nameofemployee.setText("Name of Employee   ");
   informationpanel2.add(nameofemployee);
   employeenameab = new JLabel();
   informationpanel2.add(employeenameab);
   regularpay = new JLabel();
   regularpay.setText("Regular pay ");
   informationpanel2.add(regularpay);
   pay1 = new JLabel();
   informationpanel2.add(pay1);
   overtimepay = new JLabel();
   overtimepay.setText("Overtime pay ");
   informationpanel2.add(overtimepay);
   pay2 = new JLabel();
   informationpanel2.add(pay2);
   grosspay = new JLabel();
   grosspay.setText("Gross pay ");
   informationpanel2.add(grosspay);
   pay3 = new JLabel();
   informationpanel2.add(pay3);

   //fourth panel for button display
   buttonpanel = new JPanel();
   buttonpanel.setLayout(simplelayout);
   buttonpanel.setBackground(java.awt.Color.yellow);
   clearbutton = new JButton("Clear");
   buttonpanel.add(clearbutton);
   computebutton = new JButton("Compute");
   buttonpanel.add(computebutton);
   quitbutton = new JButton("Quit");
   buttonpanel.add(quitbutton);

   //setting up button handlers and Action Listeners
   buttonhandler myhandler = new buttonhandler();
   clearbutton.addActionListener(myhandler);
   computebutton.addActionListener(myhandler);
   quitbutton.addActionListener(myhandler);
   payrollcalculatormachine = new PayrollCalculatorOperations();
   delayedclosing = new Timer(length_of_delay,myhandler);

   //attaching each panel to outer frame
   add(titlepanel);
   add(informationpanel1);
   add(informationpanel2);
   add(buttonpanel);
  }//end of constructor

 private class buttonhandler implements ActionListener{
   public void actionPerformed(ActionEvent event)
   {
     if(event.getSource()== clearbutton)
     {
       //clears text boxes
       textfield1.setText("");
       textfield2.setText("");
       textfield3.setText("");
     }
     else if(event.getSource() == computebutton)
     {
       //displays string of name of the employee
       namestring = textfield1.getText();
       employeenameab.setText(namestring);

       successful_calculation = true;
       numberstring1 = textfield2.getText();
       input_is_valid = PayrollCalculatorOperations.isDouble(numberstring1);
       if(input_is_valid) number1 = Double.parseDouble(numberstring1);
       else    successful_calculation = false;


       //Process the second number
       numberstring2 = textfield3.getText();
       input_is_valid = PayrollCalculatorOperations.isDouble(numberstring2);
       if(input_is_valid) number2 = Double.parseDouble(numberstring2);
       else    successful_calculation = false;

       //If both inputs are valid
       if(successful_calculation)
       {
         regular = PayrollCalculatorOperations.regularpay(number1, number2);
         regularstring = String.format("%.2f", regular);
         pay1.setText(regularstring);
         overtime = PayrollCalculatorOperations.overtimepay(number1, number2);
         overtimestring = String.format("%.2f", overtime);
         pay2.setText(overtimestring);
         gross = PayrollCalculatorOperations.grosspay(regular, overtime);
         grossstring = String.format("%.2f", gross);
         pay3.setText(grossstring);
       }
       else
       {
        pay1.setText("Error");
        pay2.setText("Error");
        pay3.setText("Error");
       }

      }
       else if(event.getSource()== quitbutton)
       {
         //exiting out of program
         quitbutton.setEnabled(false);
         computebutton.setEnabled(false);
         clearbutton.setEnabled(false);
         delayedclosing.start();
       }
       else if(event.getSource()==delayedclosing)
       {
         System.exit(0);
       }
     }//end of actionPerformed
   }//end of buttonhandler class
 }//end of PayrollCalculatorframe class
