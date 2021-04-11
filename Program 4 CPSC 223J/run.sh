#!/bin/bash

#Author: Nicholas Ayson
#Program: Cat and mouse
#Date completed: 2021-Mar-28

echo "Remove old byte-code files if any exist."
rm *.class


javac Cat_Mouse_panel.java

echo "Compile the user interface"
javac Game_user_interface.java

echo "Compile the main method"
javac ball.java

echo "Run the program Richochet"
java ball

echo "This bash script file is quiting"
