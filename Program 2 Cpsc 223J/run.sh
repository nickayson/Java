#!/bin/bash

#Author: Nicholas Ayson
#Program: Baseball
#Date completed: 2021-Mar-7

echo "Remove old byte-code files if any exist."
rm *.class


javac Diamond_panel.java

echo "Compile the user interface"
javac Diamond_user_interface.java

echo "Compile the main method"
javac Baseball.java

echo "Run the program BaseBall"
java Baseball

echo "This bash script file is quiting"
