#!/bin/bash

#Program name: Payroll Calculator
#Author: Nicholas Ayson
#Mail: nick.ayson@csu.fullerton.edu
#File name: Payrollrun.sh
#Preconditions:
#   1. All source files are in one directory
#   2. This file is in that same directory
#   3. The shell is currently active in directory
#How to execute: Enter "sh Payrollrun.sh" without quotes

echo Remove old byte-code files if any exist
rm *.class

echo View list of source files
ls -l *.java

echo Compile PayrollCalculatorOperations.java
javac PayrollCalculatorOperations.java

echo Compile PayrollCalculatorframe.java
javac PayrollCalculatorframe.java

echo Compile Payrollmain.java
javac Payrollmain.java

echo Execute the Payroll program
java Payrollmain

echo End of execution.  Have a nice day.
